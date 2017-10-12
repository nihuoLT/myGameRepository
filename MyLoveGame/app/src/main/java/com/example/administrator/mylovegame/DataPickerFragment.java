package com.example.administrator.mylovegame;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/11/10 0010.
 */
public class DataPickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private int year,month,day;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(getActivity(),this,year,month,day);
        return dialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        this.year=year;
        this.month=monthOfYear;
        this.day=dayOfMonth;
        MainActivity activity=(MainActivity)getActivity();
        activity.setDateValue(year,monthOfYear,dayOfMonth);
    }
}
