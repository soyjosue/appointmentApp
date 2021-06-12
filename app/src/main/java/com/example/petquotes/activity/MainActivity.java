package com.example.petquotes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petquotes.R;
import com.example.petquotes.app.Helper;
import com.example.petquotes.models.Appointment;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /* LAYOUT ELEMENTS */
    private ImageView ivAddAppointment;
    private ImageView ivSeeAppointments;
    private TextView tvAddAppointment;
    private TextView tvSeeAppointments;

    /* REALM DB */
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Change Color Action Bar */
        Helper.changeColorActionBar(getSupportActionBar());

        /* Realm DB */
        realm = Realm.getDefaultInstance();

        /* Get Layout Elements */
        ivAddAppointment = (ImageView) findViewById(R.id.ivAddAppointment);
        ivSeeAppointments = (ImageView) findViewById(R.id.ivSeeAppointments);
        tvAddAppointment = (TextView) findViewById(R.id.tvAddAppointment);
        tvSeeAppointments = (TextView) findViewById(R.id.tvSeeAppointments);

        /* Add Listening to elements */
        ivAddAppointment.setOnClickListener(this);
        ivSeeAppointments.setOnClickListener(this);
        tvAddAppointment.setOnClickListener(this);
        tvSeeAppointments.setOnClickListener(this);
    }

    /* Menu Items */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset_app:
                realm.beginTransaction();
                realm.deleteAll();
                realm.commitTransaction();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    /* Listening onClick */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ivAddAppointment:
            case R.id.tvAddAppointment:
                Intent intentAdd = new Intent(MainActivity.this, AddAppointmentActivity.class);
                startActivity(intentAdd);
                break;
            case R.id.ivSeeAppointments:
            case R.id.tvSeeAppointments:
                Intent intentView = new Intent(MainActivity.this, AppointmentsActivity.class);
                startActivity(intentView);
                break;
            default:
                Toast.makeText(MainActivity.this, "There was a error. Try again", Toast.LENGTH_LONG).show();
        }
    }
}