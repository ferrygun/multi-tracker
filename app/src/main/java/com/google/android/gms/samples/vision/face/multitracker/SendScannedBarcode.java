package com.google.android.gms.samples.vision.face.multitracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SendScannedBarcode extends AppCompatActivity {

    public final String BARCODE_MESSAGE = "com.google.android.gms.samples.vision.face.multitracker.BARCODE";
    public final String OCR_MESSAGE = "com.google.android.gms.samples.vision.face.multitracker.OCR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_scanned_barcode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        String barcode_msg = intent.getStringExtra(BARCODE_MESSAGE);
        String ocr_msg = intent.getStringExtra(OCR_MESSAGE);

        TextView txtScannedValue = (TextView) findViewById(R.id.txtScannedValue);
        txtScannedValue.setText(barcode_msg + ":" + ocr_msg);

        Button btnSendScannedValue = (Button) findViewById(R.id.btnSendScannedValue);
        btnSendScannedValue.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
