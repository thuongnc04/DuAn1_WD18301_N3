package com.example.duan1_wd18301_n3.Login_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_wd18301_n3.DAO.ForgotPasswordDAO;
import com.example.duan1_wd18301_n3.R;

public class VerifyActivity extends AppCompatActivity {
    Button btnVerify;
    EditText etCode;
    ForgotPasswordDAO forgotPasswordDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        forgotPasswordDAO = new ForgotPasswordDAO(this);
        btnVerify = (Button) findViewById(R.id.btnVerify);
        etCode = (EditText) findViewById(R.id.etCode);

        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        String email = intent.getStringExtra("email");


        btnVerify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String txtCode = etCode.getText().toString().trim();
                if (txtCode.equals(code)) {
                    Intent i = new Intent(getApplicationContext(), UpdatePasswordActivity.class);
                    i.putExtra("email", email);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Mã xác nhận sai, vui lòng nhập lại",
                            Toast.LENGTH_LONG).show();
                    etCode.setText("");
                }
            }
        });
    }
}