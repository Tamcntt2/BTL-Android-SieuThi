package com.example.sieuthi.mainfragment;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sieuthi.R;
import com.example.sieuthi.category.Category;
import com.example.sieuthi.category.CategoryAdapter;
import com.example.sieuthi.category.CategoryHighlightAdapter;
import com.example.sieuthi.product.Product;
import com.example.sieuthi.product.ProductInCategoryAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    Context context;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product, container, false);
        context = view.getContext();

        setRecyclerViewListHighlight();

        setRecyclerViewCategory();

        // Khung search tìm kiếm sản phẩm
        TextView tvSearch = (TextView) view.findViewById(R.id.textViewSearch);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSearch();
            }
        });

        // Giỏ hàng
        ImageView imgCart = (ImageView) view.findViewById(R.id.imageViewCart);
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCart();
            }
        });

        return view;
    }

    private void showDialogCart() {
    }

    private void showDialogSearch() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewDialog = layoutInflater.inflate(R.layout.layout_search, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(viewDialog);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) viewDialog.getParent());
        bottomSheetBehavior.setState(STATE_EXPANDED);
        bottomSheetDialog.show();

        // recycler view
        RecyclerView rcvListProduct = (RecyclerView) viewDialog.findViewById(R.id.recyclerViewSearch);
        List<Product> listProduct = new ArrayList<>(getDataProduct());
        ProductInCategoryAdapter productInCategoryAdapter = new ProductInCategoryAdapter(context, listProduct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        rcvListProduct.setAdapter(productInCategoryAdapter);
        rcvListProduct.setLayoutManager(linearLayoutManager);

        // button back
        ImageView imgBack = (ImageView) viewDialog.findViewById(R.id.imageViewBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

//         search view filter
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
    }

    public void setRecyclerViewCategory() {
        ArrayList<Category> listCategory = new ArrayList<>(getDataCategory());

        // init
        RecyclerView rcvCatedory = (RecyclerView) view.findViewById(R.id.recyclerViewListCategory);
        CategoryAdapter categoryAdapter = new CategoryAdapter(context, listCategory);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);

        // set up
        rcvCatedory.setAdapter(categoryAdapter);
        rcvCatedory.setLayoutManager(gridLayoutManager);

    }

    private List<Category> getDataCategory() {
        // Add database
        ArrayList<Product> listProduct = new ArrayList<>(getDataProduct());

        List<Category> listCategory = new ArrayList<>();
        listCategory.add(new Category("", R.drawable.category1,"Rau củ", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Trái cây", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Thịt, hải sản", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Thực phẩm đông lạnh", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Đồ hộp", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Mỳ gói, TP ăn liền", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Thực phẩm khô", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Nguyên liệu, gia vị", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Trứng, sữa", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Bánh ngọt, bánh mì", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Thực phẩm chế biến sẵn", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Bánh kẹo, snack", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Nước giải khát", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Bia, rượu", listProduct));

        return listCategory;
    }

    private List<Product> getDataProduct() {
        ArrayList<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        return listProduct;
    }


    public void setRecyclerViewListHighlight() {

        // Add database
        ArrayList<Product> listProduct = new ArrayList<>(getDataProduct());

        ArrayList<Category> listCategory = new ArrayList<>();
        listCategory.add(new Category("", R.drawable.category1,"Hàng mới về", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Bán chạy", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Tất cả sản phẩm", listProduct));

        // init
        RecyclerView rcvCatedory = (RecyclerView) view.findViewById(R.id.recyclerViewListHighlight);
        CategoryHighlightAdapter categoryAdapter = new CategoryHighlightAdapter(context, listCategory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        // set up
        rcvCatedory.setAdapter(categoryAdapter);
        rcvCatedory.setLayoutManager(linearLayoutManager);

    }
}