package com.varunb.drawertest;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by rajeevbansal on 5/27/15.
 */
public class SessionsContract {
    interface SessionsColumns {
        String SESSION_ID = "_id";
        String SESSION_DATE = "session_date";
        String SESSION_DURATION = "session_duration";
        String[] SESSION_COLUMNS = {SESSION_ID, SESSION_DATE, SESSION_DURATION};
    }

    public static final String CONTENT_AUTHORITY = "com.varunb.drawertest.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_SESSIONS = "sessions";
    public static final Uri URI_TABLE = Uri.parse(BASE_CONTENT_URI.toString() + "/" + PATH_SESSIONS);

    public static final String[] TOP_LEVEL_PATHS = {
            PATH_SESSIONS
    };

    public static class Sessions implements SessionsColumns, BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_SESSIONS).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + ".sessions";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + ".sessions";

        public static Uri buildSessionsUri(String sessionId){
            return CONTENT_URI.buildUpon().appendEncodedPath(sessionId).build();
        }

        public static String getSessionId(Uri uri){
            return uri.getPathSegments().get(1);
        }
    }
}
