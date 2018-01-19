package info.firozansari.android_sqlite_examples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBOpenHelper databaseHelper = new DBOpenHelper(this , DBOpenHelper.DB_NAME , null , DBOpenHelper.version);
        databaseHelper.insertIntoStudent("100" , "Lionel Messi");
        //Similarly if we delete any record the trigger get fired:e.g
        //databaseHelper.deleteFromStudent("100");
    }
}
