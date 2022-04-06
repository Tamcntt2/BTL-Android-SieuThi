package com.example.sieuthi.category;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sieuthi.R;
import com.example.sieuthi.product.Product;
import com.example.sieuthi.product.ProductAdapter;
import com.example.sieuthi.product.ProductInCartAdapter;
import com.example.sieuthi.product.ProductInCategoryAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CategoryHighlightAdapter extends RecyclerView.Adapter<CategoryHighlightAdapter.CategoryViewHolder> {

    Context context;
    List<Category> listCategory;

    public CategoryHighlightAdapter(Context context, List<Category> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_category_highlight, parent, false);

        return new CategoryHighlightAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = (Category) listCategory.get(position);
        if(category != null) {
            holder.tvName.setText(category.getName());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
            ProductAdapter productAdapter = new ProductAdapter(context, category.getListProduct());

            holder.rcvListProduct.setLayoutManager(linearLayoutManager);
            holder.rcvListProduct.setAdapter(productAdapter);

            holder.tvSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog̣(category);
                }
            });
        }
    }

    private void showDialog̣(Category category) {
        // show dialog
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewDialog = layoutInflater.inflate(R.layout.layout_category, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(viewDialog);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) viewDialog.getParent());
        bottomSheetBehavior.setState(STATE_EXPANDED);
        bottomSheetDialog.show();

        TextView tvNameCategory = (TextView) bottomSheetDialog.findViewById(R.id.textViewTenDanhMuc);
        tvNameCategory.setText(category.getName());

        RecyclerView rcvListProduct = (RecyclerView) bottomSheetDialog.findViewById(R.id.recyclerViewSearch);
        ProductInCategoryAdapter productInCategoryAdapter = new ProductInCategoryAdapter(context, category.getListProduct());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        rcvListProduct.setAdapter(productInCategoryAdapter);
        rcvListProduct.setLayoutManager(linearLayoutManager);

        // search
        SearchView searchView = (SearchView) viewDialog.findViewById(R.id.searchViewTop);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productInCategoryAdapter.getFilter().filter(newText);
                return false;
            }
        });

        // back
        ImageView imgBack = (ImageView) viewDialog.findViewById(R.id.imageViewBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        RecyclerView rcvListProduct;
        TextView tvSeeMore;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.textViewTileCategory);
            tvSeeMore = (TextView) itemView.findViewById(R.id.textViewCategorySeeMore);
            rcvListProduct = (RecyclerView) itemView.findViewById(R.id.recyclerViewListProduct);
        }
    }
}
