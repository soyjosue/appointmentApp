package com.example.petquotes.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petquotes.R;
import com.example.petquotes.app.Helper;
import com.example.petquotes.models.Appointment;

import io.realm.Realm;

public class EditAppointmentActivity extends AppCompatActivity implements View.OnClickListener {

    /* Layout Elements */
    private EditText etPetName;
    private EditText etOwnerName;
    private EditText etDate;
    private EditText etTime;
    private EditText etSymptoms;
    private Button btnAdd;

    /* DATABASE */
    private Realm realm;

    private int appointmentId;
    private Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);

        /* Modify Action Bar */
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        Helper.changeColorActionBar(ab);

        realm = Realm.getDefaultInstance();

        if(getIntent().getExtras() != null)
            appointmentId = getIntent().getExtras().getInt("id");

        appointment = realm.where(Appointment.class).equalTo("id", appointmentId).findFirst();

        /* Find elements in activity */
        etPetName = (EditText) findViewById(R.id.etPetName);
        etOwnerName = (EditText) findViewById(R.id.etOwnerName);
        etDate = (EditText) findViewById(R.id.etDate);
        etTime = (EditText) findViewById(R.id.etTime);
        etSymptoms = (EditText) findViewById(R.id.etSymptoms);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        etPetName.setText(appointment.getNamePet());
        etOwnerName.setText(appointment.getNameOwner());
        etDate.setText(appointment.getDate());
        etTime.setText(appointment.getTime());
        etSymptoms.setText(appointment.getSymptoms());

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(Helper.isEmptyEt(etPetName) || Helper.isEmptyEt(etOwnerName) || Helper.isEmptyEt(etDate) || Helper.isEmptyEt(etTime) || Helper.isEmptyEt(etSymptoms)) {
            Toast.makeText(getApplicationContext(), "You need to fill in all the fields for edit a appointment.", Toast.LENGTH_LONG).show();
        } else {
            realm.beginTransaction();

            appointment.setNamePet(Helper.getTextEt(etPetName));
            appointment.setNameOwner(Helper.getTextEt(etOwnerName));
            appointment.setDate(Helper.getTextEt(etDate));
            appointment.setTime(Helper.getTextEt(etTime));
            appointment.setSymptoms(Helper.getTextEt(etSymptoms));

            realm.copyToRealmOrUpdate(appointment);

            realm.commitTransaction();

            finish();
        }

    }
}