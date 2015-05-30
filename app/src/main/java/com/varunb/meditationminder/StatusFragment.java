package com.varunb.meditationminder;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by rajeevbansal on 5/30/15.
 */
public class StatusFragment extends Fragment {

    private static Context mContext;
    private static int mTarget;

    public static StatusFragment newInstance(int target, Context context) {
        mContext = context;
        mTarget = target;
        StatusFragment fragment = new StatusFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public StatusFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_status_fragment, container, false);

        final TextView perText = (TextView) rootView.findViewById(R.id.today_percent_text);
        final TextView minText = (TextView) rootView.findViewById(R.id.today_mins_text);
        final ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        DatabaseHelper db = new DatabaseHelper(mContext);
        int todayTime = db.getTodayTimes();
        Double dTodayPercent = todayTime * 1.0 / mTarget * 100;
        int todayPercent = dTodayPercent.intValue();

        progressBar.setProgress(todayPercent);
        perText.setText(todayPercent + "% to target");
        minText.setText(todayTime + " minutes today");

        return rootView;

    }
}