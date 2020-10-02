package com.example.glucosetrackerapp2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editUserName, editPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editUserName = (EditText) findViewById(R.id.editText_UserName);
        editPassword = (EditText) findViewById(R.id.editText_Password);
        btnLogin = (Button) findViewById(R.id.btn_Login);
        Login();
    }

    public void Login() {
        btnLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        if(editUserName.getText().toString().equals("admin") && editPassword.getText().toString().equals("admin"))
                        {
                            //Intent intent = new Intent(this, MainActivity.class);
                            //startActivity(intent);
                        }

                        else
                        {
                            //Toast.makeText(MainActivity.this, "Incorrect user/password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}