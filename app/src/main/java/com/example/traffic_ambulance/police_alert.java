package com.example.traffic_ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;

public class police_alert extends AppCompatActivity {
    DatabaseHelper db;
    String uname,usertyp,latlon;
    ArrayList<HashMap<String, String>> userList;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    SQLiteHelper sqLiteHelper;
    String EmailHolder="Ambulance";
    String bankbalresult;
    String[] splits ;
    String[] namestr ;
    String query;
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_alert);
        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        uname = intent.getStringExtra("message");
        usertyp=intent.getStringExtra("usertype");
        sqLiteHelper = new SQLiteHelper(this);
        userList = GetallUsers();
// Get User Details

        final ListView lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(police_alert.this, userList, R.layout.list_row,new String[]{"name","email","mblnum"}, new int[]{R.id.name, R.id.designation, R.id.location});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                String selectedItem = lv.getItemAtPosition(position).toString();
                // Going to Dashboard activity after login success message.
                Log.d("myTag",selectedItem);
                //Intent intent = new Intent(police_alert.this, police_alert.class);

                // Sending Email to Dashboard Activity using intent.
               // intent.putExtra("message", selectedItem);

              //  startActivity(intent);
                splits = selectedItem.split("=");
                namestr = splits[1].split(",");
                latlon=GetalatUsers(namestr[0]);
                Toast.makeText(police_alert.this, latlon, Toast.LENGTH_LONG).show();
                String url = "https://maps.google.com/?q="+latlon;
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                // Verify that the intent will resolve to an activity
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Here we use an intent without a Chooser unlike the next example
                    startActivity(intent);
                }

            }
        });
    }
    // Get User Details
    public ArrayList<HashMap<String, String>> GetallUsers(){
        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
        Cursor cursor ;
        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_5_usertyp + "=?", new String[]{EmailHolder}, null, null, null);
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * from UserTable where usertyp='Ambulance'";
        Log.d("myTag",query);

        while (cursor.moveToNext()){
            HashMap<String, String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex("name")));
            user.put("email",cursor.getString(cursor.getColumnIndex("email")));
            user.put("mblnum",cursor.getString(cursor.getColumnIndex("mblnum")));
            userList.add(user);
        }
        return  userList;
    }
    // Get User Details
    public String GetalatUsers(String names) {
        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
        Cursor cursor;
        query = "SELECT * from UserTable where name='" + names + "'";
        Log.d("myTag", query);


        cursor = sqLiteDatabaseObj.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                bankbalresult = cursor.getString(6);
            } while (cursor.moveToNext());
        }

        return bankbalresult;
    }

        }





