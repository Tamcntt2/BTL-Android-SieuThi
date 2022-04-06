package com.example.sieuthi.mainfragment;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sieuthi.R;
import com.example.sieuthi.notification.Notification;
import com.example.sieuthi.notification.NotificationAdapter;
import com.example.sieuthi.support.Support;
import com.example.sieuthi.support.SupportAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {

    Context context;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        context = view.getContext();

        // recycler view hỗ trợ
        ArrayList<Support> listSupport = new ArrayList<>(getDataSupport());

        RecyclerView rcv = (RecyclerView) view.findViewById(R.id.recyclerView);
        SupportAdapter supportAdapter = new SupportAdapter(context, listSupport);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        rcv.setAdapter(supportAdapter);
        rcv.setLayoutManager(linearLayoutManager);

        // đăng xuất
        TextView tvDangXuat = (TextView) view.findViewById(R.id.textViewDangXuat);
        tvDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // click thay đổi thông tin khi đã đăng nhập

        // đăng nhập/ đăng ký


        // lịch sử đơn hàng


        // cửa hàng
        RelativeLayout layoutAddress = (RelativeLayout) view.findViewById(R.id.layoutAddress);
        layoutAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogStore();
            }
        });

        // hotline
        RelativeLayout layoutHotline = (RelativeLayout) view.findViewById(R.id.layoutHotline);
        layoutHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tedPermission();
            }
        });

        return view;
    }

    private List<Support> getDataSupport() {
        ArrayList<Support> listSupport = new ArrayList<>();
        listSupport.add(new Support(R.drawable.ic_home, "Chính sách đổi trả hàng hóa", "Nội dung..."));
        listSupport.add(new Support(R.drawable.ic_home, "Chính sách đổi trả hàng hóa", getResources().getString(R.string.large_text)));
        listSupport.add(new Support(R.drawable.ic_home, "Chính sách đổi trả hàng hóa", getResources().getString(R.string.large_text)));
        listSupport.add(new Support(R.drawable.ic_home, "Chính sách đổi trả hàng hóa", getResources().getString(R.string.large_text)));
        listSupport.add(new Support(R.drawable.ic_home, "Chính sách đổi trả hàng hóa", getResources().getString(R.string.large_text)));
        listSupport.add(new Support(R.drawable.ic_home, "Chính sách đổi trả hàng hóa", getResources().getString(R.string.large_text)));
        listSupport.add(new Support(R.drawable.ic_home, "Chính sách đổi trả hàng hóa", getResources().getString(R.string.large_text)));
        listSupport.add(new Support(R.drawable.ic_home, "Chính sách đổi trả hàng hóa", getResources().getString(R.string.large_text)));
        listSupport.add(new Support(R.drawable.ic_home, "Chính sách đổi trả hàng hóa", getResources().getString(R.string.large_text)));
        listSupport.add(new Support(R.drawable.ic_home, "Chính sách đổi trả hàng hóa", getResources().getString(R.string.large_text)));

        return listSupport;
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

}