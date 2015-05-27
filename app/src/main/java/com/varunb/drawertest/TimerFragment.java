package com.varunb.drawertest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


public class TimerFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int SEC_IN_MIN = 60;
    private static final int SECONDS_INTERVAL_TO_VIBRATE = 10;
    private static Vibrator vibrator;
    private static Context mContext;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TimerFragment newInstance(int sectionNumber, Context context) {
        mContext = context;
        TimerFragment fragment = new TimerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        return fragment;
    }

    public TimerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timer, container, false);

        final NumberPicker number_picker = (NumberPicker) rootView.findViewById(R.id.pickerTime);
        final TextView picker_output = (TextView) rootView.findViewById(R.id.picker_output);
        final Button btnBegin = (Button) rootView.findViewById(R.id.btnBegin);
        final Button btnEnd = (Button) rootView.findViewById(R.id.btnEnd);
        final Button btnCancel = (Button) rootView.findViewById(R.id.btnCancel);

        btnBegin.setClickable(true);
        btnEnd.setVisibility(View.INVISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);

        number_picker.setMinValue(1);
        number_picker.setMaxValue(60);

        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // picker_output.setText(String.valueOf(number_picker.getValue()));
                btnBegin.setClickable(false);
                btnEnd.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);

                new CountDownTimer(number_picker.getValue() * SEC_IN_MIN * 1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        picker_output.setText("seconds remaining: " + millisUntilFinished / 1000);

                        if (millisUntilFinished / 1000 % SECONDS_INTERVAL_TO_VIBRATE == 0) {
                            long[] pattern = {0, 500, 50, 500};
                            vibrator.vibrate(pattern, -1);
                        }
                    }

                    public void onFinish() {
                        picker_output.setText("done!");
                        vibrator.vibrate(1000);
                        btnBegin.setClickable(true);
                        btnEnd.setClickable(false);
                    }
                }.start();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            // TODO: complete listener
            public void onClick(View v) {
                Toast toast = Toast.makeText(mContext, "Toast from cancel", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            // TODO: complete listener
            public void onClick(View v) {
                Toast toast = Toast.makeText(mContext, "Toast from end", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }


}
