package com.example.rdvmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RDVDetails extends AppCompatActivity {

    private EditText etTitle;
    private EditText etPerson;
    private EditText etPhone;
    private DatabaseHelper myHelper;

    private int year,month,day;
    DatePickerDialog.OnDateSetListener onDate ;
    Button btnPickDate;
    String stringDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdv_details);
        etTitle = findViewById(R.id.etTitle);
        etPerson = findViewById(R.id.etPerson);
        etPhone = findViewById(R.id.etPhone);
        btnPickDate=findViewById(R.id.btnPickDate);
        myHelper = new DatabaseHelper(this);
        myHelper.open();

        onDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay)
            {
                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;
                stringDate = new StringBuilder().append(day).append("-")
                        .append(month + 1).append("-")
                        .append(year).toString();
            }
        };

        /*Intent intent = getIntent();
        boolean fromAdd = intent.getBooleanExtra("fromAdd", false);
        if(!fromAdd){
            Bundle b= intent.getExtras();
            RDV selectedMoment= b.getParcelable("SelectedRDV");
            //tvId.setText(String.valueOf(selectedMoment.getId()));
            etTitle.setText(selectedMoment.getTitle());
            etPerson.setText(selectedMoment.getPerson());
        }*/

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

    public void onCancelClick(View v) {
        finish();
    }

    public void saveRDV(View view) {
        String title= etTitle.getText().toString();
        String person = etPerson.getText().toString();
        String phone = etPhone.getText().toString();
        RDV moment = new RDV(title, stringDate, person, phone);
        myHelper.add(moment);
        Intent main = new Intent(this,MainActivity.class);//.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
    }

}
