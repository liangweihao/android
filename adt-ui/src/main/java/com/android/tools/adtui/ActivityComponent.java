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

package com.android.tools.adtui;

import com.android.tools.adtui.model.event.LifecycleAction;
import com.android.tools.adtui.model.event.EventAction;
import com.android.tools.adtui.model.SeriesData;
import com.android.tools.adtui.model.event.EventModel;
import com.android.tools.adtui.model.event.LifecycleEvent;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

import static com.android.tools.adtui.common.AdtUiUtils.*;

/**
 * A component that renders activities and their fragments.
 */
public class ActivityComponent extends AnimatedComponent {

  public static final int EVENT_LINE_WIDTH_PX = 4;
  public static final int EVENT_LINE_GAP_WIDTH_PX = 1;
  private static final Color DISABLED_ACTION = new JBColor(0xDBDFE2, 0X5E5F60);
  private static final Color ENABLED_ACTION = new JBColor(0x64D8B6, 0x12B0A1);
  private static final Color EVENT_LINE = new JBColor(0x898B8E, 0x999A9A);

  private static final int SEGMENT_SPACING = 5;

  private static final float DEFAULT_LINE_THICKNESS = .3f;
  private static final float FONT_PADDING = 10;
  private static final int FONT_SPACING = 10;

  @NotNull
  private final EventModel<LifecycleEvent> myActivityModel;

  @NotNull
  private final EventModel<LifecycleEvent> myFragmentModel;

  private List<ActivityRenderData> myActivities = new ArrayList<>();
  private List<Double> myFragmentPositions = new ArrayList<>();
  private boolean myRender;

  public ActivityComponent(@NotNull EventModel<LifecycleEvent> activityModel, @NotNull EventModel<LifecycleEvent> fragmentModel) {
    myActivityModel = activityModel;
    myFragmentModel = fragmentModel;
    myActivityModel.addDependency(myAspectObserver).onChange(EventModel.Aspect.EVENT, this::modelChanged);
    myRender = true;
  }

  private void modelChanged() {
    myRender = true;
    opaqueRepaint();
  }

  private void renderActivity() {
    double min = myActivityModel.getRangedSeries().getXRange().getMin();
    double max = myActivityModel.getRangedSeries().getXRange().getMax();

    // A map of EventAction started events to their start time, so we can correlate these to
    // EventAction competed events with the EventAction start events. This is done this way as
    // a event started and completed events may come in in any order at any time.

    // A queue of open index values, this allows us to pack our events without leaving gaps.

    myActivities.clear();
    // Loop through the data series looking at all of the start events, and stop events.
    // For each start event we store off its EventAction until we find an associated stop event.
    // Once we find a stop event we determine the draw order, name, start and stop locations and
    // cache off a path to draw.
    for (SeriesData<EventAction<LifecycleEvent>> seriesData : myActivityModel.getRangedSeries().getSeries()) {
      LifecycleAction data = (LifecycleAction)seriesData.value;
      // Here we normalize the position to a value between 0 and 1. This allows us to scale the width of the line based on the
      // width of our chart.
      double endTime = data.getEndUs() == 0 ? max : data.getEndUs();
      double normalizedEndPosition = ((endTime - min) / (max - min));
      double normalizedStartPosition = ((data.getStartUs() - min) / (max - min));
      if (normalizedStartPosition < 0) {
        normalizedStartPosition = 0;
      }
      Rectangle2D.Double rect =
        new Rectangle2D.Double(normalizedStartPosition, 1 - DEFAULT_LINE_THICKNESS, normalizedEndPosition - normalizedStartPosition,
                               DEFAULT_LINE_THICKNESS);
      myActivities.add(new ActivityRenderData(data, rect));
    }

    myActivities.sort((erd1, erd2) -> {
      if (erd1.getAction().getEndUs() == 0 && erd2.getAction().getEndUs() != 0) {
        return -1;
      }
      else if (erd1.getAction().getEndUs() != 0 && erd2.getAction().getEndUs() == 0) {
        return 1;
      }
      else if (erd1.getAction().getEndUs() != 0 && erd2.getAction().getEndUs() != 0) {
        return erd1.getAction().getEndUs() - erd2.getAction().getEndUs() >= 0 ? 1 : -1;
      }
      return erd1.getAction().getStartUs() - erd2.getAction().getStartUs() >= 0 ? 1 : -1;
    });

    myFragmentPositions.clear();
    for (SeriesData<EventAction<LifecycleEvent>> seriesData : myFragmentModel.getRangedSeries().getSeries()) {
      LifecycleAction data = (LifecycleAction)seriesData.value;
      if (data.getEndUs() >= min && data.getEndUs() < max) {
        myFragmentPositions.add((data.getEndUs() - min) / (max - min));
      }
      if (data.getStartUs() >= min && data.getStartUs() < max) {
        myFragmentPositions.add((data.getStartUs() - min) / (max - min));
      }
    }
  }

