package com.example.dat.project1_ringtone.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dat.project1_ringtone.Model.Category;
import com.example.dat.project1_ringtone.View.Event.CallbackClick;
import com.example.dat.project1_ringtone.View.Event.ItemClickListener;
import com.example.dat.project1_ringtone.R;

import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.Viewholder> {
    Context context;
    ArrayList<Category> arrayList;
    CallbackClick callbackClick;

    public  AdapterCategory(ArrayList<Category> arrayList, Context context,CallbackClick callbackClick){
        this.context = context;
        this.arrayList = arrayList;
        this.callbackClick = callbackClick;

    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_category,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.imageView.setImageResource(arrayList.get(position).getImage());
        holder.setItemClick(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean islongclick) {
                callbackClick.click(view);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        ItemClickListener itemClickListener;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imCategory);
            itemView.setOnClickListener(this);
        }

        public void setItemClick(ItemClickListener itemClick){
            this.itemClickListener = itemClick;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }
    }
}
