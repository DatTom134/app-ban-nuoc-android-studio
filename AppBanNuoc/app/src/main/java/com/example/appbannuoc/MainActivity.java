package com.example.appbannuoc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if (username.equals("admin") && password.equals("admin"))
                {
                    Intent intent = new Intent(MainActivity.this, Home_Activity.class);
//                    Intent intent = Home_Activity.makeIntent(MainActivity.this);
                    startActivity(intent);
                }
                else if (username.equals("user") && password.equals("123123"))
                {

                }
                else
                {
                    Toast.makeText(MainActivity.this, "tài khoản hoặc mật khẩu sai, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                    edtPassword.setText("");
                }

            }
        });
    }
}