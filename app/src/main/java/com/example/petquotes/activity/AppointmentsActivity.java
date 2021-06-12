package com.example.petquotes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.petquotes.R;
import com.example.petquotes.adapters.AppointmentAdapter;
import com.example.petquotes.app.Helper;
import com.example.petquotes.models.Appointment;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class AppointmentsActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Appointment>> {

    private ListView listView;

    private AppointmentAdapter adapter;

    private Realm realm;
    private RealmResults<Appointment> appointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        /* Modify Action Bar */
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        Helper.changeColorActionBar(ab);

        realm = Realm.getDefaultInstance();
        appointments = realm.where(Appointment.class).findAll();
        appointments.addChangeListener(this);

        listView = findViewById(R.id.listView);

        adapter = new AppointmentAdapter(this, appointments, R.layout.list_view_appointment_item);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
    }

    private void deleteAppointment(Appointment appo) {
        realm.beginTransaction();
        appo.deleteFromRealm();
        realm.commitTransaction();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("Mascota: " + appointments.get(info.position).getNamePet());
        getMenuInflater().inflate(R.menu.context_menu_appointments_activity, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_appointment:
                deleteAppointment(appointments.get(info.position));
                return true;
            case R.id.edit_appointment:
                Intent intent = new Intent(AppointmentsActivity.this, EditAppointmentActivity.class);
                intent.putExtra("id", appointments.get(info.position).getId());
                startActivity(intent);
                return true;
            default: return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onChange(RealmResults<Appointment> appointments) {
        adapter.notifyDataSetChanged();
    }
}