package com.varunb.meditationminder;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by rajeevbansal on 5/28/15.
 */
public class SessionsListFragment extends Fragment {
    private static final String LOG_CAT = SessionsListFragment.class.getSimpleName();
    private static final String ARG_SECTION_NUMBER = "section_number";
    private SessionsCustomAdapter mAdapter;
    private static final int LOADER_ID = 1;
    private static Context mContext;
    //private ContentResolver mContentResolver;
    private ArrayList<Session> mSessions;


    public static SessionsListFragment newInstance(Context context) {
        mContext = context;
        SessionsListFragment fragment = new SessionsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SessionsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(com.varunb.meditationminder.R.layout.fragment_sessions_list, container, false);
        DatabaseHelper db = new DatabaseHelper(mContext);

        // THIS IS JUST TO CHECK IF IT WORKS
        // TODO: remove when you make manual add functionality
        /*
        Session test1 = new Session("June 25", "15");
        Session test2 = new Session("June 26", "20");
        Session test3 = new Session("June 27", "25");
        db.addSession(test1);
        db.addSession(test2);
        db.addSession(test3);
*/

        ArrayList<Session> sessionsArray = db.getAllSessions();
        //Log.d("+++all sessions", sessionsArray.toString());
        //Log.d("+++session[0].date", sessionsArray.get(0).getDate());
        //Log.d("+++session[0].dur", sessionsArray.get(0).getDuration());

        SessionsCustomAdapter adapter = new SessionsCustomAdapter(mContext, sessionsArray);
        ListView listView = (ListView) rootView.findViewById(com.varunb.meditationminder.R.id.sessions_list);
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    /*
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mContentResolver = getActivity().getContentResolver();
        mAdapter = new SessionsCustomAdapter(getActivity());
        setEmptyText("No sessions so far!");
        setListAdapter(mAdapter);
        setListShown(false);

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Session>> onCreateLoader(int id, Bundle args) {
        mContentResolver = getActivity().getContentResolver();
        return new SessionsListLoader(getActivity(), SessionsContract.URI_TABLE, mContentResolver);
    }

    @Override
    public void onLoadFinished(Loader<List<Session>> loader, List<Session> sessions) {
        mAdapter.setData(sessions);
        mSessions = sessions;
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Session>> loader) {
        mAdapter.setData(null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    */

}
