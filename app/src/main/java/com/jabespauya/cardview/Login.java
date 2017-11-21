package com.jabespauya.cardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private TextView mRegisterText;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    /**
     * initialization
     */
    private void init() {
        mRegisterText = (TextView) findViewById(R.id.txtRegister);
        mLogin = (Button) findViewById(R.id.btnLogin);

        mRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activitiesIntent(2);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activitiesIntent(1);
            }
        });

    }


    /**
     * navigate activity per intent
     */
    private void activitiesIntent(int intentActivities) {
        Intent intent;
        switch (intentActivities) {
            case 1:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, AccountRegistration.class);
                startActivity(intent);
                break;
        }
    }
}
