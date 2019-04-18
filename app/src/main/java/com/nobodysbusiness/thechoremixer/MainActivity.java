package com.nobodysbusiness.thechoremixer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper choreDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        choreDB = new DatabaseHelper(this);
        boolean isInserted = choreDB.newChore("Sweep Floor");
        if (isInserted){
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Not Inserted", Toast.LENGTH_SHORT).show();
        }
        Cursor res = choreDB.getAllChore();
        if (res.getCount()!=0){
            while (res.moveToNext()){
                Toast.makeText(this, res.getString(1), Toast.LENGTH_SHORT).show();

            }
        }
    }
}
