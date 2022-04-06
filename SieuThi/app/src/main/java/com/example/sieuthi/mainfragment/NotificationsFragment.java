package com.example.sieuthi.mainfragment;

import static com.example.sieuthi.R.color.background_noti_2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sieuthi.R;
import com.example.sieuthi.category.Category;
import com.example.sieuthi.category.CategoryHighlightAdapter;
import com.example.sieuthi.notification.Notification;
import com.example.sieuthi.notification.NotificationAdapter;
import com.example.sieuthi.product.Product;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    Context context;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_notifications, container, false);
        context = view.getContext();

        // RecyclerView
        ArrayList<Notification> listNotification = new ArrayList<>(getDataNotification());

        RecyclerView rcv = (RecyclerView) view.findViewById(R.id.recyclerViewList);
        NotificationAdapter notificationAdapter = new NotificationAdapter(context, listNotification);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        rcv.setAdapter(notificationAdapter);
        rcv.setLayoutManager(linearLayoutManager);

        return view;
    }

    private List<Notification> getDataNotification() {
        List<Notification> list = new ArrayList<>();

        list.add(new Notification("Đơn hàng #932362 đã được giao thành công.", "02/12/2022", "02:08"));
        list.add(new Notification("Đơn hàng #932362 đã được giao thành công.", "02/12/2022", "02:08"));
        list.add(new Notification("Đơn hàng #932362 đã được giao thành công.", "02/12/2022", "02:08"));
        list.add(new Notification("Đơn hàng #932362 đã được giao thành công.", "02/12/2022", "02:08"));

        return list;

    }
}