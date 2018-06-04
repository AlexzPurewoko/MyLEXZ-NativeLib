package com.mylexz.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LOAD_AND_SAVE";
    EditText nama, email, pass;
    NodeData data;
    TextView status;
    private static final String NAME_METHOD = "LIST_NAMES";
    private static final String EMAIL_METHOD = "LIST_EMAIL";
    private static final String PASS_METHOD = "LIST_PASSW";
    private static final String CONS_METHOD = "DAFTAR";
    private static final String SIGNATURE = "dataDIR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new NodeData(this, "data.txt", SIGNATURE, NodeData.MODE_PRIVATE);
        data.open();
        // initialize all elements
        nama = findViewById(R.id.editText);
        email = findViewById(R.id.editText2);
        pass = findViewById(R.id.editText3);
        status = findViewById(R.id.textView2);
        // make a node in data
        data.addNode(null, CONS_METHOD);
        if(!data.isElementExists(conc(CONS_METHOD, NAME_METHOD))){
            data.addStringArray(CONS_METHOD, NAME_METHOD, null, false);
            data.addStringArray(CONS_METHOD, EMAIL_METHOD, null, false);
            data.addStringArray(CONS_METHOD, PASS_METHOD, null, false);
        }

    }

    private String conc(String consMethod, String nameMethod) {
        return consMethod+"/"+nameMethod;
    }

    public void click(View view) {
        String names = nama.getText().toString();
        String mEmail = email.getText().toString();
        String ypass = pass.getText().toString();
        if(data.isEmptyData(conc(CONS_METHOD, NAME_METHOD))){
            data.setArray(conc(CONS_METHOD, NAME_METHOD), new String[] {names});
            data.setArray(conc(CONS_METHOD, EMAIL_METHOD), new String[] {mEmail});
            data.setArray(conc(CONS_METHOD, PASS_METHOD), new String[] {ypass});
        }
        else{
            data.appendArray(conc(CONS_METHOD, NAME_METHOD), new String[]{names});
            data.appendArray(conc(CONS_METHOD, EMAIL_METHOD), new String[]{mEmail});
            data.appendArray(conc(CONS_METHOD, PASS_METHOD), new String[]{ypass});
        }
    }

    public void load(View view) {
        status.setText("");
        StringBuilder sb = new StringBuilder("");
        int len = data.getArrayLength(conc(CONS_METHOD, NAME_METHOD));
        if(len == 0)return;
        Log.i(TAG, "START LOADING RESOURCES...");
        String[] allnames = data.getStringArray(conc(CONS_METHOD, NAME_METHOD));
        String[] allemails = data.getStringArray(conc(CONS_METHOD, EMAIL_METHOD));
        String[] allpassw = data.getStringArray(conc(CONS_METHOD, PASS_METHOD));
        for (int x = 0; x < len; x++){
            sb.append("Data "+x+1);
            sb.append("\n\tNama\t: "+allnames[x]);
            Log.i(TAG, "names is "+allnames[x]);
            sb.append("\n\tEmail\t: "+allemails[x]);
            Log.i(TAG, "EMAIL is "+allemails[x]);
            sb.append("\n\tPassw\t: "+allpassw[x]);
            Log.i(TAG, "passw is "+allpassw[x]);
            sb.append(System.getProperty("line.separator"));
        }
        status.setText(sb.toString()
        );
    }
}
