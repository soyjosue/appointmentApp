package com.example.petquotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.petquotes.R;
import com.example.petquotes.models.Appointment;

import java.util.List;

public class AppointmentAdapter extends BaseAdapter {

    private Context context;
    private List<Appointment> appointments;
    private int layout;

    public AppointmentAdapter(Context context, List<Appointment> appointments, int layout) {
        this.context = context;
        this.appointments = appointments;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return appointments.size();
    }

    @Override
    public Object getItem(int position) {
        return appointments.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.petName = (TextView) convertView.findViewById(R.id.petName);
            vh.ownerName = (TextView) convertView.findViewById(R.id.ownerName);
            vh.date = (TextView) convertView.findViewById(R.id.tvDate);
            vh.time = (TextView) convertView.findViewById(R.id.tvTime);
            vh.symptom = (TextView) convertView.findViewById(R.id.tvSymptoms);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Appointment appo = appointments.get(position);

        vh.petName.setText(appo.getNamePet());
        vh.ownerName.setText(appo.getNameOwner());
        vh.date.setText(appo.getDate());
        vh.time.setText(appo.getTime());
        vh.symptom.setText(appo.getSymptoms());

        return convertView;
    }

    private class ViewHolder {

        private TextView petName;
        private TextView ownerName;
        private TextView date;
        private TextView time;
        private TextView symptom;

    }
}
