package com.example.amy.mycontactapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
    EditText editSearch;
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
        editSearch = (EditText) findViewById(R.id.editText_Search);
    }

    public void addData(View v){
        boolean isInserted = false;
        if(!editName.getText().toString().equals("") &&
                !editNumber.getText().toString().equals("")&&
                !editAge.getText().toString().equals(""))
        {
             isInserted = myDb.insertData(editName.getText().toString(),
                    editNumber.getText().toString(),
                    editAge.getText().toString());
        }

        if(isInserted == true ){
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
        while(res.moveToNext())
        {
            for(int i=0; i<res.getColumnCount(); i++)
            {
                buffer.append(res.getColumnName(i) + ": " + res.getString(i)+ "\n");
            }
        }
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        //AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        AlertDialog d = builder.create();
        d.show();
    }

    public void searchData(View v)
    {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            //output message using Log.d and Toast
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            if(res.getString(1).toUpperCase().equals(editSearch.getText().toString().toUpperCase()))
            {
                for(int i=0; i<res.getColumnCount();i++)
                {
                    buffer.append(res.getColumnName(i) + ": " + res.getString(i) + "\n");
                }
                showMessage("Data", buffer.toString());
                return;
            }
        }
        buffer.append("Contact not found.");
        showMessage("Data", buffer.toString());

    }
}
