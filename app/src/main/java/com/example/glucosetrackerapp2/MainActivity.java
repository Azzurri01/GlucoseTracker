package com.example.glucosetrackerapp2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editID, editWeight, editFood, editExercise, editBloodGlucose;
    Button btnAdd;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        editID = (EditText) findViewById(R.id.editText_UserName);
        editWeight = (EditText) findViewById(R.id.editText_Password);
        editFood = (EditText) findViewById(R.id.editText_Food);
        editExercise = (EditText) findViewById(R.id.editText_Exercise);
        editBloodGlucose = (EditText) findViewById(R.id.editText_BloodGlucose);
        btnAdd = (Button) findViewById(R.id.btn_Login);
        btnViewAll = (Button) findViewById(R.id.btn_ViewAll);
        btnUpdate = (Button) findViewById(R.id.btn_Update);
        btnDelete = (Button) findViewById(R.id.btn_Delete);
        Add();
        Update();
        ViewAll();
        Delete();
    }

    public void Add() {
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean Isinserted = myDb.insertData(
                                editWeight.getText().toString(),
                                editFood.getText().toString(),
                                editExercise.getText().toString(),
                                editBloodGlucose.getText().toString());

                        if(editWeight.getText().toString().isEmpty() || editFood.getText().toString().isEmpty() || editExercise.getText().toString().isEmpty() || editBloodGlucose.getText().toString().isEmpty())
                        {
                            Toast.makeText(MainActivity.this, "All fields must be populated", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            if (Isinserted == true) {
                                Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                            }
                            editID.setText("");
                            editWeight.setText("");
                            editFood.setText("");
                            editExercise.setText("");
                            editBloodGlucose.setText("");
                        }
                    }
                }
        );
    }

    public void Update()
    {
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean IsUpdate= myDb.updateData(editID.getText().toString(),editWeight.getText().toString(),editFood.getText().toString(),editExercise.getText().toString(),editBloodGlucose.getText().toString());
                        if(IsUpdate==true)
                        {
                            Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Data not updated", Toast.LENGTH_SHORT).show();
                        }
                        editID.setText("");
                        editWeight.setText("");
                        editFood.setText("");
                        editExercise.setText("");
                        editBloodGlucose.setText("");
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void ViewAll()
    {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=myDb.getAllData();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer= new StringBuffer();
                        while(res.moveToNext()) {
                            buffer.append("ID:"+res.getString(0)+"\n");
                            buffer.append("WEIGHT:"+res.getString(1)+"\n");
                            buffer.append("FOOD:"+res.getString(2)+"\n");
                            buffer.append("EXERCISE:"+res.getString(3)+"\n\n");
                            buffer.append("BLOOD GLUCOSE:"+res.getString(4)+"\n\n");
                        }
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void Delete()
    {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editID.getText().toString());
                        if (deletedRows > 0) {
                            Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Data not deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}