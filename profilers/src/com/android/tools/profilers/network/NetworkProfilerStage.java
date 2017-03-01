/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.tools.profilers.network;

import com.android.tools.adtui.model.*;
import com.android.tools.adtui.model.formatter.BaseAxisFormatter;
import com.android.tools.adtui.model.formatter.NetworkTrafficFormatter;
import com.android.tools.adtui.model.formatter.SingleUnitAxisFormatter;
import com.android.tools.adtui.model.legend.LegendComponentModel;
import com.android.tools.adtui.model.legend.SeriesLegend;
import com.android.tools.profilers.*;
import com.android.tools.profilers.common.CodeLocation;
import com.android.tools.profilers.common.CodeNavigator;
import com.android.tools.profilers.common.StackTraceModel;
import com.android.tools.profilers.event.EventMonitor;
import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf3jarjar.ByteString;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class NetworkProfilerStage extends Stage implements CodeNavigator.Listener {

  private static final BaseAxisFormatter TRAFFIC_AXIS_FORMATTER = new NetworkTrafficFormatter(1, 5, 5);
  private static final BaseAxisFormatter CONNECTIONS_AXIS_FORMATTER = new SingleUnitAxisFormatter(1, 5, 1, "");

  // If null, means no connection to show in the details pane.
  @Nullable
  private HttpData mySelectedConnection;

  // Intentionally local field, to prevent GC from cleaning it and removing weak listeners
  @SuppressWarnings("FieldCanBeLocal") private AspectObserver myAspectObserver = new AspectObserver();
  private AspectModel<NetworkProfilerAspect> myAspect = new AspectModel<>();

  StateChartModel<NetworkRadioDataSeries.RadioState> myRadioState;

  private final NetworkConnectionsModel myConnectionsModel =
    new RpcNetworkConnectionsModel(getStudioProfilers().getClient().getProfilerClient(),
                                   getStudioProfilers().getClient().getNetworkClient(), getStudioProfilers().getProcessId(),
                                   getStudioProfilers().getSession());

  private final DetailedNetworkUsage myDetailedNetworkUsage;
  private final NetworkStageLegends myLegends;
  private final AxisComponentModel myTrafficAxis;
  private final AxisComponentModel myConnectionsAxis;
  private final EventMonitor myEventMonitor;
  private final StackTraceModel myStackTraceModel;
  private final SelectionModel mySelectionModel;

  public NetworkProfilerStage(StudioProfilers profilers) {
    super(profilers);

    ProfilerTimeline timeline = profilers.getTimeline();
    NetworkRadioDataSeries radioDataSeries =
      new NetworkRadioDataSeries(profilers.getClient().getNetworkClient(), profilers.getProcessId(), getStudioProfilers().getSession());
    myRadioState = new StateChartModel<>();
    myRadioState.addSeries(new RangedSeries<>(timeline.getViewRange(), radioDataSeries));

    myDetailedNetworkUsage = new DetailedNetworkUsage(profilers);

    myTrafficAxis = new AxisComponentModel(myDetailedNetworkUsage.getTrafficRange(), TRAFFIC_AXIS_FORMATTER);
    myTrafficAxis.setClampToMajorTicks(true);

    myConnectionsAxis = new AxisComponentModel(myDetailedNetworkUsage.getConnectionsRange(), CONNECTIONS_AXIS_FORMATTER);
    myConnectionsAxis.setClampToMajorTicks(true);

    myLegends = new NetworkStageLegends(myDetailedNetworkUsage, timeline.getDataRange());

    myEventMonitor = new EventMonitor(profilers);

    myStackTraceModel = new StackTraceModel(profilers.getIdeServices().getCodeNavigator());

    mySelectionModel = new SelectionModel(timeline.getSelectionRange(), timeline.getViewRange());
    profilers.addDependency(myAspectObserver)
      .onChange(ProfilerAspect.AGENT, () -> mySelectionModel.setSelectionEnabled(profilers.isAgentAttached()));
    mySelectionModel.setSelectionEnabled(profilers.isAgentAttached());

    getStudioProfilers().getTimeline().getSelectionRange().addDependency(myAspectObserver)
      .onChange(Range.Aspect.RANGE, () -> {
        setProfilerMode(getStudioProfilers().getTimeline().getSelectionRange().isEmpty() ? ProfilerMode.NORMAL : ProfilerMode.EXPANDED);
      });
  }

  @NotNull
  public NetworkConnectionsModel getConnectionsModel() {
    return myConnectionsModel;
  }

  @NotNull
  public SelectionModel getSelectionModel() {
    return mySelectionModel;
  }

  @NotNull
  public StackTraceModel getStackTraceModel() {
    return myStackTraceModel;
  }

  /**
   * Sets the active connection, or clears the previously selected active connection if given data is null.
   */
  public void setSelectedConnection(@Nullable HttpData data) {
    if (Objects.equals(mySelectedConnection, data)) {
      return;
    }

    if (data != null && StringUtil.isNotEmpty(data.getResponsePayloadId()) && data.getResponsePayloadFile() == null) {
      ByteString payload = getConnectionsModel().requestResponsePayload(data);
      try {
        File file = getConnectionPayload(payload, data);
        data.setResponsePayloadFile(file);
      }
      catch (IOException e) {
        return;
      }
    }
    mySelectedConnection = data;
    getAspect().changed(NetworkProfilerAspect.ACTIVE_CONNECTION);
  }

  @VisibleForTesting
  File getConnectionPayload(@NotNull ByteString payload, @NotNull HttpData data) throws IOException {
    String extension = (data.getContentType() == null) ? null : data.getContentType().guessFileExtension();
    File file = FileUtil.createTempFile(data.getResponsePayloadId(), StringUtil.notNullize(extension), true);
    FileUtil.writeToFile(file, payload.toByteArray());
    // We don't expect the following call to fail but don't care if it does
    //noinspection ResultOfMethodCallIgnored
    file.setReadOnly();
    return file;
  }

  /**
   * Returns the active connection, or {@code null} if no request is currently selected.
   */
  @Nullable
  public HttpData getSelectedConnection() {
    return mySelectedConnection;
  }

  @NotNull
  public AspectModel<NetworkProfilerAspect> getAspect() {
    return myAspect;
  }

  public StateChartModel<NetworkRadioDataSeries.RadioState> getRadioState() {
    return myRadioState;
  }

  @Override
  public void enter() {
    myEventMonitor.enter();

    getStudioProfilers().getUpdater().register(myRadioState);
    getStudioProfilers().getUpdater().register(myDetailedNetworkUsage);
    getStudioProfilers().getUpdater().register(myTrafficAxis);
    getStudioProfilers().getUpdater().register(myConnectionsAxis);
    getStudioProfilers().getUpdater().register(myLegends);

    getStudioProfilers().getIdeServices().getCodeNavigator().addListener(this);
  }

  @Override
  public void exit() {
    myEventMonitor.exit();

    getStudioProfilers().getUpdater().unregister(myRadioState);
    getStudioProfilers().getUpdater().unregister(myDetailedNetworkUsage);
    getStudioProfilers().getUpdater().unregister(myTrafficAxis);
    getStudioProfilers().getUpdater().unregister(myConnectionsAxis);
    getStudioProfilers().getUpdater().unregister(myLegends);

    getStudioProfilers().getIdeServices().getCodeNavigator().removeListener(this);
  }

  @NotNull
  public String getName() {
    return "NETWORK";
  }

  @NotNull
  public DetailedNetworkUsage getDetailedNetworkUsage() {
    return myDetailedNetworkUsage;
  }

  @NotNull
  public AxisComponentModel getTrafficAxis() {
    return myTrafficAxis;
  }

  @NotNull
  public AxisComponentModel getConnectionsAxis() {
    return myConnectionsAxis;
  }

  @NotNull
  public NetworkStageLegends getLegends() {
    return myLegends;
  }

  @NotNull
  public EventMonitor getEventMonitor() {
    return myEventMonitor;
  }

  @Override
  public void onNavigated(@NotNull CodeLocation location) {
    setProfilerMode(ProfilerMode.NORMAL);
  }

  public static class NetworkStageLegends extends LegendComponentModel {

    private final SeriesLegend myRxLegend;
    private final SeriesLegend myTxLegend;
    private final SeriesLegend myConnectionLegend;

    public NetworkStageLegends(DetailedNetworkUsage usage, Range range) {
      super(ProfilerMonitor.LEGEND_UPDATE_FREQUENCY_MS);
      myRxLegend = new SeriesLegend(usage.getRxSeries(), TRAFFIC_AXIS_FORMATTER, range);
      myTxLegend = new SeriesLegend(usage.getTxSeries(), TRAFFIC_AXIS_FORMATTER, range);
      myConnectionLegend = new SeriesLegend(usage.getConnectionSeries(), CONNECTIONS_AXIS_FORMATTER, range);

      add(myRxLegend);
      add(myTxLegend);
      add(myConnectionLegend);
    }

    public SeriesLegend getRxLegend() {
      return myRxLegend;
    }

    public SeriesLegend getTxLegend() {
      return myTxLegend;
    }

    public SeriesLegend getConnectionLegend() {
      return myConnectionLegend;
    }
  }
}
