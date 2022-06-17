package com.nahfang.studycard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nahfang.studycard.fragment.record.RecordFragment;

/**
 * 作为用户第一次进入app的欢迎界面
 *
 */

public class LaucherActivity extends AppCompatActivity {
    boolean isLauch = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laucher);
        Intent intent = new Intent(LaucherActivity.this,MainActivity.class);
        Button start = findViewById(R.id.starter_button);
        SharedPreferences pref = getSharedPreferences("isLaucher", MODE_PRIVATE);
        isLauch = pref.getBoolean("isLauch", false);

        //看是否是第一次进入该App
        if(isLauch){
            startActivity(intent);
            finish();
        }

        start.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SharedPreferences.Editor editor = getSharedPreferences("isLaucher",
                        MODE_PRIVATE).edit();
                editor.putBoolean("isLauch", true);
                editor.apply();

                SharedPreferences.Editor editor1 = getSharedPreferences(RecordFragment.SWITCHSECTOR_SP,MODE_PRIVATE).edit();
                editor1.putString(RecordFragment.SWITCHSECTOR_SP_String, RecordFragment.RANDOM_CARD);
                editor1.apply();

                startActivity(intent);
                finish();
            }
        });
    }
}