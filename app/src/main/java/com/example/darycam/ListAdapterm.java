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

public class ListAdapterm extends RecyclerView.Adapter<ListAdapterm.ViewHolder>{
    private List<ListElement> mDatam;
    private LayoutInflater mInflaterm;
    private Context contextm;
    final ListAdapterm.OnItemClickListener listenerm;


    public interface OnItemClickListener{
        void onItemClick(ListElement item);
    }

    public ListAdapterm(List<ListElement> itemList, Context context, ListAdapterm.OnItemClickListener listenerm){
        this.mInflaterm = LayoutInflater.from(context);
        this.contextm = context;
        this.mDatam = itemList;
        this.listenerm = listenerm;
    }
    @Override
    public int getItemCount() {return mDatam.size(); }


    @Override
    public ListAdapterm.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflaterm.inflate(R.layout.list_classa,null);
        return new ListAdapterm.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterm.ViewHolder holder, final int position){
        holder.bindData(mDatam.get(position));
    }

    public void setItems(List<ListElement> items) {mDatam=items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconclass;
        TextView clase, hora, status;

        ViewHolder(View itemView){
            super(itemView);
            iconclass=itemView.findViewById(R.id.Iconoclasm);
            clase=itemView.findViewById(R.id.nameclasm);
            hora=itemView.findViewById(R.id.horaclasm);
            status=itemView.findViewById(R.id.estadoclasm);

        }


        void bindData(final ListElement item){
            iconclass.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            clase.setText(item.getClase());
            hora.setText(item.getHora());
            status.setText(item.getEstatus());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerm.onItemClick(item);
                }
            });
        }
    }


}
