package com.example.appforermasoft;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appforermasoft.model.CarData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;

public class CarDetailsActivity extends AppCompatActivity {

    EditText car_brand, car_model, car_price;
    FloatingActionButton save, back;

    CarData carData;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        car_brand = findViewById(R.id.car_brand);
        car_model = findViewById(R.id.car_model);
        car_price = findViewById(R.id.car_price);

        save = findViewById(R.id.save);
        back = findViewById(R.id.back);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carBrand = car_brand.getText().toString();
                String carModel = car_model.getText().toString();
                String carPrice = car_price.getText().toString();

                if (carBrand.isEmpty() || carModel.isEmpty() || carPrice.isEmpty()){
                    Toast.makeText(CarDetailsActivity.this,"Введены не все данные", Toast.LENGTH_LONG).show();
                    return;
                }

                carData = new CarData();
                carData.setNameBrand(carBrand);
                carData.setNameCar(carModel);
                carData.setPriceCar(carPrice);

                Intent intent = new Intent();
                intent.putExtra("car_data", carData);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}