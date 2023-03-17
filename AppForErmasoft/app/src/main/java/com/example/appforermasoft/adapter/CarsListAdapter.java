package com.example.appforermasoft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appforermasoft.CarsClickListener;
import com.example.appforermasoft.R;
import com.example.appforermasoft.model.CarData;

import java.util.List;

public class CarsListAdapter extends RecyclerView.Adapter<CarsViewHolder>{

    Context context;
    List<CarData> list;

    CarsClickListener listener;

    public CarsListAdapter(Context context, List<CarData> list, CarsClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarsViewHolder(LayoutInflater.from(context).inflate(R.layout.cars_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        holder.car_brand.setText(list.get(position).getNameBrand());
        holder.car_brand.setSelected(true);

        holder.car_model.setText(list.get(position).getNameCar());

        holder.car_price.setText(list.get(position).getPriceCar());
        holder.car_price.setSelected(true);

        holder.cars_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.Click(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CarsViewHolder extends RecyclerView.ViewHolder{

    CardView cars_container;
    TextView car_brand, car_model, car_price;

    public CarsViewHolder(@NonNull View itemView) {
        super(itemView);

        cars_container = itemView.findViewById(R.id.cars_container);
        car_brand = itemView.findViewById(R.id.car_brand);
        car_model = itemView.findViewById(R.id.car_model);
        car_price = itemView.findViewById(R.id.car_price);
    }
}