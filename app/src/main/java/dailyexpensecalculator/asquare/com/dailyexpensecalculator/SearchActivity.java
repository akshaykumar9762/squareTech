package dailyexpensecalculator.asquare.com.dailyexpensecalculator;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import dailyexpensecalculator.asquare.com.dailyexpensecalculator.adapter.DayCalculationAdapter;
import dailyexpensecalculator.asquare.com.dailyexpensecalculator.database.DayCalculationDb;
import dailyexpensecalculator.asquare.com.dailyexpensecalculator.entity.DayCalculation;

/**
 * Created by akshaykumar on 23/1/18.
 */

public class SearchActivity extends AppCompatActivity {

    TextView txtDatePicker, txtTotal;
    Button btnGo;
    ListView dateListView;
   String dateString = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_activity);

        txtDatePicker = findViewById(R.id.txt_date);
        txtTotal = findViewById(R.id.txt_total);
        btnGo = findViewById(R.id.btn_go);
        dateListView = findViewById(R.id.date_listView);


        txtDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDatepicker();
            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(dateString == null)
                {
                    txtDatePicker.setError("Date is required");
                }else {

                    ArrayList<DayCalculation> dayCalculationArrayList = new ArrayList<>();

                    DayCalculationDb dayCalculationDb = new DayCalculationDb(SearchActivity.this);

                    dayCalculationArrayList = dayCalculationDb.fetchDaycalculationDateWise(dateString);

                    DayCalculationAdapter dayCalculationAdapter = new DayCalculationAdapter(SearchActivity.this, dayCalculationArrayList);

                    String total = dayCalculationDb.fetchTotal(dateString);

                    dateListView.setAdapter(dayCalculationAdapter);

                    txtTotal.setText("Total: " + total);
                }
            }
        });

    }
//to make class as immutable
//clas as final,all data member are orivate,allm utable values final,only getters,initialize all field by using deep copy,perform object cloning while returning objrct




    public void showDatepicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String monthString ="xyz";

                if((month+1) == 1){
                    monthString ="Jan";
                }else if((month+1) == 2){
                    monthString ="Feb";
                }else if((month+1) == 3){
                    monthString ="Mar";
                }else if((month+1) == 4){
                    monthString ="Apr";
                }else if((month+1) == 5){
                    monthString ="May";
                }else if((month+1) == 6){
                    monthString ="Jun";
                }else if((month+1) == 7){
                    monthString ="Jul";
                }else if((month+1) == 8){
                    monthString ="Aug";
                }else if((month+1) == 9){
                    monthString ="Sep";
                }else if((month+1) == 10){
                    monthString ="Oct";
                }else if((month+1) == 11){
                    monthString ="Nov";
                }else if((month+1) == 12){
                    monthString ="Dec";
                }

                txtDatePicker.setText(day +" "+monthString +" "+year);
                dateString = day +" "+monthString +" "+year;

            }
        }, 2018, 00, 1);

        datePickerDialog.show();
    }

}

