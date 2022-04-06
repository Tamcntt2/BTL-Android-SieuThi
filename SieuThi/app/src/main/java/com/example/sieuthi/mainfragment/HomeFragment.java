package com.example.sieuthi.mainfragment;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sieuthi.R;
import com.example.sieuthi.banner.Banner;
import com.example.sieuthi.banner.BannerAdapter;
import com.example.sieuthi.category.Category;
import com.example.sieuthi.category.CategoryHighlightAdapter;
import com.example.sieuthi.newsfeed.Newsfeed;
import com.example.sieuthi.newsfeed.NewsfeedAdapter;
import com.example.sieuthi.product.Product;
import com.example.sieuthi.product.ProductInCartAdapter;
import com.example.sieuthi.product.ProductInCategoryAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    Context context;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();

        // Banner
        RecyclerView rcvBanner = (RecyclerView) view.findViewById(R.id.recyclerViewBanner);
        setRecyclerViewBanner(rcvBanner);

        // 3 danh mục sp nổi bật: bán chạy, hàng mới, tất cả sp
        RecyclerView rcvCatedoryHighlight = (RecyclerView) view.findViewById(R.id.recyclerViewListHighlight);
        setRecyclerViewCategoryHighlight(rcvCatedoryHighlight);

        // tin tức
        RecyclerView rcvNewsfeed = (RecyclerView) view.findViewById(R.id.recyclerViewNewsfeed);
        setRecyclerViewNewsfeed(rcvNewsfeed, 5);    // Hiện tối đa 5 tin tức

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

        // 4 Button điều hướng đầu tiên --> mở fragment/dialog tương ứng
        ImageButton btnSanPham = (ImageButton) view.findViewById(R.id.imageButtonSanPham);
        btnSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ImageButton btnHotline = (ImageButton) view.findViewById(R.id.imageButtonHotline);
        btnHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tedPermission();
            }
        });

        ImageButton btnBangTin = (ImageButton) view.findViewById(R.id.imageButtonBangTin);
        btnBangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogNewsfeedCT();
            }
        });

        ImageButton btnCuaHang = (ImageButton) view.findViewById(R.id.imageButtonCuaHang);
        btnCuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogStore();
            }
        });

        // sự kiện click xem thêm tin tức
        TextView tvXemThem = (TextView) view.findViewById(R.id.textViewNewsSeeMore);
        tvXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogNewsfeedCT();
            }
        });

        return view;
    }

    public void showDialogSearch() {
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

    private void tedPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openIntentCallPhone();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(context, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check();
    }

    private void openIntentCallPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + String.valueOf(R.string.store_hotline)));
        startActivity(intent);
    }

    private void showDialogStore() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewDialog = layoutInflater.inflate(R.layout.bottom_sheet_store, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(viewDialog);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) viewDialog.getParent());
        bottomSheetBehavior.setState(STATE_EXPANDED);
        bottomSheetDialog.show();

        CircleImageView circleImageView = (CircleImageView) viewDialog.findViewById(R.id.circleImageViewClose);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void showDialogCart() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewDialog = layoutInflater.inflate(R.layout.layout_cart, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(viewDialog);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) viewDialog.getParent());
        bottomSheetBehavior.setState(STATE_EXPANDED);

        // recycler view
        RecyclerView rcvListCart = (RecyclerView) viewDialog.findViewById(R.id.recyclerViewList);
        List<Product> listProductInCart = new ArrayList<>(getDataProductInCart());
        ProductInCartAdapter productInCartAdapter = new ProductInCartAdapter(context, listProductInCart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        rcvListCart.setAdapter(productInCartAdapter);
        rcvListCart.setLayoutManager(linearLayoutManager);

        // back
        ImageView imgBack = (ImageView) viewDialog.findViewById(R.id.imageViewBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void showDialogNewsfeedCT() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewDialog = layoutInflater.inflate(R.layout.layout_newsfeed_ct, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(viewDialog);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) viewDialog.getParent());
        bottomSheetBehavior.setState(STATE_EXPANDED);

        RecyclerView rcvNewsfeedCT = (RecyclerView) viewDialog.findViewById(R.id.recyclerViewNewsfeedCT);
        setRecyclerViewNewsfeed(rcvNewsfeedCT, -1);

        ImageView imgCart = (ImageView) viewDialog.findViewById(R.id.imageViewCart);
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCart();
            }
        });

        ImageView imgBack = (ImageView) viewDialog.findViewById(R.id.imageViewBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.cancel();
            }
        });

        bottomSheetDialog.show();
    }

    public void setRecyclerViewNewsfeed(RecyclerView rcvNewsfeed, int slMax) {

        // init
        List<Newsfeed> listNewsfeed = new ArrayList<>(getDataNewsfeed());
        NewsfeedAdapter newsfeedAdapter = new NewsfeedAdapter(context, listNewsfeed);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        if(slMax != -1) {
            newsfeedAdapter.setSlMax(slMax);
        }

        // set up
        rcvNewsfeed.setAdapter(newsfeedAdapter);
        rcvNewsfeed.setLayoutManager(linearLayoutManager);

    }

    public void setRecyclerViewCategoryHighlight(RecyclerView rcvCatedory) {

        // init
        List<Product> listProduct = new ArrayList<>(getDataProduct());
        List<Category> listCategory = new ArrayList<>(getDataCategoryHighlight(listProduct));
        CategoryHighlightAdapter categoryAdapter = new CategoryHighlightAdapter(context, listCategory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        // set up
        rcvCatedory.setAdapter(categoryAdapter);
        rcvCatedory.setLayoutManager(linearLayoutManager);

    }

    public void setRecyclerViewBanner(RecyclerView rcvBanner) {

        // init
        List<Banner> listBanner = new ArrayList<>(getDataBanner());
        BannerAdapter bannerAdapter = new BannerAdapter(context, listBanner);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        // set up
        rcvBanner.setAdapter(bannerAdapter);
        rcvBanner.setLayoutManager(linearLayoutManager);

    }

    // Get Database

    private List<Product> getDataProduct() {
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Hảo Hảo chua cay", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì tôm Kokomi", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì xaò Hảo Hảo", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì Ommi", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì Omachi", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì phở Cung Đình", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì phở bò Vifon", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì phở gà Vifon", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì phở vịt Hiếu Lợn", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì thịt bò", 10));
        listProduct.add(new Product("", R.drawable.product1, 5000, "Mì trứng xúc xích", 10));
        return listProduct;

    }

    private List<Banner> getDataBanner() {
        List<Banner> listBanner = new ArrayList<>();

        listBanner.add(new Banner(R.drawable.banner));
        listBanner.add(new Banner(R.drawable.banner));
        listBanner.add(new Banner(R.drawable.banner));
        listBanner.add(new Banner(R.drawable.banner));
        listBanner.add(new Banner(R.drawable.banner));
        listBanner.add(new Banner(R.drawable.banner));
        listBanner.add(new Banner(R.drawable.banner));
        listBanner.add(new Banner(R.drawable.banner));
        return listBanner;
    }

    private List<Category> getDataCategoryHighlight(List<Product> listProduct) {

        List<Category> listCategory = new ArrayList<>();
        listCategory.add(new Category("", R.drawable.category1, "Hàng mới về", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Bán chạy", listProduct));
        listCategory.add(new Category("", R.drawable.category1,"Tất cả sản phẩm", listProduct));
        return listCategory;
    }

    private List<Newsfeed> getDataNewsfeed() {
        List<Newsfeed> listNewsfeed = new ArrayList<>();

        String strContentTest = getResources().getString(R.string.large_text);
        listNewsfeed.add(new Newsfeed(R.drawable.banner, "Ngày hội giảm giá săn sale thả ga", "25/03/2022",
                "Từ ngày 25.03-31.03, chốt đơn ngay hàng trăm mặt hàng với giá siêu rẻ", strContentTest));
        listNewsfeed.add(new Newsfeed(R.drawable.news1, "Ngày hội giảm giá săn sale thả ga", "25/03/2022",
                "Từ ngày 25.03-31.03, chốt đơn ngay hàng trăm mặt hàng với giá siêu rẻ", strContentTest));
        listNewsfeed.add(new Newsfeed(R.drawable.banner, "Ngày hội giảm giá săn sale thả ga", "25/03/2022",
                "Từ ngày 25.03-31.03, chốt đơn ngay hàng trăm mặt hàng với giá siêu rẻ", strContentTest));
        listNewsfeed.add(new Newsfeed(R.drawable.news1, "Ngày hội giảm giá săn sale thả ga", "25/03/2022",
                "Từ ngày 25.03-31.03, chốt đơn ngay hàng trăm mặt hàng với giá siêu rẻ", strContentTest));
        listNewsfeed.add(new Newsfeed(R.drawable.banner, "Ngày hội giảm giá săn sale thả ga", "25/03/2022",
                "Từ ngày 25.03-31.03, chốt đơn ngay hàng trăm mặt hàng với giá siêu rẻ", strContentTest));
        listNewsfeed.add(new Newsfeed(R.drawable.news1, "Ngày hội giảm giá săn sale thả ga", "25/03/2022",
                "Từ ngày 25.03-31.03, chốt đơn ngay hàng trăm mặt hàng với giá siêu rẻ", strContentTest));
        listNewsfeed.add(new Newsfeed(R.drawable.banner, "Ngày hội giảm giá săn sale thả ga", "25/03/2022",
                "Từ ngày 25.03-31.03, chốt đơn ngay hàng trăm mặt hàng với giá siêu rẻ", strContentTest));
        listNewsfeed.add(new Newsfeed(R.drawable.news1, "Ngày hội giảm giá săn sale thả ga", "25/03/2022",
                "Từ ngày 25.03-31.03, chốt đơn ngay hàng trăm mặt hàng với giá siêu rẻ", strContentTest));
        listNewsfeed.add(new Newsfeed(R.drawable.banner, "Ngày hội giảm giá săn sale thả ga", "25/03/2022",
                "Từ ngày 25.03-31.03, chốt đơn ngay hàng trăm mặt hàng với giá siêu rẻ", strContentTest));
        listNewsfeed.add(new Newsfeed(R.drawable.news1, "Ngày hội giảm giá săn sale thả ga", "25/03/2022",
                "Từ ngày 25.03-31.03, chốt đơn ngay hàng trăm mặt hàng với giá siêu rẻ", strContentTest));

        return listNewsfeed;
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