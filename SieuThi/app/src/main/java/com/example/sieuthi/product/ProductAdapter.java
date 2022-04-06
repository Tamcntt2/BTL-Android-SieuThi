package com.example.sieuthi.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sieuthi.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<Product> listProduct;

    public ProductAdapter(Context context, List<Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_product, parent, false);

        return new ProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {

        Product product = (Product) listProduct.get(position);

        if(product != null) {
            holder.imgPhoto.setImageResource(product.getImgPhoto());
            holder.tvName.setText(product.getName());
            holder.tvPrice.setText(editPrice(product.getPrice()));

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnClickItemProduct(product);
                }
            });
        }
    }

    private void setOnClickItemProduct(Product product) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewDialog = layoutInflater.inflate(R.layout.bottom_sheet_product, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(viewDialog);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) viewDialog.getParent());
//        bottomSheetBehavior.setState(STATE_EXPANDED);

        // Gán dữ liệu cho dialog
        ImageView imgPhoto = (ImageView) viewDialog.findViewById(R.id.imageViewProduct);
        TextView tvName = (TextView) viewDialog.findViewById(R.id.textViewProductName);
        TextView tvPrice = (TextView) viewDialog.findViewById(R.id.textViewProductPrice);
        TextView tvNum = (TextView) viewDialog.findViewById(R.id.textViewNum);
        Button btnAddToCart = (Button) viewDialog.findViewById(R.id.buttonAddToCart);

        ImageView imgClose = (ImageView) viewDialog.findViewById(R.id.imageViewClose);
        ImageView imgAdd = (ImageView) viewDialog.findViewById(R.id.imageViewAdd);
        ImageView imgRemove = (ImageView) viewDialog.findViewById(R.id.imageViewRemove);

        imgPhoto.setImageResource(product.getImgPhoto());
        tvName.setText(product.getName());
        tvPrice.setText(editPrice(product.getPrice()));

        imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tvNum.getText().toString());
                if(num > 0) {
                    num --;
                    tvNum.setText(num + "");
                    btnAddToCart.setText("Thêm vào giỏ hàng " + editPrice(num * product.getPrice()));
                }

            }
        });

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tvNum.getText().toString());
                if(num < product.getQuantity()) {
                    num ++;
                    tvNum.setText(num + "");
                    btnAddToCart.setText("Thêm vào giỏ hàng " + editPrice(num * product.getPrice()));
                }
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // up dữ liệu lên database giỏ hàng
                // ...

                bottomSheetDialog.dismiss();
            }
        });


        bottomSheetDialog.show();
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
        return listProduct .size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvPrice;
        TextView tvName;
        LinearLayout linearLayout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = (ImageView) itemView.findViewById(R.id.imageViewProduct);
            tvName = (TextView) itemView.findViewById(R.id.textViewProductName);
            tvPrice = (TextView) itemView.findViewById(R.id.textViewProductPrice);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutProduct);
        }
    }
}
