package com.example.appbannuoc;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterSanPham extends BaseAdapter {
    public Home_Activity context;
    public int Layout;
    public List<SanPhamTable> sanPhamList;

    public AdapterSanPham(Home_Activity context, int layout, List<SanPhamTable> sanPhamList) {
        this.context = context;
        Layout = layout;
        this.sanPhamList = sanPhamList;
    }

    public AdapterSanPham() {}

    @Override
    public int getCount() {
        return sanPhamList.size(); //trả về số phần tử mà ListView hiển thị
    }

    @Override
    public Object getItem(int position) { return null; }

    @Override
    public long getItemId(int position) { return 0; }

    public class ViewHolder
    {
        public TextView edtTENSP;
        public TextView edtGIA;
        public ImageView imgvDelete;
        public ImageView imgvEdit;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(Layout, null);
            holder.edtTENSP = (TextView) view.findViewById(R.id.edtTENSP);
            holder.edtGIA = (TextView) view.findViewById(R.id.edtGIA);
            holder.imgvDelete = (ImageView) view.findViewById(R.id.imgvDelete);
            holder.imgvEdit = (ImageView) view.findViewById(R.id.imgvEdit);

            view.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        SanPhamTable sanpham = this.sanPhamList.get(position);
        holder.edtTENSP.setText(sanpham.getTENSP());
        holder.edtGIA.setText(String.valueOf(sanpham.getGIA()));
        holder.imgvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogDelete(sanpham.getSP_ID());
            }
        });
        holder.imgvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogEdit(sanpham);
            }
        });
        return view;
    }
}
