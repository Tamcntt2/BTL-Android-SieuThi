package com.example.sieuthi.product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sieuthi.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductInCategoryAdapter extends RecyclerView.Adapter<ProductInCategoryAdapter.ProductInCategoryViewHolder> implements Filterable {

    Context context;
    List<Product> listProduct;
    List<Product> listProductFull;

    public ProductInCategoryAdapter(Context context, List<Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
        this.listProductFull = new ArrayList<>(listProduct);
    }

    @NonNull
    @Override
    public ProductInCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_product_in_category, parent,
                false);

        return new ProductInCategoryAdapter.ProductInCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductInCategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Product product = (Product) listProduct.get(position);

        if(product != null) {
            holder.imgPhoto.setImageResource(product.getImgPhoto());
            holder.tvName.setText(product.getName());
            holder.tvPrice.setText(editPrice(product.getQuantity() * product.getPrice()));

            // Kiểm tra sản phẩm đã nằm trong giỏ hàng chưa
            Product productInCart = checkProductInCart(product);
            if(productInCart != null) {       // đã có
                holder.relativeLayoutNum.setVisibility(View.VISIBLE);
                holder.tvThemMoi.setVisibility(View.INVISIBLE);

                holder.tvNum.setText(productInCart.getQuantity() + "");

                holder.imgRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(productInCart.getQuantity() > 0) {
                            productInCart.setQuantity(productInCart.getQuantity() - 1);
                            holder.tvNum.setText(productInCart.getQuantity() + "");
                            holder.tvPrice.setText(editPrice(productInCart.getQuantity() * product.getPrice()));
                        }
                    }
                });

                holder.imgAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(productInCart.getQuantity() < product.getQuantity()) {
                            productInCart.setQuantity(productInCart.getQuantity() + 1);
                            holder.tvNum.setText(productInCart.getQuantity() + "");
                            holder.tvPrice.setText(editPrice(productInCart.getQuantity() * product.getPrice()));
                        }
                    }
                });

            } else {        // không
                holder.relativeLayoutNum.setVisibility(View.INVISIBLE);
                holder.tvThemMoi.setVisibility(View.VISIBLE);

            }

        }
    }

    private Product checkProductInCart(Product product) {
        List<Product> productsInCart = getDataProductInCart();
        for(Product p : productsInCart) {
            if(product.getId().equals(p.getId())) {
                return p;
            }
        }
        return null;
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

    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Product> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(listProductFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Product item : listProductFull) {
                    if(item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listProduct.clear();
            listProduct.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ProductInCategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvPrice;
        TextView tvName;
        TextView tvNum;
        ImageView imgAdd;
        ImageView imgRemove;
        TextView tvThemMoi;
        RelativeLayout relativeLayoutNum;

        public ProductInCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = (ImageView) itemView.findViewById(R.id.imageViewPhoto);
            tvName = (TextView) itemView.findViewById(R.id.textViewName);
            tvPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            tvNum = (TextView) itemView.findViewById(R.id.textViewNum);
            imgAdd = (ImageView) itemView.findViewById(R.id.imageViewAdd);
            imgRemove = (ImageView) itemView.findViewById(R.id.imageViewRemove);
            tvThemMoi = (TextView) itemView.findViewById(R.id.textViewThemMoi);
            relativeLayoutNum = (RelativeLayout) itemView.findViewById(R.id.relativeAddRemove);


        }
    }

    private List<Product> getDataProductInCart() {
        List<Product> listProductInCart = new ArrayList<>();

        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProductInCart.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));

        return listProductInCart;
    }
}
