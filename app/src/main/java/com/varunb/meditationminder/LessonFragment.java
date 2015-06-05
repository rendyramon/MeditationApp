package com.varunb.meditationminder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        //pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        //isLessonOn = pref.getBoolean(IS_LESSON_ON, true);

        //final DatabaseHelper db = new DatabaseHelper(mContext);
        //LessonData lessonData = db.getLessonsData();
        //isLessonOn = lessonData.ismIsCourseInProgress();


        rootView = inflater.inflate(R.layout.fragment_lesson_opener, container, false);

        Button btn_lesson1 = (Button) rootView.findViewById(R.id.btn_lesson1);
        Button btn_lesson2 = (Button) rootView.findViewById(R.id.btn_lesson2);
        Button btn_lesson3 = (Button) rootView.findViewById(R.id.btn_lesson3);
        Button btn_lesson4 = (Button) rootView.findViewById(R.id.btn_lesson4);
        Button btn_lesson5 = (Button) rootView.findViewById(R.id.btn_lesson5);
        Button btn_lesson6 = (Button) rootView.findViewById(R.id.btn_lesson6);
        Button btn_lesson7 = (Button) rootView.findViewById(R.id.btn_lesson7);
        Button btn_lesson8 = (Button) rootView.findViewById(R.id.btn_lesson8);
        Button btn_lesson9 = (Button) rootView.findViewById(R.id.btn_lesson9);
        Button btn_lesson10 = (Button) rootView.findViewById(R.id.btn_lesson10);

        btn_lesson1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(com.varunb.meditationminder.R.id.container, LessonContentFragment.newInstance(mContext, 1))
                        .addToBackStack(null)
                        .commit();
            }
        });

        btn_lesson2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(com.varunb.meditationminder.R.id.container, LessonContentFragment.newInstance(mContext, 2))
                        .addToBackStack(null)
                        .commit();
            }
        });

        btn_lesson3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(com.varunb.meditationminder.R.id.container, LessonContentFragment.newInstance(mContext, 3))
                        .addToBackStack(null)
                        .commit();
            }
        });

        btn_lesson4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(com.varunb.meditationminder.R.id.container, LessonContentFragment.newInstance(mContext, 4))
                        .addToBackStack(null)
                        .commit();
            }
        });

        btn_lesson5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(com.varunb.meditationminder.R.id.container, LessonContentFragment.newInstance(mContext, 5))
                        .addToBackStack(null)
                        .commit();
            }
        });

        btn_lesson6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(com.varunb.meditationminder.R.id.container, LessonContentFragment.newInstance(mContext, 6))
                        .addToBackStack(null)
                        .commit();
            }
        });

        btn_lesson7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(com.varunb.meditationminder.R.id.container, LessonContentFragment.newInstance(mContext, 7))
                        .addToBackStack(null)
                        .commit();
            }
        });

        btn_lesson8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(com.varunb.meditationminder.R.id.container, LessonContentFragment.newInstance(mContext, 8))
                        .addToBackStack(null)
                        .commit();
            }
        });

        btn_lesson9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(com.varunb.meditationminder.R.id.container, LessonContentFragment.newInstance(mContext, 9))
                        .addToBackStack(null)
                        .commit();
            }
        });

        btn_lesson10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(com.varunb.meditationminder.R.id.container, LessonContentFragment.newInstance(mContext, 10))
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;

    }
}