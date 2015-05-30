package com.varunb.meditationminder;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;

/**
 * Created by rajeevbansal on 5/28/15.
 */
public class ManualFragment extends Fragment {

    private static Context mContext;

    public static ManualFragment newInstance(Context context) {
        mContext = context;
        ManualFragment fragment = new ManualFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ManualFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(com.varunb.meditationminder.R.layout.fragment_manual_add, container, false);

        //final EditText editDate = (EditText) rootView.findViewById(R.id.edit_date);
        final EditText editDur = (EditText) rootView.findViewById(com.varunb.meditationminder.R.id.edit_duration);
        final Button btnSave = (Button) rootView.findViewById(com.varunb.meditationminder.R.id.btn_save);
        final DatePicker datePicker = (DatePicker) rootView.findViewById(com.varunb.meditationminder.R.id.choose_date);

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(mContext);

                CalendarView calendarView = datePicker.getCalendarView();
                SimpleDateFormat sdf = new SimpleDateFormat("E, LLL d, yyyy");
                String sessionDate = sdf.format(calendarView.getDate());

                String elapsed_time = editDur.getText().toString() + " minutes";

                Session session = new Session(sessionDate, elapsed_time, calendarView.getDate(), Integer.parseInt(editDur.getText().toString()));
                db.addSession(session);

                Toast toast = Toast.makeText(mContext, "Saved successfully!", Toast.LENGTH_LONG);
                toast.show();

                //editDate.setText("");
                editDur.setText("");
            }
        });

        return rootView;

    }
}