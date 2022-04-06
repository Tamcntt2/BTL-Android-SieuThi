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
import com.example.sieuthi.product.ProductInCategoryAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<Category> listCategory;

    public CategoryAdapter(Context context, List<Category> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_category, parent, false);

        return new CategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = (Category) listCategory.get(position);
        if(category != null) {
            holder.tvName.setText(category.getName());
            holder.imgPhoto.setImageResource(category.getImgPhoto());

            holder.imgPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog(category);
                }
            });

            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog(category);
                }
            });
        }
    }

    private void showDialog(Category category) {
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
        ImageView imgPhoto;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.textViewCategoryName);
            imgPhoto = (ImageView) itemView.findViewById(R.id.imageViewCategory);
        }
    }
}
