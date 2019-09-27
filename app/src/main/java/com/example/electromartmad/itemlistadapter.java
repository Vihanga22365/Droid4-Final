package com.example.electromartmad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class itemlistadapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<items> itemslist;

    public itemlistadapter(Context context, int layout, ArrayList<items> itemslist) {
        this.context = context;
        this.layout = layout;
        this.itemslist = itemslist;
    }

    @Override
    public int getCount() {
        return itemslist.size();
    }

    @Override
    public Object getItem(int position) {
        return itemslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        items i = itemslist.get(position);

        holder.txtName.setText(i.getName());
        holder.txtPrice.setText(i.getPrice());

        byte[] foodImage = i.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}