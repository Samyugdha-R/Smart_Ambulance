package com.example.traffic_ambulance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class useralert extends AppCompatActivity {
    DatabaseHelper db;
    String uname,usertyp;
    ArrayList<HashMap<String, String>> userList;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    SQLiteHelper sqLiteHelper;
    String EmailHolder="Police";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useralert);
        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        uname = intent.getStringExtra("message");
        usertyp=intent.getStringExtra("usertype");
        sqLiteHelper = new SQLiteHelper(this);
        userList = GetallUsers();
// Get User Details

        final ListView lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(useralert.this, userList, R.layout.list_row,new String[]{"name","email","mblnum"}, new int[]{R.id.name, R.id.designation, R.id.location});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                String selectedItem = lv.getItemAtPosition(position).toString();
                // Going to Dashboard activity after login success message.
                Log.d("myTag",selectedItem);
                Intent intent = new Intent(useralert.this, police_alert.class);

                // Sending Email to Dashboard Activity using intent.
                intent.putExtra("message", selectedItem);

                startActivity(intent);
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
}
