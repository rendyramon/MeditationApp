package com.varunb.meditationminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by rajeevbansal on 5/28/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sessions.db";
    private static final int DATABASE_VERSION = 2;

    interface Tables {
        String SESSIONS = "sessions";
    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "
                + Tables.SESSIONS + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SessionsContract.SessionsColumns.SESSION_DATE + " TEXT NOT NULL,"
                + SessionsContract.SessionsColumns.SESSION_DURATION + " TEXT NOT NULL)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int version = oldVersion;
        if (version == 1) {
            // add fields to DB without deleting existing data
            version = 2;
        }

        //if (version != DATABASE_VERSION) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.SESSIONS);
        this.onCreate(db);
        //}
    }


    public void addSession(Session session) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SessionsContract.SessionsColumns.SESSION_DATE, session.getDate());
        values.put(SessionsContract.SessionsColumns.SESSION_DURATION, session.getDuration());

        db.insert(Tables.SESSIONS, null, values);

        db.close();
    }

    public Session getSession(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Tables.SESSIONS, SessionsContract.SessionsColumns.SESSION_COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Session session = new Session();
        session.setId(Integer.parseInt(cursor.getString(0)));
        session.setDate(cursor.getString(1));
        session.setDuration(cursor.getString(2));

        cursor.close();
        return session;
    }

    public ArrayList<Session> getAllSessions() {
        ArrayList<Session> sessions = new ArrayList<Session>();

        String query = "SELECT * FROM " + Tables.SESSIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Session session = null;
        if (cursor.moveToFirst()) {
            do {
                session = new Session();
                session.setId(Integer.parseInt(cursor.getString(0)));
                session.setDate(cursor.getString(1));
                session.setDuration(cursor.getString(2));

                sessions.add(session);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return sessions;
    }

    public int updateSession(Session session) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SessionsContract.SessionsColumns.SESSION_DATE, session.getDate());
        values.put(SessionsContract.SessionsColumns.SESSION_DURATION, session.getDuration());

        int i = db.update(Tables.SESSIONS, values, SessionsContract.SessionsColumns.SESSION_ID + " = ?", new String[]{String.valueOf(session.getId())});

        db.close();

        return i;

    }

    public void deleteSession(Session session) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(Tables.SESSIONS, SessionsContract.SessionsColumns.SESSION_ID + " = ?", new String[]{String.valueOf(session.getId())});

        db.close();

    }


}
