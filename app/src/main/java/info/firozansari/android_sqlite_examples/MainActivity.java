package info.firozansari.android_sqlite_examples;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper databaseHelper = new DatabaseHelper(this , DatabaseHelper.DB_NAME , null , DatabaseHelper.version);
        databaseHelper.insertIntoStudent("100" , "Lionel Messi");
        //Similarly if we delete any record the trigger get fired:e.g
        //databaseHelper.deleteFromStudent("100");
    }

    protected void onResume(){
        super.onResume();
        showDebugDBAddressLogToast(this);
    }



    public static void showDebugDBAddressLogToast(Context context) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                Toast.makeText(context, (String) value, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.d("excep : %s ", e.getMessage());
            }
        }
    }

}
