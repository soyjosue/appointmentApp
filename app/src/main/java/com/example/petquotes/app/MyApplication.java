package com.example.petquotes.app;

import android.app.Application;

import com.example.petquotes.models.Appointment;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {

    public static AtomicInteger AppointmentID = new AtomicInteger();

    @Override
    public void onCreate() {
        setUpRealmConfig();
        super.onCreate();

        Realm realm = Realm.getDefaultInstance();

        AppointmentID = getIdTable(realm, Appointment.class);

        realm.close();
    }

    private void setUpRealmConfig() {

        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }

}
