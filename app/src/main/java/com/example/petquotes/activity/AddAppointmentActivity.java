package com.example.petquotes.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.petquotes.app.Helper;
import com.example.petquotes.models.Appointment;
import com.example.petquotes.picker.DatePickerFragment;
import com.example.petquotes.picker.TimePickerFragment;
import com.example.petquotes.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddAppointmentActivity extends AppCompatActivity implements View.OnClickListener {

    /* Layout Elements */
    private EditText etPetName;
    private EditText etOwnerName;
    private EditText etDate;
    private EditText etTime;
    private EditText etSymptoms;
    private Button btnAdd;

    /* DATABASE */
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        /* Modify Action Bar */
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        Helper.changeColorActionBar(ab);

        /* REALM */
        realm = Realm.getDefaultInstance();

        /* Find elements in activity */
        etPetName = (EditText) findViewById(R.id.etPetName);
        etOwnerName = (EditText) findViewById(R.id.etOwnerName);
        etDate = (EditText) findViewById(R.id.etDate);
        etTime = (EditText) findViewById(R.id.etTime);
        etSymptoms = (EditText) findViewById(R.id.etSymptoms);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        /* Add Listening */
        etDate.setOnClickListener(this);
        etTime.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

    }

    /* Elements Listener */

    private void showDatePicker() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String selectDate = day + "/" + (month+1) + "/" + year;
                etDate.setText(selectDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void showTimePicker() {
        TimePickerFragment fragment = TimePickerFragment.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                String selectTime = hour + ":" + minute;
                etTime.setText(selectTime);
            }
        });

        fragment.show(getSupportFragmentManager(), "timePicker");
    }

    private void btnAddListener() {
        if(Helper.isEmptyEt(etPetName) || Helper.isEmptyEt(etOwnerName) || Helper.isEmptyEt(etDate) || Helper.isEmptyEt(etTime) || Helper.isEmptyEt(etSymptoms)) {
            Toast.makeText(getApplicationContext(), "You need to fill in all the fields for create a appointment.", Toast.LENGTH_LONG).show();
        } else {
            realm.beginTransaction();

            Appointment appointment = new Appointment();
            appointment.setNamePet(Helper.getTextEt(etPetName));
            appointment.setNameOwner(Helper.getTextEt(etOwnerName));
            appointment.setDate(Helper.getTextEt(etDate));
            appointment.setTime(Helper.getTextEt(etTime));
            appointment.setSymptoms(Helper.getTextEt(etSymptoms));

            realm.copyToRealm(appointment);

            realm.commitTransaction();

            finish();
        }


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.etDate:
                showDatePicker();
                break;
            case R.id.etTime:
                showTimePicker();
                break;
            case R.id.btnAdd:
                btnAddListener();
                break;
            default:
                Toast.makeText(AddAppointmentActivity.this, "There was a Error. Try Again", Toast.LENGTH_LONG).show();
        }

    }

}