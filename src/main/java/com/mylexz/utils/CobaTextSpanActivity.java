package com.mylexz.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.mylexz.utils.text.TextSpanFormat;

public class CobaTextSpanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba_text_span);
        String result = "[[size=2.5f]][[link=http://google.com]]**Ke Google**[[link]][[size]]";
        TextView txt = findViewById(R.id.textVi8);
        txt.setMovementMethod(LinkMovementMethod.getInstance());
        txt.setText(TextSpanFormat.convertStrToSpan(result));
    }
}
