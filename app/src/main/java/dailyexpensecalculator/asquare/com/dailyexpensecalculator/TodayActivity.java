package dailyexpensecalculator.asquare.com.dailyexpensecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import dailyexpensecalculator.asquare.com.dailyexpensecalculator.adapter.DayCalculationAdapter;
import dailyexpensecalculator.asquare.com.dailyexpensecalculator.database.DayCalculationDb;
import dailyexpensecalculator.asquare.com.dailyexpensecalculator.entity.DayCalculation;

/**
 * Created by akshaykumar on 22/1/18.
 */

public class TodayActivity extends AppCompatActivity {

ListView todaylist;
ArrayList<DayCalculation> daycalculationlist;
TextView txtTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.today_activity);

        initViews();

        }

    public void initViews() {
        txtTotal = findViewById(R.id.txt_total);
        todaylist = (ListView) findViewById(R.id.today_listview);

        daycalculationlist = new ArrayList<>();
        Intent intent = getIntent();
        String dateString = intent.getStringExtra("date");
        String total = intent.getStringExtra("total");
        DayCalculationDb dayCalculationdb = new DayCalculationDb(TodayActivity.this);
        daycalculationlist = dayCalculationdb.fetchtodayDetails(dateString);

        DayCalculationAdapter dayCalculationAdapter = new DayCalculationAdapter(TodayActivity.this,daycalculationlist);

        txtTotal.setText(dateString+" || Total: "+total);
        todaylist.setAdapter(dayCalculationAdapter);


    }
}
