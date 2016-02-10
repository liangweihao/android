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
package com.android.tools.idea.fd;

import com.android.tools.idea.stats.UsageTracker;
import com.google.common.collect.Maps;
import com.intellij.concurrency.JobScheduler;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class InstantRunStatsService {
  // On average, we'll lose stats from half the upload interval.
  public static final int UPLOAD_INTERVAL_MINUTES = 10;

  public enum DeployType {
    LEGACY,   // full apk installation when IR is disabled
    FULLAPK,  // full apk installation when IR is enabled
    HOTSWAP,
    SPLITAPK, // split apk installation as part of cold swap (however, split APKs are currently disabled..)
    DEX,      // cold swap scheme that uses dex files
  }

  private final Object LOCK = new Object();

  /**
   * Current session id: A session starts from installing an APK, continues through multiple hot/cold swaps until the next full apk install
   */
  private UUID mySessionId;

  private int myDeployStartedCount;
  private int[] myDeployTypeCounts = new int[DeployType.values().length];

  private int myRestartLaunchCount;

  public static InstantRunStatsService get(@NotNull Project project) {
    return ServiceManager.getService(project, InstantRunStatsService.class);
  }

  private InstantRunStatsService() {
    JobScheduler.getScheduler().scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        uploadStats();
      }
    }, UPLOAD_INTERVAL_MINUTES, UPLOAD_INTERVAL_MINUTES, TimeUnit.MINUTES);
  }

  public void notifyDeployStarted() {
    synchronized (LOCK) {
      myDeployStartedCount++;
    }
  }


  public void notifyDeployType(@NotNull DeployType type) {
    synchronized (LOCK) {
      if (type == DeployType.FULLAPK) {
        // We want to assign a session id for all launches in order to compute the number of hot/coldswaps between each APK push
        // Installing an APK starts a new session.
        resetSession();
      }

      myDeployTypeCounts[type.ordinal()]++;
    }
  }

  private void resetSession() {
    synchronized (LOCK) {
      if (mySessionId != null) {
        // Since we only keep track of the current session, upload all existing stats that belong to the previous session
        uploadStats();
      }

      mySessionId = UUID.randomUUID();
    }
  }

  public void incrementRestartLaunchCount() {
    synchronized (LOCK) {
      myRestartLaunchCount++;
    }
  }

  private void uploadStats() {
    int deployCount;
    int[] deployTypeCount = new int[myDeployTypeCounts.length];
    int restartCount;
    String sessionId;

    synchronized (LOCK) {
      if (myDeployStartedCount == 0) {
        return;
      }

      deployCount = myDeployStartedCount;
      restartCount = myRestartLaunchCount;
      System.arraycopy(myDeployTypeCounts, 0, deployTypeCount, 0, myDeployTypeCounts.length);

      myDeployStartedCount = 0;
      myRestartLaunchCount = 0;
      for (int i = 0; i < myDeployTypeCounts.length; i++) {
        myDeployTypeCounts[i] = 0;
      }
      sessionId = mySessionId.toString();
    }

    Map<String,String> kv = Maps.newHashMap();
    kv.put("deploycount", Integer.toString(deployCount));
    kv.put("restartBuild", Integer.toString(restartCount));
    kv.put("sessionId", sessionId);
    for (DeployType type : DeployType.values()) {
      kv.put(type.toString(), Integer.toString(deployTypeCount[type.ordinal()]));
    }

    UsageTracker.getInstance().trackInstantRunStats(kv);
  }
}
