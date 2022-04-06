package com.example.sieuthi.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sieuthi.R;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    Context context;
    List<Banner> listBanner;

    public BannerAdapter(Context context, List<Banner> listBanner) {
        this.context = context;
        this.listBanner = listBanner;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_banner, parent, false);

        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Banner banner = (Banner) listBanner.get(position);
        if(banner != null) {
            holder.imgPhoto.setImageResource(banner.getImgPhoto());
        }
    }

    @Override
    public int getItemCount() {
        return listBanner .size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = (ImageView) itemView.findViewById(R.id.imageViewItemBanner);
        }
    }
}
