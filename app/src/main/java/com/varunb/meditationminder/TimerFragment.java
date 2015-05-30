package com.varunb.meditationminder;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TimerFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int SEC_IN_MIN = 60;
    //private static final int SECONDS_INTERVAL_TO_VIBRATE = 10;
    private static Vibrator vibrator;
    private static Context mContext;
    private static int default_length;
    private static int interval_length;
    private static boolean exceededTarget;
    private final String restingTitle = "Start a session timer";
    private final String activeTitle = "Session in progress";
    private int current_notification = 0;

    private NotificationManager mNotificationManager;

    private MyCountDownTimer myCountDownTimer;
    private long sec_elapsed;
    private NumberPicker number_picker;
    //private TextView picker_output;
    private Button btnBegin;
    private Button btnEnd;
    private Button btnCancel;
    private TextView interval_text;
    private TextView counter_text;
    private TextView timer_title;


    // TODO: use the above boolean to record when we flip from countdown to count up

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TimerFragment newInstance(int length, int intervals, Context context) {
        mContext = context;
        default_length = length;
        interval_length = intervals;
        TimerFragment fragment = new TimerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 0);
        fragment.setArguments(args);
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        return fragment;
    }

    public TimerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(com.varunb.meditationminder.R.layout.fragment_timer, container, false);

        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        timer_title = (TextView) rootView.findViewById(com.varunb.meditationminder.R.id.timer_title);
        number_picker = (NumberPicker) rootView.findViewById(com.varunb.meditationminder.R.id.pickerTime);
        counter_text = (TextView) rootView.findViewById(com.varunb.meditationminder.R.id.counter_text);
        interval_text = (TextView) rootView.findViewById(com.varunb.meditationminder.R.id.interval_text);

        btnBegin = (Button) rootView.findViewById(com.varunb.meditationminder.R.id.btnBegin);
        btnEnd = (Button) rootView.findViewById(com.varunb.meditationminder.R.id.btnEnd);
        btnCancel = (Button) rootView.findViewById(com.varunb.meditationminder.R.id.btnCancel);

        btnBegin.setClickable(true);
        btnEnd.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);

        number_picker.setMinValue(1);
        number_picker.setMaxValue(60);
        number_picker.setValue(default_length);

        timer_title.setText(restingTitle);
        interval_text.setText("Your phone will buzz every " + interval_length + " minutes.");

        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // picker_output.setText(String.valueOf(number_picker.getValue()));
                btnBegin.setClickable(false);
                btnBegin.setVisibility(View.GONE);
                btnEnd.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
                number_picker.setVisibility(View.GONE);
                counter_text.setVisibility(View.VISIBLE);
                counter_text.setText(Integer.toString(number_picker.getValue()));
                timer_title.setText(activeTitle);

                myCountDownTimer = new MyCountDownTimer(number_picker.getValue() * SEC_IN_MIN * 1000, 1000);
                myCountDownTimer.start();

                Toast toast = Toast.makeText(mContext, "Begin session!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            // TODO: complete listener
            public void onClick(View v) {
                myCountDownTimer.cancel();
                number_picker.setVisibility(View.VISIBLE);
                counter_text.setVisibility(View.GONE);
                btnBegin.setVisibility(View.VISIBLE);
                btnBegin.setClickable(true);
                btnEnd.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                timer_title.setText(restingTitle);

                Toast toast = Toast.makeText(mContext, "Session timer canceled", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            // TODO: complete listener
            public void onClick(View v) {
                myCountDownTimer.cancel();
                recordTime();

                number_picker.setVisibility(View.VISIBLE);
                counter_text.setVisibility(View.GONE);
                btnBegin.setVisibility(View.VISIBLE);
                btnBegin.setClickable(true);
                btnEnd.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                timer_title.setText(restingTitle);

                Toast toast = Toast.makeText(mContext, "Session ended early", Toast.LENGTH_SHORT);
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

    private void recordTime() {
        DatabaseHelper db = new DatabaseHelper(mContext);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("E, LLL d, yyyy");
        String sessionDate = sdf.format(c.getTime());

        long longElapsed = sec_elapsed / 60;
        Integer intElapsed = (int) (long) longElapsed;

        String elapsed_time = intElapsed + " minutes";

        Session session = new Session(sessionDate, elapsed_time, c.getTimeInMillis(), intElapsed);
        db.addSession(session);
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onTick(long millisUntilFinished) {
            sec_elapsed = (number_picker.getValue() * SEC_IN_MIN) - (millisUntilFinished / 1000);

            long totalSecsUntilFinished = millisUntilFinished / 1000;
            long minUntilFinished = totalSecsUntilFinished / SEC_IN_MIN;
            long remainingSecs = totalSecsUntilFinished % SEC_IN_MIN;
            String minString;
            String secString;
            if (minUntilFinished < 10) {
                minString = "0" + minUntilFinished;
            } else {
                minString = String.valueOf(minUntilFinished);
            }
            if(remainingSecs < 10){
                secString = "0" + remainingSecs;
            } else {
                secString = String.valueOf(remainingSecs);
            }
            String counterContents = minString + " : " + secString;

            counter_text.setText(counterContents);

            if (sec_elapsed % (interval_length * SEC_IN_MIN) == 0) {
                //vibrator.vibrate(500);
                long minElapsed = number_picker.getValue() - minUntilFinished;
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(mContext)
                                .setSmallIcon(com.varunb.meditationminder.R.drawable.ic_notify)
                                .setContentTitle("This is your " + minElapsed + " minute reminder!")
                                .setContentText(minUntilFinished + " minutes to go.")
                                .setVibrate(new long[]{0, 500});

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(mContext, MainActivity.class);

                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(MainActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                /*PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );*/

                PendingIntent resultPendingIntent = PendingIntent.getActivity(
                        mContext.getApplicationContext(),
                        0,
                        new Intent(), // add this
                        PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.setContentIntent(resultPendingIntent);

                Notification notification = mBuilder.build();
                notification.defaults |= Notification.DEFAULT_VIBRATE;


                // mId allows you to update the notification later on.
                mNotificationManager.cancel(current_notification);
                current_notification++;
                mNotificationManager.notify(current_notification, notification);
            }
        }

        public void onFinish() {
            long[] pattern = {0, 500, 100, 500, 100, 500};
            // vibrator.vibrate(pattern, -1);

            recordTime();

            Toast toast = Toast.makeText(mContext, "Session finished!", Toast.LENGTH_LONG);
            toast.show();

            number_picker.setVisibility(View.VISIBLE);
            counter_text.setVisibility(View.GONE);
            btnBegin.setClickable(true);
            btnEnd.setClickable(false);
            btnBegin.setVisibility(View.VISIBLE);
            btnEnd.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.VISIBLE);
            timer_title.setText(restingTitle);


            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(mContext)
                            .setSmallIcon(com.varunb.meditationminder.R.drawable.ic_notify)
                            .setContentTitle("Session finished")
                            .setContentText("Be sure to meditate again tomorrow!")
                            .setVibrate(pattern);

            // Creates an explicit intent for an Activity in your app
            Intent resultIntent = new Intent(mContext, MainActivity.class);

            // The stack builder object will contain an artificial back stack for the
            // started Activity.
            // This ensures that navigating backward from the Activity leads out of
            // your application to the Home screen.
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
            // Adds the back stack for the Intent (but not the Intent itself)
            stackBuilder.addParentStack(MainActivity.class);
            // Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
                /*PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );*/

            PendingIntent resultPendingIntent = PendingIntent.getActivity(
                    mContext.getApplicationContext(),
                    0,
                    new Intent(), // add this
                    PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder.setContentIntent(resultPendingIntent);

            Notification notification = mBuilder.build();
            notification.defaults |= Notification.DEFAULT_VIBRATE;


            // mId allows you to update the notification later on.
            mNotificationManager.cancel(current_notification);
            current_notification++;
            mNotificationManager.notify(current_notification, notification);

            // TODO: start the counting up and change the buttons displayed
        }
    }

}

