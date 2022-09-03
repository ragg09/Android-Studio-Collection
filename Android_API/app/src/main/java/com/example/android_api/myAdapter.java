package com.example.android_api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder>{

    private Context mContext;
    private List<item_data> list_data;
    private static final String urlString ="http://192.168.1.9:8000/" ;

    public myAdapter(Context mContext,List<item_data> list_data) {
        this.list_data = list_data;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.activity_list_data,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final item_data listData=list_data.get(position);
        Log.i("image",urlString + listData.getImage());
        if (listData.getImage().isEmpty()) {
            holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        } else{
            Picasso.get().load(urlString + listData.getImage()).into(holder.imageView);
        }

        /*Glide.with(mContext)
                .load(listData.getImage())
                .into(holder.imageView);*/
        holder.txtdescription.setText(listData.getDescription());
        holder.txtsell_price.setText(listData.getSell_price());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,DetailView.class);
                intent.putExtra("name",listData.getDescription());
                intent.putExtra("imageurl",urlString + listData.getImage());
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtdescription,txtsell_price;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            txtdescription=(TextView)itemView.findViewById(R.id.txt_description);
            txtsell_price=(TextView)itemView.findViewById(R.id.txt_sell_price);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }

}
