package com.zhjy.cultural.services.patrol.job;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.zhjy.cultural.services.patrol.ui.route.LocationService;

import java.util.concurrent.TimeUnit;


/**
 * Created by jialg on 2018/1/10.
 */

public class DemoSyncJob extends Job {

    public static final String TAG = "job_demo_tag";

    public static Intent lntentServices;

    public static Context context;

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        Log.e(TAG,"JOB IS RUNNING!");
        lntentServices = new Intent(getContext(), LocationService.class);
        getContext().startService(lntentServices);
        // run your job here
        return Result.SUCCESS;
    }

    public static void scheduleJob() {
        cancelAll();
        int jobId = new JobRequest.Builder(DemoSyncJob.TAG)
                //.setExecutionWindow(30_000L, 40_000L)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(15))
                .build()
                .schedule();
    }

    public static void cancelJob(int jobId) {
        JobManager.instance().cancel(jobId);
    }

    public static void cancelAll(){
        JobManager.instance().cancelAllForTag(TAG);
    }
}
