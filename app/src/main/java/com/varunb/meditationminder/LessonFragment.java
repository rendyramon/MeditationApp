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
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rajeevbansal on 6/4/15.
 */
public class LessonFragment extends Fragment {

    private static Context mContext;
    private boolean isLessonOn;
    private int lastLessonFinished;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private static final String IS_LESSON_ON = "isLessonOn";
    private static final String LAST_LESSON_FINISHED = "lastLessonFinished";
    private View rootView;
    private FragmentManager fragmentManager;


    public static LessonFragment newInstance(Context context) {
        mContext = context;
        LessonFragment fragment = new LessonFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public LessonFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getFragmentManager();
        pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        isLessonOn = pref.getBoolean(IS_LESSON_ON, true);

        if (isLessonOn) {
            rootView = inflater.inflate(com.varunb.meditationminder.R.layout.fragment_lesson, container, false);
            TextView text_day_on = (TextView) rootView.findViewById(R.id.text_day_on);
            Button btnEndCourseEarly = (Button) rootView.findViewById(R.id.btnEndCourseEarly);
            Button btnBeginNextLesson = (Button) rootView.findViewById(R.id.btnBeginNextLesson);

            btnEndCourseEarly.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    editor = pref.edit();
                    editor.putBoolean(IS_LESSON_ON, false);
                    editor.apply();
                    editor.putInt(LAST_LESSON_FINISHED, 0);
                    editor.apply();
                    fragmentManager.beginTransaction()
                            .replace(com.varunb.meditationminder.R.id.container, LessonFragment.newInstance(mContext))
                            .addToBackStack(null)
                            .commit();
                }
            });

            btnBeginNextLesson.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    fragmentManager.beginTransaction()
                            .replace(com.varunb.meditationminder.R.id.container, LessonContentFragment.newInstance(mContext))
                            .addToBackStack(null)
                            .commit();
                }
            });


        } else {
            rootView = inflater.inflate(R.layout.fragment_lesson_opener, container, false);
            Button btn_begin_course = (Button) rootView.findViewById(R.id.btn_begin_course);

            btn_begin_course.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    editor = pref.edit();
                    editor.putBoolean(IS_LESSON_ON, true);
                    editor.apply();
                    editor.putInt(LAST_LESSON_FINISHED, 1);
                    editor.apply();
                    fragmentManager.beginTransaction()
                            .replace(com.varunb.meditationminder.R.id.container, LessonFragment.newInstance(mContext))
                            .addToBackStack(null)
                            .commit();
                }
            });
        }

        return rootView;

    }
}