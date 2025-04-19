package com.example.rdvmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase database;

    // Table Name
    public static final String TABLE_NAME = "RDV";
    // Table columns
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String PERSON = "person";
    public static final String PHONE = "phone";

    // Database Information
    static final String DB_NAME = "RDV.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT NOT NULL, "
            + DATE + " TEXT NOT NULL, "
            + TIME + " TEXT NOT NULL, "
            + PERSON + " TEXT NOT NULL, "
            + PHONE + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION) ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void add(RDV rdv){
        ContentValues contentValues= new ContentValues();
        contentValues.put(TITLE, rdv.getTitle());
        contentValues.put(DATE, rdv.getDate());
        contentValues.put(TIME, rdv.getTime());
        contentValues.put(PERSON, rdv.getPerson());
        contentValues.put(PHONE, rdv.getPhone());
        database.insert(TABLE_NAME,null,contentValues);
    }

    public Cursor getAllRDV() {
        String[] projection = {_ID, TITLE, DATE, TIME, PERSON, PHONE};
        return database.query(TABLE_NAME,projection,null,null,null,null,null,null);
    }

    public void delete(long _id)
    {
        database.delete(TABLE_NAME, _ID + "=" + _id, null);
    }

    public int update(RDV rdv) {
        int _id = Integer.parseInt(rdv.getId());
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, rdv.getTitle());
        contentValues.put(DATE, rdv.getDate());
        contentValues.put(TIME, rdv.getTime());
        contentValues.put(PERSON, rdv.getPerson());
        contentValues.put(PHONE, rdv.getPhone());
        return database.update(TABLE_NAME, contentValues, _ID + " = " + _id, null);
    }
}