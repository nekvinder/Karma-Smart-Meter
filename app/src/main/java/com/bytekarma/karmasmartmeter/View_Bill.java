package com.bytekarma.karmasmartmeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class View_Bill extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String[] Gateway = {"PayTm", "PhonePe", "UPI", "Banking Portal","Google Pay","Debit Card","Credit Card"};


    private TextView txtConsumer;
    private TextView txtKWH;
    private TextView txtCost;
    private TextView txtTotalAmt;
    private TextView txtGST;
    private TextView txtPayAmt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__bill);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        Bundle extras = i.getExtras();

        txtConsumer = (TextView) findViewById(R.id.textView19);
        txtKWH = (TextView) findViewById(R.id.textView21);
        txtConsumer.setText(extras.getString("consumerno"));
        txtKWH.setText(extras.getString("kwh"));

        txtCost = (TextView) findViewById(R.id.textView22);
        txtCost.setText("5");
        txtTotalAmt = (TextView) findViewById(R.id.textView27);
        txtTotalAmt.setText(Double.toString(Double.parseDouble(txtKWH.getText().toString()) * Double.parseDouble(txtCost.getText().toString())));

        txtGST = (TextView) findViewById(R.id.textView29);
        txtGST.setText("7.5");
        txtPayAmt = (TextView) findViewById(R.id.textView31);
        txtPayAmt.setText(Double.toString(Double.parseDouble(txtTotalAmt.getText().toString()) * .075));


        Spinner spin = (Spinner) findViewById(R.id.spinner);

        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Gateway);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
