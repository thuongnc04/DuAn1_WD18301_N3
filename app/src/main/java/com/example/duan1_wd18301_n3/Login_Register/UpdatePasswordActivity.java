package com.example.duan1_wd18301_n3.Login_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_wd18301_n3.DAO.CustomerDAO;
import com.example.duan1_wd18301_n3.R;

public class UpdatePasswordActivity extends AppCompatActivity {
    Button btnVerify;
    EditText etNewPassword;
    EditText etNewPasswordConfirm;
    CustomerDAO customerDAO;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        customerDAO = new CustomerDAO(this);
        btnVerify = (Button) findViewById(R.id.btnVerify);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etNewPasswordConfirm = (EditText) findViewById(R.id.etNewPasswordConfirm);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");


        btnVerify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String password = etNewPassword.getText().toString().trim();
                String passwordConfirm = etNewPasswordConfirm.getText().toString().trim();

                if (!password.equals(passwordConfirm)) {
                    Toast.makeText(getApplicationContext(),
                            "Mật khẩu không trùng khớp",
                            Toast.LENGTH_LONG).show();
                } else if (customerDAO.updatePassword(email,password)) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Cập nhật mật khẩu thành công",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}