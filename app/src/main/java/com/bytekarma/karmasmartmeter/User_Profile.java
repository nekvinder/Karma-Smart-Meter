package com.bytekarma.karmasmartmeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class User_Profile extends AppCompatActivity implements View.OnClickListener {
    private TextView showFields[]  = new TextView[8] ;
    private String strs[];
    private Button btnExit;
    private Button btnBill;
    private Button btnReport;
    private Button btnback;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                break;
            case R.id.button3:
                onSupportNavigateUp();
            break;
            case R.id.button5:
                Toast.makeText(getApplicationContext(),"Show Report",Toast.LENGTH_SHORT).show();
                Intent xz = new Intent(getApplicationContext(), Reports.class);
                startActivity(xz);
                break;
            case R.id.button2:
//                Toast.makeText(getApplicationContext(),"Show Bill",Toast.LENGTH_SHORT).show();
                Intent z = new Intent(getApplicationContext(), View_Bill.class);
                z.putExtra("consumerno", strs[1] );
                z.putExtra("kwh", strs[6] );
                btnReport.setEnabled(false);
                startActivity(z);
                btnReport.setEnabled(true);

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);
//        UserName = (TextView) findViewById(R.id.txtName);
        btnExit = (Button) findViewById(R.id.button4);
        btnback = (Button) findViewById(R.id.button3);
        btnReport = (Button) findViewById(R.id.button5);
        btnBill = (Button) findViewById(R.id.button2);

        btnback.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnReport.setOnClickListener(this);
        btnBill.setOnClickListener(this);


        showFields[0] = (TextView) findViewById(R.id.textView11);
        showFields[1] = (TextView) findViewById(R.id.textView5);
        showFields[2] = (TextView) findViewById(R.id.textView4);
        showFields[3] = (TextView) findViewById(R.id.textView7);
        showFields[4] = (TextView) findViewById(R.id.textView13);
        showFields[5] = (TextView) findViewById(R.id.textView15);
        showFields[6] = (TextView) findViewById(R.id.textView17);
        showFields[7] = (TextView) findViewById(R.id.textView23);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        strs = extras.getStringArray("showdata");
        System.out.println(strs);
//        UserName.setText(new String(strs));
        for (int xi = 0; xi < 8; xi++) {
          showFields[xi].setText( strs[xi].isEmpty()?"None":strs[xi] );
        }

        assert getSupportActionBar() !=null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // dev only
//        btnBill.callOnClick();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
