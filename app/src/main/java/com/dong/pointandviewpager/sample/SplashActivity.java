package com.dong.pointandviewpager.sample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dong.pointandviewpager.R;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = this;

        Button btn_loop = findViewById(R.id.btn_loopviewpager);
        Button btn_point = findViewById(R.id.btn_pointviewpager);

        btn_loop.setOnClickListener(this);
        btn_point.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_loopviewpager:
                startActivity(LoopActivity.class);
                break;
            case R.id.btn_pointviewpager:
                startActivity(PointActivity.class);
                break;
        }
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        mContext.startActivity(intent);
    }
}
