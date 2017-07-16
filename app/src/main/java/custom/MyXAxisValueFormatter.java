package custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.uottawa.gradetogo.Singleton;

/**
 * Created by ychah102 on 7/16/2017.
 */

public class MyXAxisValueFormatter implements IAxisValueFormatter {

    private String[] mValues;

    public MyXAxisValueFormatter(String[] values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        String valueGrade  = Singleton.getSingleton().getGrade(value);
        return valueGrade;
    }

}