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

import java.util.Random;

public class ForgotPasswordActivity extends AppCompatActivity {
    Button sendEmail;
    EditText etEmail;
    ForgotPasswordDAO forgotPasswordDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotPasswordDAO = new ForgotPasswordDAO(this);
        sendEmail = (Button) findViewById(R.id.btnSendEmail);
        etEmail = (EditText) findViewById(R.id.etEmail);

//        sendEmail.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                String email = etEmail.getText().toString().trim();
//                if (!forgotPasswordDAO.checkEmail(email)) {
//                    Toast.makeText(getApplicationContext(),
//                            "Tài khoản không tồn tại.",
//                            Toast.LENGTH_LONG).show();
//                    etEmail.setText("");
//                } else {
//                    Random random = new Random();
//                    String code = String.valueOf(random.nextInt(900000) + 100000);
//
//                    forgotPasswordDAO.sendEmail(email, code);
//                    Intent i = new Intent(getApplicationContext(), VerifyActivity.class);
//                    i.putExtra("code", code);
//                    i.putExtra("email", email);
//                    startActivity(i);
//                    Toast.makeText(getApplicationContext(),
//                            "Đã gửi email xác nhận",
//                            Toast.LENGTH_LONG).show();
//               }
//            }
//        });
    }
}