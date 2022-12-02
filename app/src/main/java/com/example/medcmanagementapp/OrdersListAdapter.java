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

public class OrdersListAdapter extends ArrayAdapter<Order> {
    public OrdersListAdapter(Context context, ArrayList<Order> orderArrayList) {
        super(context, R.layout.activity_view_single_order, orderArrayList);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Order order = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_view_single_order, parent, false);
        }
        TextView orderID=convertView.findViewById(R.id.textView28);
        TextView studentID=convertView.findViewById(R.id.textView29);
        TextView medicineName=convertView.findViewById(R.id.textView34);
        TextView quantity=convertView.findViewById(R.id.textView35);
        TextView PaymentMode=convertView.findViewById(R.id.textView38);

        orderID.setText(order.getOrderID().toString());
        studentID.setText(Long.toString(order.getBitsID()));
        medicineName.setText(order.getMedicineName());
        quantity.setText(order.getStringOrderQuantity());
        PaymentMode.setText(order.getPaymentMethod());

        return convertView;
    }
}
