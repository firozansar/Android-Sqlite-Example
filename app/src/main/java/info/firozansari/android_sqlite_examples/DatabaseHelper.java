package info.firozansari.android_sqlite_examples;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by topcashback on 19/01/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    static final public String DB_NAME = "trigger_demo";
    static final public int version =1;
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE student (sid INTEGER PRIMARY KEY, sname TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE canteen (cid INTEGER PRIMARY KEY, sid TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE library (lid INTEGER PRIMARY KEY, sid TEXT)");
        sqLiteDatabase.execSQL(insertRecordTrigger()); // create trigger
        sqLiteDatabase.execSQL(deleteRecordTrigger()); // delete trigger
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP table student");
        sqLiteDatabase.execSQL("DROP table student");
        sqLiteDatabase.execSQL("DROP table student");
        sqLiteDatabase.execSQL("DROP trigger add_student"); // Drop trigger
        sqLiteDatabase.execSQL("DROP trigger delete_student"); // Drop trigger
        onCreate(sqLiteDatabase);
    }
    public String deleteRecordTrigger(){
        String deleteRecord = "CREATE TRIGGER if not exists delete_student " +
                " AFTER DELETE " +
                " ON[student] " +
                " for each row " +
                " BEGIN " +
                "  delete from library where sid = old.sid; " +
                "  delete from library where sid = old.sid; " +
                " END; ";
        return deleteRecord;
    }
    public String insertRecordTrigger(){
        String insertRecord = "CREATE TRIGGER if not exists add_student "
                + " AFTER INSERT "
                + " ON[student] "
                + " for each row "
                + " BEGIN "
                + " insert into library values (2 , new.sid );"
                + " insert into canteen values (3 , new.sid);"
                + " END;";
        return insertRecord;
    }
    /**
     * Insert new student record into student table which eventually fire trigger and insert record into canteen and library
     */
    public void insertIntoStudent(String sid , String sname){
        ContentValues insertValues = new ContentValues();
        insertValues.put("sid", sid);
        insertValues.put("sname", sname);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("student", null, insertValues);
    }
    /**
     * Delete student record from student table which eventually fire trigger and delete record from canteen and library
     */
    public void deleteFromStudent(String sid){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from student where sid = '"+sid+"'");
    }


}