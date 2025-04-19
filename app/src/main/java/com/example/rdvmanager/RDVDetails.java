package com.example.rdvmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RDVDetails extends AppCompatActivity {

    private EditText etTitle;
    private EditText etPerson;
    private EditText etPhone;
    private TextView tvId;
    private DatabaseHelper myHelper;

    private int year,month,day;
    DatePickerDialog.OnDateSetListener onDate ;
    Button btnPickDate;
    String stringDate;
    private EditText etDate;

    int hours, minutes;
    TimePickerDialog.OnTimeSetListener onTime;
    Button btnPickTime;
    String stringTime;
    private EditText etTime;

    boolean fromAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdv_details);
        etTitle = findViewById(R.id.etTitle);
        etPerson = findViewById(R.id.etPerson);
        etPhone = findViewById(R.id.etPhone);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        btnPickDate=findViewById(R.id.btnPickDate);
        btnPickTime=findViewById(R.id.btnPickTime);
        tvId = findViewById(R.id.tvId);
        myHelper = new DatabaseHelper(this);
        myHelper.open();

        onDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay)
            {
                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;
                stringDate = new StringBuilder().append(day).append("/")
                        .append(month + 1).append("/")
                        .append(year).toString();
                etDate.setText(stringDate);
            }
        };

        onTime = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                hours = hour;
                minutes = minute;

                stringTime = new StringBuilder().append(hours).append(":").append(minutes).toString();
                etTime.setText(stringTime);
            }
        };

        Intent intent = getIntent();
        fromAdd = intent.getBooleanExtra("fromAdd", true);
        if(!fromAdd){
            Bundle b = intent.getExtras();
            RDV selectedRDV = b.getParcelable("SelectedRDV");
            tvId.setText(selectedRDV.getId());
            etTitle.setText(selectedRDV.getTitle());
            etPerson.setText(selectedRDV.getPerson());
            etPhone.setText(selectedRDV.getPhone());
            etDate.setText(selectedRDV.getDate());
            etTime.setText(selectedRDV.getTime());
        }
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        Bundle args = new Bundle();
        args.putInt("year",year);
        args.putInt("month",month);
        args.putInt("day",day);
        date.setArguments(args);
        date.setCallBack(onDate);
        date.show(getSupportFragmentManager(),"Date Picker");
    }

    public void pickDate(View view){
        showDatePicker();
    }

    private void showTimePicker() {
        TimePickerFragment time= new TimePickerFragment();
        final Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        Bundle args = new Bundle();
        args.putInt("hours",hours);
        args.putInt("minutes",minutes);
        time.setArguments(args);
        time.setCallBack(onTime);
        time.show(getSupportFragmentManager(),"Time Picker");
    }

    public void pickTime(View view){
        showTimePicker();
    }

    public void onCancelClick(View v) {
        finish();
    }

    public void saveRDV(View view) {
        String title = etTitle.getText().toString();
        String person = etPerson.getText().toString();
        String phone = etPhone.getText().toString();
        String date = etDate.getText().toString();
        String time = etTime.getText().toString();
        if (fromAdd) {
            RDV rdv = new RDV(title, date, time, person, phone);
            myHelper.add(rdv);
            Intent main = new Intent(this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
        } else {
            int id = Integer.parseInt(tvId.getText().toString());
            RDV rdv = new RDV(id,title, date, time, person, phone);
            int n = myHelper.update(rdv);

            Intent main = new Intent(this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
        }
    }

}
