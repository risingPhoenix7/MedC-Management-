package com.example.medcmanagementapp;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class InventoryListAdapter extends ArrayAdapter<Medicine> {
    TextView medicineName;
    TextView quantityAvailable;
    TextView id;
    TextView price;

    public InventoryListAdapter(Context context, ArrayList<Medicine> inventory) {
        super(context, R.layout.activity_single_inventory_view, inventory);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Medicine medicine = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_single_inventory_view, parent, false);
        }
        medicineName = convertView.findViewById(R.id.medicinename);
        quantityAvailable = convertView.findViewById(R.id.quantityavailable);
        id = convertView.findViewById(R.id.medicineid);
        price = convertView.findViewById(R.id.price);


        medicineName.setText(medicine.getItemName());
        quantityAvailable.setText(medicine.getStringQuantity());
        price.setText(medicine.getStringPrice());
        id.setText(medicine.getItemID());
        System.out.println("justd");
        return convertView;
    }
}
