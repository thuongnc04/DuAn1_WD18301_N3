package com.example.duan1_wd18301_n3.Login_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_wd18301_n3.DAO.RegisterDAO;
import com.example.duan1_wd18301_n3.R;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    EditText etFullName;
    EditText etEmail;
    EditText etSdt;

    EditText etPassword;
    EditText etUsername;
    RegisterDAO registerDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerDAO = new RegisterDAO(this);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etFullName = (EditText) findViewById(R.id.etFullName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSdt = (EditText) findViewById(R.id.etSdt);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String name = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String phone = etSdt.getText().toString().trim();

                if (registerDAO.register(name, email, username, password, phone)) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đăng ký tài khoản thành công.",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Đăng ký tài khoản không thành công.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}