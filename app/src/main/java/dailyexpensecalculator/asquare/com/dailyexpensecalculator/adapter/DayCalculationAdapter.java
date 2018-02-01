package dailyexpensecalculator.asquare.com.dailyexpensecalculator.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dailyexpensecalculator.asquare.com.dailyexpensecalculator.R;
import dailyexpensecalculator.asquare.com.dailyexpensecalculator.entity.DayCalculation;

/**
 * Created by akshaykumar on 22/1/18.
 */

public class DayCalculationAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<DayCalculation> mdayCalculationArrayList;

    public DayCalculationAdapter(Context mContext, ArrayList<DayCalculation> dayCalculationArrayList) {
        this.mContext = mContext;
        this.mdayCalculationArrayList = dayCalculationArrayList;
    }

    @Override
    public int getCount() {
        return mdayCalculationArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view = View.inflate(mContext, R.layout.today_items,null);

        TextView txtAmount = view.findViewById(R.id.txt_amount);
        TextView txtPurpose = view.findViewById(R.id.txt_purpose);
        TextView txtTime = view.findViewById(R.id.txt_time);

        DayCalculation dayCalculation = mdayCalculationArrayList.get(position);

        txtAmount.setText(dayCalculation.getAmount());
        txtPurpose.setText(dayCalculation.getPurpose());
        txtTime.setText(dayCalculation.getTime());

        return  view;



    }
}
