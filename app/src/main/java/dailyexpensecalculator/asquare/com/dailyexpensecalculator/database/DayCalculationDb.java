package dailyexpensecalculator.asquare.com.dailyexpensecalculator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import dailyexpensecalculator.asquare.com.dailyexpensecalculator.entity.DayCalculation;

/**
 * Created by akshaykumar on 22/1/18.
 */

public class DayCalculationDb extends SQLiteOpenHelper {


    Context mContext;
    SQLiteDatabase database;


    final static String DB_NAME = "ExpenseCalculator";
    final static String TABLE_NAME = "dayCalculations";
    final static String COL_DATE = "date";
    final static String COL_TIME = "time";
    final static String COL_AMOUNT = "amount";
    final static String COL_PURPOSE = "purpose";
    final static String COL_TOTAL = "total";


    public DayCalculationDb(Context context) {
        super(context, DB_NAME, null, 1);


        this.mContext = context;

    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "Create table " + TABLE_NAME + "(" + COL_DATE + " varchar(30), "+
                      COL_TIME + " varchar(30), "+
                      COL_AMOUNT + " varchar(50), "+
                      COL_PURPOSE + " varchar(30), "+
                      COL_TOTAL + " varchar(30) "+ ");";

        sqLiteDatabase.execSQL(sql);


    }

    public void insertDayCalculations(DayCalculation dayCalculation)
    {

        database = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COL_DATE,dayCalculation.getDate());
        cv.put(COL_TIME,dayCalculation.getTime());
        cv.put(COL_AMOUNT,dayCalculation.getAmount());
        cv.put(COL_PURPOSE,dayCalculation.getPurpose());
        cv.put(COL_TOTAL,dayCalculation.getTotal());

        long id = database.insert(TABLE_NAME,null,cv);
        Toast.makeText(mContext,"Amount is added...",Toast.LENGTH_SHORT).show();
        database.close();



    }

    public String fetchTotal(String dateString)
    {
        database = getReadableDatabase();

        String total_temp = null;
        int sum = 0;

        Log.i("here", "fetchTotal: "+total_temp);

        String query = "Select "+ COL_AMOUNT+" from "+TABLE_NAME+" where "+COL_DATE+" = ?";
        Cursor cursor = database.rawQuery(query,new String[]{dateString});

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            total_temp = cursor.getString(cursor.getColumnIndex(COL_AMOUNT));
            Log.i("here", "fetchTotal: "+total_temp);


             int temp = Integer.valueOf(total_temp);
            sum = sum + temp;
            cursor.moveToNext();
        }}

        String total = String.valueOf(sum);

        return total;
    }

    public ArrayList<DayCalculation> fetchtodayDetails(String dateString){

        database = getReadableDatabase();

        ArrayList<DayCalculation> dayCalculations = new ArrayList<>();

        String sql ="select * from "+TABLE_NAME+" where "+COL_DATE+" = ?";

        Cursor cursor = database.rawQuery(sql,new String[]{dateString});

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                String amount = cursor.getString(cursor.getColumnIndex(COL_AMOUNT));
                String purpose = cursor.getString(cursor.getColumnIndex(COL_PURPOSE));
                String time = cursor.getString(cursor.getColumnIndex(COL_TIME));

                DayCalculation dayCalculation = new DayCalculation();
                dayCalculation.setAmount(amount);
                dayCalculation.setPurpose(purpose);
                dayCalculation.setTime(time);
                dayCalculation.setDate("xyz");
                dayCalculation.setTotal("z");

                dayCalculations.add(dayCalculation);
                cursor.moveToNext();
            }
        }

        return dayCalculations;
    }


    public ArrayList<DayCalculation> fetchDaycalculationDateWise(String dateString)
    {


        database = getReadableDatabase();

        ArrayList<DayCalculation> dayCalculations = new ArrayList<>();

        String sql = "select * from "+TABLE_NAME+" where "+COL_DATE+" = ?";

        Cursor cursor = database.rawQuery(sql,new String[]{dateString});

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();

            while(!cursor.isAfterLast())
            {
                String amount = cursor.getString(cursor.getColumnIndex(COL_AMOUNT));
                String purpose = cursor.getString(cursor.getColumnIndex(COL_PURPOSE));
                String time = cursor.getString(cursor.getColumnIndex(COL_TIME));

                DayCalculation dayCalculation = new DayCalculation();
                dayCalculation.setAmount(amount);
                dayCalculation.setPurpose(purpose);
                dayCalculation.setTime(time);
                dayCalculation.setDate("xyz");
                dayCalculation.setTotal("z");

                dayCalculations.add(dayCalculation);
                cursor.moveToNext();

            }

        }

        return dayCalculations;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
