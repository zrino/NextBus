package com.example.zpernar.nextbus;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refreshOption:
                importSchedule();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void importSchedule() {
        Log.i("NextBus", "App started");
        // gets current date in YMd format
        String currentDateTime = getCurrentDate();

        Log.i("NextBus", currentDateTime);
        try {
           String url = "http://www.zet.hr/raspored-voznji/325?route_id=218&" + currentDateTime;

            Log.i("NextBus", url);

            new RetrieveScheduleTask(this).execute(url);
        }
        catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private String getCurrentDate() {
        DateFormat dateFormatter = new SimpleDateFormat("yMd");
        Date today = new Date();

        return dateFormatter.format(today);
    }
}
