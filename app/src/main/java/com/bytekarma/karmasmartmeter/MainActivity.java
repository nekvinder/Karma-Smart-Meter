package com.bytekarma.karmasmartmeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Info;
    private EditText mtrNo;
    private Button loginBtn;
    private AsyncTask MyAsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Info = (TextView) findViewById(R.id.textView2);
        mtrNo = (EditText) findViewById(R.id.editText);
        loginBtn = (Button) findViewById((R.id.button));
        loginBtn.setOnClickListener(this);


        // dev only
        mtrNo.setText("GP4456803");
//        loginBtn.callOnClick();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                new Loginx().execute(mtrNo.getText().toString());
                break;
        }
    }

    private class Loginx extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... params) {
            String[] result = new String[30];

            String mtrNum = params[0];
            CSVReader reader = null;
            try {
                reader = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.data)));
                List<String[]> entries = reader.readAll();
                int check = 0;
                for (int i = 0; i < entries.size(); i++) {
                    System.out.println(entries.get(i)[11]);
                    if (entries.get(i)[11].equalsIgnoreCase(mtrNum)) {
                        result[0] = "Found at index " + i;//entries.get(i)[11].indexOf(mtrNum);
                        result[1] = "true";
                        result[2] = entries.get(i)[2];
                        result[3] = entries.get(i)[0];
                        result[4] = entries.get(i)[3];
                        result[5] = entries.get(i)[4];
                        result[6] = entries.get(i)[5];
                        result[7] = entries.get(i)[9];
                        result[8] = Integer.toString(Integer.parseInt(entries.get(i)[27]) - Integer.parseInt(entries.get(i)[25]));
                        result[9] = entries.get(i)[42];

                        return result;
                    }
                }
                if (check == 0) {
                    result[1] = "false";
                } else
                    result[1] = "true";
            } catch (java.io.IOException e) {
                e.printStackTrace();
                result[1] = "false";
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            loginBtn.setEnabled(false);
            Info.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(String[] res) {
            if (res[1] == "false") {
                Info.setText("Not Found");
            } else
                Info.setText(res[0]);
            Intent i = new Intent(getApplicationContext(), User_Profile.class);
            i.putExtra("showdata", Arrays.copyOfRange(res,2,res.length ) );
            loginBtn.setEnabled(true);
            startActivity(i);
        }

    }
}