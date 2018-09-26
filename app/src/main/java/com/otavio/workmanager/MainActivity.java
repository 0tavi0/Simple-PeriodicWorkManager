package com.otavio.workmanager;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkStatus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);

        final Calendar c = Calendar.getInstance();


        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .setRequiresBatteryNotLow(false)
                .setRequiresStorageNotLow(false)
                .build();

        final PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
                MyPeriodicWork.class,
                15, TimeUnit.MINUTES)
                .addTag("teste4")
                .setConstraints(constraints)
                .build();


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance().cancelAllWork();

                Log.e("Cancel"," ID: Cancel all");
            }
        });

//        WorkManager.getInstance().getStatusById(periodicWorkRequest.getId())
//                .observe(this, new Observer<WorkStatus>() {
//                    @Override
//                    public void onChanged(@Nullable WorkStatus workStatus) {
//                        if (workStatus != null) {
//                            Log.e("SimpleWorkRequest: " + workStatus.getState().name(), "Log");
//                        }
//
//                        if (workStatus != null && workStatus.getState().isFinished()) {
//                            Log.e("Mensage: " + workStatus.getState().name(), "Log");
//
//                        }
//                    }
//                });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Clicado","ID"+periodicWorkRequest.getId());
                Log.e("Horario","ID"+c.getTime());


                WorkManager.getInstance().enqueue(periodicWorkRequest);




            }
        });
    }



}
