package com.bebi.bebi_uas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bebi.bebi_uas.R;
import com.bebi.bebi_uas.model.BarangListResponse;
import com.bebi.bebi_uas.model.BarangResponse;

import java.util.ArrayList;
import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {


    ArrayList<BarangListResponse> result;
    Context context;

    public BarangAdapter(Context context, ArrayList<BarangListResponse> result) {
        super();
        this.result = result;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nama_barang.setText(context.getString(R.string.nama_barang)+ ": " + result.get(position).getNamaBarang());
        holder.harga_beli.setText(context.getString(R.string.harga_barang)+ ": " + result.get(position).getHargabeliBarang());
        holder.harga_jual.setText(context.getString(R.string.item_sell)+ ": " + result.get(position).getHargajualBarang());
        holder.jumlah.setText(context.getString(R.string.jumlah_barang)+ ": " + result.get(position).getJumlah());
        holder.entry.setText(context.getString(R.string.entry)+ ": " + result.get(position).getEntryBy());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nama_barang,harga_beli,harga_jual,jumlah,entry;

        public MyViewHolder(View itemView) {
            super(itemView);

            nama_barang = itemView.findViewById(R.id.txt_namabarang);
            harga_beli = itemView.findViewById(R.id.txt_hargabarang);
            harga_jual = itemView.findViewById(R.id.txt_hargajual);
            jumlah = itemView.findViewById(R.id.txt_jumlahbarang);
            entry = itemView.findViewById(R.id.txt_entry);
        }
    }
}