package com.example.sieuthi.product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sieuthi.R;

import java.util.List;

public class ProductInCartAdapter extends RecyclerView.Adapter<ProductInCartAdapter.ProductViewHolder> {

    Context context;
    List<Product> listProduct;
    int tongTien;

    public ProductInCartAdapter(Context context, List<Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;

        // tính tổng tiền ban đầu
        this.tongTien = 0;
        for(Product product : listProduct) {
            tongTien += product.getPrice() * product.getQuantity();
        }

    }

    public int getTongTien() {
        return tongTien;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_product_in_cart, parent, false);

        return new ProductInCartAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Product product = (Product) listProduct.get(position);

        if(product != null) {
            holder.imgPhoto.setImageResource(product.getImgPhoto());
            holder.tvName.setText(product.getName());
            holder.tvPrice.setText(editPrice(product.getQuantity() * product.getPrice()));
            holder.tvNum.setText(product.getQuantity() + "");

            // xóa
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listProduct.remove(position);
                    tongTien -= product.getPrice() * product.getQuantity();
                    notifyDataSetChanged();
                }
            });

            // trừ 1
            holder.imgRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int num = product.getQuantity();
                    if(num > 0) {
                        num --;
                        holder.tvNum.setText(num + "");
                        holder.tvPrice.setText(editPrice(num * product.getPrice()));
                        product.setQuantity(num);
                    } else {
                        listProduct.remove(position);
                        notifyDataSetChanged();
                    }
                    tongTien -= product.getPrice();
                }
            });

            // cộng 1
            holder.imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int num = product.getQuantity();
                    num ++;
                    holder.tvNum.setText(num + "");
                    holder.tvPrice.setText(editPrice(num * product.getPrice()));
                    product.setQuantity(num);
                    tongTien += product.getPrice();
                }
            });
        }
    }

    String editPrice(int price) {
        String sPrice = price + "";
        for(int i=sPrice.length() - 3; i>0; i -= 3) {
            sPrice = sPrice.substring(0, i) + "," + sPrice.substring(i, sPrice.length());
        }
        return sPrice + "đ";
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvPrice;
        TextView tvName;
        TextView tvNum;
        ImageView imgAdd;
        ImageView imgRemove;
        ImageView imgDelete;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = (ImageView) itemView.findViewById(R.id.imageViewPhoto);
            tvName = (TextView) itemView.findViewById(R.id.textViewName);
            tvPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            tvNum = (TextView) itemView.findViewById(R.id.textViewNum);
            imgAdd = (ImageView) itemView.findViewById(R.id.imageViewAdd);
            imgRemove = (ImageView) itemView.findViewById(R.id.imageViewRemove);
            imgDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);

        }
    }
}
