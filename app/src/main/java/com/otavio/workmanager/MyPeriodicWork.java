package com.otavio.workmanager;

import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Calendar;

import androidx.work.Worker;

class MyPeriodicWork extends Worker {
    @NonNull
    @Override
    public Result doWork() {

        Calendar c = Calendar.getInstance();

        Log.e("Sucess",""+c.getTime());

        return Result.SUCCESS;
    }

}