  @Override
  protected void draw(Graphics2D g2d, Dimension dim) {
    // Set Antialiasing, before we draw anything.
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    if (myRender) {
      renderActivity();
      myRender = false;
    }
    drawActivity(g2d, dim);
  }

  private void drawActivity(Graphics2D g2d, Dimension dim) {
    int scaleFactor = dim.width;
    AffineTransform scale = AffineTransform.getScaleInstance(scaleFactor, dim.height - SEGMENT_SPACING);
    double min = myActivityModel.getRangedSeries().getXRange().getMin();
    double max = myActivityModel.getRangedSeries().getXRange().getMax();
    FontMetrics metrics = g2d.getFontMetrics();
    Object previousHint = g2d.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

    ListIterator<ActivityRenderData> itor = myActivities.listIterator();
    while (itor.hasNext()) {
      ActivityRenderData renderData = itor.next();
      EventAction<LifecycleEvent> event = renderData.getAction();
      g2d.setColor(event.getEndUs() == 0 ? ENABLED_ACTION : DISABLED_ACTION);
      Shape shape = scale.createTransformedShape(renderData.getPath());
      g2d.fill(shape);

      String text = "";
      if (event.getType() != LifecycleEvent.NONE) {
        text = ((LifecycleAction)event).getName();
      }
      double normalizedStartPosition = (event.getStartUs() - min) / (max - min);
      double lifetime = event.getEndUs();
      if (event.getEndUs() == 0) {
        lifetime = max;
      }
      double normalizedEndPosition = (lifetime - min)
                                     / (max - min);
      float startPosition = (float)normalizedStartPosition * scaleFactor;
      float endPosition = (float)normalizedEndPosition * scaleFactor;

      if (startPosition <= FONT_PADDING) {
        startPosition = FONT_PADDING;
      }

      text = shrinkToFit(text, metrics, endPosition - startPosition);
      if (text.isEmpty()) {
        continue;
      }

      g2d.setColor(DEFAULT_FONT_COLOR);
      g2d.drawString(text, startPosition, FONT_SPACING);
    }
    g2d.setColor(g2d.getBackground());
    for (Double normalizedPosition : myFragmentPositions) {
      g2d.fill(scale.createTransformedShape(
        new Rectangle2D.Double(normalizedPosition - (EVENT_LINE_WIDTH_PX / 2.0 + EVENT_LINE_GAP_WIDTH_PX) / dim.getWidth(),
                               1 - DEFAULT_LINE_THICKNESS, (EVENT_LINE_WIDTH_PX + 2 * EVENT_LINE_GAP_WIDTH_PX) / dim.getWidth(),
                               DEFAULT_LINE_THICKNESS)));
    }

    g2d.setColor(EVENT_LINE);
    for (Double normalizedPosition : myFragmentPositions) {
      g2d.fill(scale.createTransformedShape(
        new Rectangle2D.Double(normalizedPosition - EVENT_LINE_WIDTH_PX / 2.0 / dim.getWidth(), 1 - DEFAULT_LINE_THICKNESS,
                               EVENT_LINE_WIDTH_PX / dim.getWidth(),
                               DEFAULT_LINE_THICKNESS)));
    }
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, previousHint);
  }

  private static class ActivityRenderData {

    private final EventAction<LifecycleEvent> mAction;
    private final Rectangle2D mPath;

    public EventAction<LifecycleEvent> getAction() {
      return mAction;
    }

    public Rectangle2D getPath() {
      return mPath;
    }

    public ActivityRenderData(EventAction<LifecycleEvent> action, Rectangle2D path) {
      mAction = action;
      mPath = path;
    }
  }
}

