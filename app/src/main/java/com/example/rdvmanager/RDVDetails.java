package com.example.rdvmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RDVDetails extends AppCompatActivity {

    private EditText etTitle;
    private EditText etPerson;
    private EditText etPhone;
    private DatabaseHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdv_details);
        etTitle = findViewById(R.id.etTitle);
        etPerson = findViewById(R.id.etPerson);
        etPhone = findViewById(R.id.etPhone);
        myHelper = new DatabaseHelper(this);
        myHelper.open();

        Intent intent = getIntent();
        /*boolean fromAdd = intent.getBooleanExtra("fromAdd", false);
        if(!fromAdd){
            Bundle b= intent.getExtras();
            RDV selectedMoment= b.getParcelable("SelectedRDV");
            //tvId.setText(String.valueOf(selectedMoment.getId()));
            etTitle.setText(selectedMoment.getTitle());
            etPerson.setText(selectedMoment.getPerson());
        }*/

    }

    public void onCancelClick(View v) {
        finish();
    }

    public void saveRDV(View view) {
        String title= etTitle.getText().toString();
        String person = etPerson.getText().toString();
        String phone = etPhone.getText().toString();
        RDV moment = new RDV(title, person, phone);
        myHelper.add(moment);
        Intent main = new Intent(this,MainActivity.class);//.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
    }

}
