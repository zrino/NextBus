package com.example.zpernar.nextbus;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RetrieveScheduleTask extends AsyncTask<String, Integer, ArrayList<BusScheduleEntry>> {
    private Exception exception;

    private Activity activity;

    private TableLayout table;

    RetrieveScheduleTask(Activity activity) {
        this.activity = activity;
    }

    protected ArrayList<BusScheduleEntry> doInBackground(String... urls) {
        try {
            String url = new String(urls[0]);
            Document doc = Jsoup.connect(url).get();
            Log.i("NextBus", url);
            // first we find table
            Element table = doc.select(".raspored").first();
            if (table == null) {
                throw new Exception("Data could not be found at URL");
            }

            Elements elementRows = table.select("tr");

            // we remove first row as that contains titles, not the actual values we need
            elementRows.remove(0);

            ArrayList<BusScheduleEntry> busList = new ArrayList<>();

            for(int i = 0; i < elementRows.size(); i++) {
                Element elementRow = elementRows.get(i);
                Elements elementDataCells = elementRow.select("td");

                String time = elementDataCells.get(0).select("a").first().text();
                String from = elementDataCells.get(1).text();
                String to = elementDataCells.get(3).text();

                BusScheduleEntry busEntry = new BusScheduleEntry(time, from, to);
                busList.add(busEntry);
            }

            return busList;
        }
        catch (Exception e) {
            this.exception = e;
            Log.e("Error", e.getMessage());
            return null;
        }
    }

    protected void onPostExecute(ArrayList<BusScheduleEntry> busList) {
        display(busList);
    }

    private void display(ArrayList<BusScheduleEntry> output) {
        TableLayout tableLayout = (TableLayout) this.activity.findViewById(R.id.tableLayout);

        for (int i = 0; i < output.size(); i++)
        {
            TableRow tr = new TableRow(activity);
            TextView time = new TextView(activity);
            time.setText(output.get(i).getTime());

            TableRow.LayoutParams = new TableRow.LayoutParams()
            tr.setLayoutParams();

            tr.addView(time);

            tableLayout.addView(tr);
        }
    }
}
