package com.varunb.meditationminder;

import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rajeevbansal on 5/28/15.
 */
public class SessionsCustomAdapter extends ArrayAdapter<Session> {
    //private LayoutInflater mLayoutInflater;
    private static FragmentManager sFragmentManager;

    public SessionsCustomAdapter(Context context, ArrayList<Session> sessions) {
        super(context, 0, sessions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Session session = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(com.varunb.meditationminder.R.layout.custom_session, parent, false);
            //inflate(R.layout.custom_session, parent, false);
        }

    /*else

    {
        view = convertView;
    }*/

        TextView txtDate = (TextView) convertView.findViewById(com.varunb.meditationminder.R.id.session_date);
        TextView txtDuration = (TextView) convertView.findViewById(com.varunb.meditationminder.R.id.session_duration);

        txtDate.setText(session.getDate());
        txtDuration.setText(session.getDuration());

        return convertView;

        /*

    final Session session = getItem(position);
    final int _id = session.getId();
    final String time = session.getTime();
    final String duration = session.getDuration();

    ((TextView)view.findViewById(R.id.session_time)).

    setText(time);

    ((TextView)view.findViewById(R.id.session_duration)).

    setText(duration);

    return view;*/
    }

/*
    public void setData(List<Session> sessions) {
        clear();
        if (sessions != null) {
            for (Session session : sessions) {
                add(session);
            }
        }
    } */
}
