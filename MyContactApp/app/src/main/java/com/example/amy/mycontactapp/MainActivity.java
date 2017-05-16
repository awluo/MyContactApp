package com.example.amy.mycontactapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editNumber;
    EditText editAge;
    Button btnAddData;
    Button showData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        //Add the layout vars
        editName = (EditText) findViewById(R.id.editText_Name);
        editNumber = (EditText) findViewById(R.id.editText_Number);
        editAge = (EditText) findViewById(R.id.editText_Age);
    }

    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString(),
                editNumber.getText().toString(),
                editAge.getText().toString());

        if(isInserted == true){
            Log.d("MyContact", "Success inserting data");
            //Insert Toast message here...
            Context context = getApplicationContext();
            CharSequence text = "Data inserted!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            Log.d("MyContact", "Failure inserting data");
            //Insert Toast message here...
            Context context = getApplicationContext();
            CharSequence text = "Failure inserting data!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void viewData(View v){
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            //output message using Log.d and Toast
            return;
        }

        StringBuffer buffer = new StringBuffer();
        //set up a loop with Cursor(res) using moveToNext
        //append each COL to the buffer
        //display message using showMessage
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        //AlertDialog.Builder

    }
}
