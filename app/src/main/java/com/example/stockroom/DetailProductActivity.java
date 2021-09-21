package com.example.stockroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class DetailProductActivity extends AppCompatActivity {
    private Button applyChangesBtn;
    private EditText inputName, inputStock, inputSize, inputCategory, inputPrice, inputDescription;
    private ImageView inputImage;

    private String productID ="";
    private DatabaseReference productsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        applyChangesBtn = findViewById(R.id.apply_changes_btn);
        inputName = findViewById(R.id.name_form);
        inputStock = findViewById(R.id.stock_form);
        inputSize = findViewById(R.id.size_form);
        inputCategory = findViewById(R.id.category_form);
        inputPrice = findViewById(R.id.price_form);
        inputDescription = findViewById(R.id.description_form);
        inputImage = findViewById(R.id.select_image);

        productID = getIntent().getStringExtra("pid");
        productsRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productID);

        displaySpesificProductInfo();

        applyChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });
    }

    private void applyChanges(){
        String name = inputName.getText().toString();
        String stock = inputStock.getText().toString();
        String size = inputSize.getText().toString();
        String category = inputCategory.getText().toString();
        String price = inputPrice.getText().toString();
        String description = inputDescription.getText().toString();

        if(name.equals("")){
            Toast.makeText(this, "Write down product Name..", Toast.LENGTH_SHORT).show();
        }
        else if(stock.equals("")){
            Toast.makeText(this, "Write down product Stock", Toast.LENGTH_SHORT).show();
        }
        else if(size.equals("")){
            Toast.makeText(this, "Write down product Stock", Toast.LENGTH_SHORT).show();
        }
        else if(category.equals("")){
            Toast.makeText(this, "Write down product Stock", Toast.LENGTH_SHORT).show();
        }
        else if(price.equals("")){
            Toast.makeText(this, "Write down product Stock", Toast.LENGTH_SHORT).show();
        }
        else if(description.equals("")){
            Toast.makeText(this, "Write down product Stock", Toast.LENGTH_SHORT).show();
        }
        else{
            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid", productID);
            productMap.put("name", name);
            productMap.put("stock", stock);
            productMap.put("size", size);
            productMap.put("category", category);
            productMap.put("price", price);
            productMap.put("description", description);

            productsRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(DetailProductActivity.this, "Changes applied successfully", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(DetailProductActivity.this, SuccessProductActivity.class));
                    }
                }
            });
        }
    }
    private void displaySpesificProductInfo(){
        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = snapshot.child("name").getValue().toString();
                    String image = snapshot.child("image").getValue().toString();
                    String stock = snapshot.child("stock").getValue().toString();
                    String size = snapshot.child("size").getValue().toString();
                    String category = snapshot.child("category").getValue().toString();
                    String price = snapshot.child("price").getValue().toString();
                    String description = snapshot.child("description").getValue().toString();

                    inputName.setText(name);
                    inputStock.setText(stock);
                    inputSize.setText(size);
                    inputCategory.setText(category);
                    inputPrice.setText(price);
                    inputDescription.setText(description);
                    Picasso.get().load(image).into(inputImage);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}