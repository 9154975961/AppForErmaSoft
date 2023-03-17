package com.example.appforermasoft.data_base;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.appforermasoft.model.CarData;

import java.util.List;

@Dao
public interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CarData carData);

    @Query("SELECT * FROM car_data ORDER BY uid DESC")
    List<CarData> getAll();

    @Query("UPDATE car_data SET name_brand = :nameBrand, name_car = :nameCar, price_car = :priceCar WHERE uid = :id")
    void update (int id, String nameBrand, String nameCar, Double priceCar);

    @Delete
    void delete (CarData carData);
}
