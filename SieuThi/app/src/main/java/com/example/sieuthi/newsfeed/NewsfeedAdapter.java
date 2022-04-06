package com.example.sieuthi.newsfeed;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static java.lang.Math.min;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sieuthi.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.List;
import java.util.Locale;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewsfeedAdapter extends RecyclerView.Adapter<NewsfeedAdapter.NewsfeedViewHolder> {

    Context context;
    List<Newsfeed> listNews;
    int slMax; // số lượng item tối đa hiển thị

    public NewsfeedAdapter(Context context, List<Newsfeed> listNews) {
        this.context = context;
        this.listNews = listNews;
        slMax = -1;
    }

    public void setSlMax(int slMax) {
        this.slMax = slMax;
    }

    @NonNull
    @Override
    public NewsfeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_newsfeed, parent, false);

        return new NewsfeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsfeedViewHolder holder, int position) {

        Newsfeed newsfeed = (Newsfeed) listNews.get(position);

        if(newsfeed != null) {
            holder.imgPhoto.setImageResource(newsfeed.getImgPhoto());
            holder.tvName.setText(newsfeed.getTitle().toUpperCase(Locale.ROOT));
            holder.tvCalender.setText(newsfeed.getTime());
            holder.tvShortContent.setText(newsfeed.getShortContent());

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnClickItemNewsfeed(newsfeed);
                }
            });

        }
    }

    private void setOnClickItemNewsfeed(Newsfeed newsfeed) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewDialog = layoutInflater.inflate(R.layout.bottom_sheet_newsfeed, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(viewDialog);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) viewDialog.getParent());
        bottomSheetBehavior.setState(STATE_EXPANDED);

        // Gán dữ liệu cho dialog
        ImageView imgPhoto = (ImageView) viewDialog.findViewById(R.id.imageViewNews);
        TextView tvTitle = (TextView) viewDialog.findViewById(R.id.textViewNewsName);
        TextView tvTime = (TextView) viewDialog.findViewById(R.id.textViewNewsCalendar);
        TextView tvContent = (TextView) viewDialog.findViewById(R.id.textViewContent);
        CircleImageView btnClose = (CircleImageView) viewDialog.findViewById(R.id.circleImageViewClose);

        imgPhoto.setImageResource(newsfeed.getImgPhoto());
        tvTitle.setText(newsfeed.getTitle());
        tvTime.setText(newsfeed.getTime());
        tvContent.setText(newsfeed.getContent());

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.show();

    }

    @Override
    public int getItemCount() {
        if(slMax == -1) {
            return listNews.size();
        }
        return min(listNews.size(), slMax);
    }

    public class NewsfeedViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvName;
        TextView tvCalender;
        TextView tvShortContent;
        LinearLayout linearLayout;

        public NewsfeedViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutNewsfeed);
            imgPhoto = (ImageView) itemView.findViewById(R.id.imageViewNews);
            tvName = (TextView) itemView.findViewById(R.id.textViewNewsName);
            tvCalender = (TextView) itemView.findViewById(R.id.textViewNewsCalendar);
            tvShortContent = (TextView) itemView.findViewById(R.id.textViewShortContent);
        }
    }
}
