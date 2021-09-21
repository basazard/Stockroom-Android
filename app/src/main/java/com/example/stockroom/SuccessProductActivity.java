package com.example.stockroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessProductActivity extends AppCompatActivity {
    Button addproductBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_product);

        addproductBtn = (Button) findViewById(R.id.addproduct_btn);
        addproductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuccessProductActivity.this, AddProductActivity.class));
            }
        });
    }
}