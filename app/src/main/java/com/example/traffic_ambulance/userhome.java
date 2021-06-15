package com.example.traffic_ambulance;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class userhome extends AppCompatActivity {
    Button ambulance ;
    String uname;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    SQLiteHelper sqLiteHelper;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    int PERMISSION_ID = 44;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);

        ambulance = (Button)findViewById(R.id.button2);
        sqLiteHelper = new SQLiteHelper(this);
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sqLiteDatabaseObj = sqLiteHelper.getReadableDatabase();
                // SQLite query to insert data into table.
                Cursor resultSet = sqLiteDatabaseObj.rawQuery("Select * from UserTable",null);
                resultSet.moveToFirst();
                String mblno = resultSet.getString(4);
                Cursor name1 = sqLiteDatabaseObj.rawQuery("Select * from UserTable",null);
                name1.moveToFirst();
                String user = name1.getString(1);
                // Executing query.j0t


                // Closing SQLite database object.
                sqLiteDatabaseObj.close();
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(mblno, null, "Please Clear Traffic Ambulance is Nearby you- Mithra ", null, null);
                Toast.makeText(userhome.this, "Requested to clear Traffic-"+mblno, Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
        else {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("7299944416", null, "Checking", null, null);
                Toast.makeText(getApplicationContext(), "SMS sent.",
                        Toast.LENGTH_LONG).show();
            }
        }

    }
}