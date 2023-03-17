package com.example.appforermasoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.appforermasoft.adapter.CarsListAdapter;
import com.example.appforermasoft.data_base.RoomDB;
import com.example.appforermasoft.model.CarData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.appforermasoft.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add;
    CarsListAdapter carsListAdapter;
    RoomDB database;
    List<CarData> carData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        add = findViewById(R.id.add);

        database = RoomDB.getInstance(this);
        carData = database.mainDao().getAll();

        updateRecycle(carData);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101){
            if (resultCode == Activity.RESULT_OK){
                CarData new_carData = (CarData) data.getSerializableExtra("car_data");
                database.mainDao().insert(new_carData);
                carData.clear();
                carData.addAll(database.mainDao().getAll());
                carsListAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRecycle(List<CarData> carDatas) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        carsListAdapter = new CarsListAdapter(MainActivity.this, carDatas, carsClickListener);
        recyclerView.setAdapter(carsListAdapter);
    }

    private final CarsClickListener carsClickListener = new CarsClickListener() {
        @Override
        public void Click(CarData carData) {

        }
    };
}