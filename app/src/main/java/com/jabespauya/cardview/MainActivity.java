package com.jabespauya.cardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView _image;
    private TextView _title;
    private TextView _description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    private void initLayout(){
        _image = (ImageView) findViewById(R.id.thumbnail);
        _title = (TextView) findViewById(R.id.mTitle);
        _description = (TextView) findViewById(R.id.mDescription);
    }
}
