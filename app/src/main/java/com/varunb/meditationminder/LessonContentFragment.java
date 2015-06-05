package com.varunb.meditationminder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rajeevbansal on 6/5/15.
 */
public class LessonContentFragment extends Fragment {

    private static Context mContext;
    private boolean isLessonOn;
    private int lastLessonFinished;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private static final String IS_LESSON_ON = "isLessonOn";
    private static final String LAST_LESSON_FINISHED = "lastLessonFinished";
    private View rootView;
    private FragmentManager fragmentManager;


    public static LessonContentFragment newInstance(Context context) {
        mContext = context;
        LessonContentFragment fragment = new LessonContentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public LessonContentFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getFragmentManager();
        pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        isLessonOn = pref.getBoolean(IS_LESSON_ON, true);

        String[] lesson_titles = {mContext.getResources().getString(R.string.day1_title),
                mContext.getResources().getString(R.string.day2_title),
                mContext.getResources().getString(R.string.day3_title),
                mContext.getResources().getString(R.string.day4_title),
                mContext.getResources().getString(R.string.day5_title),
                mContext.getResources().getString(R.string.day6_title),
                mContext.getResources().getString(R.string.day7_title),
                mContext.getResources().getString(R.string.day8_title),
                mContext.getResources().getString(R.string.day9_title),
                mContext.getResources().getString(R.string.day10_title)
        };

        String[] lesson_contents = {"Welcome to the mindfulness course! In just ten days, we will get you meditating on your own.\n\nToday, we will begin with a deceptively simple exercise: noticing ourselves.\n\nSit comfortably in a quiet place and close your eyes. Notice what it feels like to be in your body, the physical sensations. Just let each sensation come and go. Try not to linger on any one. Just notice it, feel it, then let it go. Treat pleasant and unpleasant sensations the same: notice, feel, and let go.\n\nIf you forget to let go of a sensation and catch yourself lingering on it, it\'s fine. Catching yourself is what has value. Be grateful you caught the lingering, and let go. After five minutes, the timer will buzz; feel free to continue after for as long as you\'d like.",
                "Welcome back! If you found yesterday\'s exercise more challenging than anticipated, take comfort in knowing that most find it quite difficult. And also remember that in meditation, there is no end goal. Each time you catch your mind drifting and return, be satisfied: this process of catching and re-focusing attention, over and over again, is what gives us value from meditation.\n\nToday we will try to cultivate our mindfulness by noticing the environment around us. Find somewhere that is free of conversations and speech -- human voices are difficult to detach from -- but that is not completely quiet. Sit comfortably and close your eyes. Pretend your eyes are human satellite dishes, picking up every sound. Be aware of your surroundings, but not hyper-alert. Just sit and notice the sounds: don\'t try and label them, or assign judgments or preferences to them. Don\'t try and figure out what each sound is. Just notice, and let it pass. When it is quiet, don\'t search for sounds. Just let them come to you.\n\n" +
                        "If you forget to let go of a sound and catch yourself lingering on or thinking about it, it\'s fine. Catching yourself is what has value. Be grateful you caught the lingering, and let go. After five minutes, the timer will buzz; feel free to continue after for as long as you\'d like.",
                mContext.getResources().getString(R.string.day3_title),
                mContext.getResources().getString(R.string.day4_title),
                mContext.getResources().getString(R.string.day5_title),
                mContext.getResources().getString(R.string.day6_title),
                mContext.getResources().getString(R.string.day7_title),
                mContext.getResources().getString(R.string.day8_title),
                mContext.getResources().getString(R.string.day9_title),
                mContext.getResources().getString(R.string.day10_title)
        };

        // TODO: finish updating the lesson contents



        rootView = inflater.inflate(com.varunb.meditationminder.R.layout.fragment_lesson_content, container, false);
        TextView text_lesson_title = (TextView) rootView.findViewById(R.id.text_lesson_title);
        TextView text_lesson_content = (TextView) rootView.findViewById(R.id.text_lesson_content);

        text_lesson_title.setText(lesson_titles[0]);
        text_lesson_content.setText(lesson_contents[0]);


        return rootView;

    }
}