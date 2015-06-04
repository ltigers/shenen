package com.cntysoft.wheelview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private TextView mBirth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBirth = (TextView) findViewById(R.id.tv_birth);
        mBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ChangeBirthDialog mChangeBirthDialog = new ChangeBirthDialog(
                        MainActivity.this);
                mChangeBirthDialog.setDate(2015, 03, 29);
                mChangeBirthDialog.show();
                mChangeBirthDialog.setBirthdayListener(new ChangeBirthDialog.OnBirthListener() {

                    @Override
                    public void onClick(String year, String month, String day) {
                        // TODO Auto-generated method stub
                        Toast.makeText(MainActivity.this,
                                year + "-" + month + "-" + day,
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
