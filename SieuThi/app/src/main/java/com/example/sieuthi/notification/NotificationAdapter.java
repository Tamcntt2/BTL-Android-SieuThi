package com.example.sieuthi.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sieuthi.R;
import com.example.sieuthi.banner.Banner;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    Context context;
    List<Notification> listNotification;

    public NotificationAdapter(Context context, List<Notification> listNotification) {
        this.context = context;
        this.listNotification = listNotification;
    }

    @NonNull
    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_notification, parent, false);

        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = (Notification) listNotification.get(position);
        if(notification != null) {
            holder.tvND.setText(notification.getStrND());
            holder.tvNgay.setText(notification.getStrNgay());
            holder.tvGio.setText(notification.getStrGio());
        }
    }

    @Override
    public int getItemCount() {
        return listNotification.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView tvND;
        TextView tvNgay;
        TextView tvGio;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            tvND = (TextView) itemView.findViewById(R.id.textViewND);
            tvNgay = (TextView) itemView.findViewById(R.id.textViewDay);
            tvGio = (TextView) itemView.findViewById(R.id.textViewHour);
        }
    }
}
