package com.cntysoft.testmaterial;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextInputLayout textInputLayout;
    private EditText editText;
    private FloatingActionButton button;
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInputLayout = (TextInputLayout) findViewById(R.id.textInput);
        button = (FloatingActionButton) findViewById(R.id.button);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        editText = textInputLayout.getEditText();
        textInputLayout.setHint("请输入用户名");
        textInputLayout.setError("密码输入错啦！");
        textInputLayout.setErrorEnabled(true);//当设置成false的时候 错误信息不显示 反之显示
        button.setRippleColor(Color.GRAY);//设置按下去的波纹颜色
        button.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_menu_add));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(relativeLayout,"你好",Snackbar.LENGTH_SHORT)
                        .setAction("delete", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this,"hhh",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }
}
