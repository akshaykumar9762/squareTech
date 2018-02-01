package dailyexpensecalculator.asquare.com.dailyexpensecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import dailyexpensecalculator.asquare.com.dailyexpensecalculator.database.DayCalculationDb;
import dailyexpensecalculator.asquare.com.dailyexpensecalculator.entity.DayCalculation;

public class MainActivity extends AppCompatActivity {


    EditText edtAmount, edtPurpose;
    Button btn10, btn25, btn50, btn100, btnToday, btnAdd, btnFind;
    TextView txtToday, txtTotal;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtAmount = findViewById(R.id.edt_amount);
        edtPurpose = findViewById(R.id.edt_purpose);
        txtToday = findViewById(R.id.txt_today);
        txtTotal = findViewById(R.id.txt_total);

        btn10 = findViewById(R.id.btn_ten);
        btn25 = findViewById(R.id.btn_twenty_five);
        btn50 = findViewById(R.id.btn_fifty);
        btn100 = findViewById(R.id.btn_hundred);
        btnToday = findViewById(R.id.btn_today);
        btnAdd = findViewById(R.id.bt_add);
        btnFind = findViewById(R.id.btn_find);

        final long date = System.currentTimeMillis();

        //"MMM MM dd, yyyy h:mm a" =>Jan 01 22,2018 4:55 PM
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        final String dateString = sdf.format(date);

        SimpleDateFormat sdf_time = new SimpleDateFormat("h:mm a");
        final String timeString = sdf_time.format(date);

        txtToday.setText(dateString);  //date set to string

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtAmount.getText().toString().length() == 0) {
                    edtAmount.setError("Amount is required");
                } else {

                    String amount = edtAmount.getText().toString();
                    String purpose = edtPurpose.getText().toString();

                    DayCalculation dayCalculation = new DayCalculation();

                    dayCalculation.setAmount(amount);    //setting values to daycalculation object
                    dayCalculation.setPurpose(purpose);
                    dayCalculation.setTime(timeString);
                    dayCalculation.setDate(dateString);

                    DayCalculationDb dayCalculationDb = new DayCalculationDb(MainActivity.this);//value inserted into database
                    dayCalculationDb.insertDayCalculations(dayCalculation);

                    DayCalculationDb dayCalculationdb = new DayCalculationDb(MainActivity.this);

                    String total = dayCalculationdb.fetchTotal(dateString);

                    txtTotal.setText("Todays Total: " + total);
                }
            }
        });


        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DayCalculationDb dayCalculationDb = new DayCalculationDb(MainActivity.this);
                String total = dayCalculationDb.fetchTotal(dateString);
                Intent intent = new Intent(MainActivity.this, TodayActivity.class);
                intent.putExtra("date", dateString);
                intent.putExtra("total", total);
                startActivity(intent);


            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);

                startActivity(intent);
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtAmount.setText("10");
                edtPurpose.setText("Tea");
            }
        });

        btn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtAmount.setText("25");
                edtPurpose.setText("Break Fast");
            }
        });
        btn50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtAmount.setText("50");
                edtPurpose.setText("Dinner");
            }
        });
        btn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtAmount.setText("100");
                edtPurpose.setText("ETC");
            }
        });


    }
}
