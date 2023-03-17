package com.example.appforermasoft.data_base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appforermasoft.model.CarData;

@Database(entities = CarData.class, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB roomDB;
    private static String DATABASE_NAME = "car_data";

    public synchronized static RoomDB getInstance(Context context){
        if (roomDB == null){
            roomDB = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDB;
    }

    public abstract MainDao mainDao();
}
