package com.example.darycam;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<ListElement> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;

    }
    @Override
    public int getItemCount() {return mData.size(); }


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_classm,null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListElement> items) {mData=items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconclass;
        TextView clase, hora, status;

        ViewHolder(View itemView){
            super(itemView);
            iconclass=itemView.findViewById(R.id.Iconoclas);
            clase=itemView.findViewById(R.id.nameclas);
            hora=itemView.findViewById(R.id.horaclas);
            status=itemView.findViewById(R.id.estadoclas);
        }


        void bindData(final ListElement item){
            iconclass.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            clase.setText(item.getClase());
            hora.setText(item.getHora());
            status.setText(item.getEstatus());
        }
    }


}
