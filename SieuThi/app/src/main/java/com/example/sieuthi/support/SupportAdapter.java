package com.example.sieuthi.support;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sieuthi.R;
import com.example.sieuthi.notification.Notification;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class SupportAdapter extends RecyclerView.Adapter<SupportAdapter.SupportViewHolder>{

    Context context;
    List<Support> listSupport;

    public SupportAdapter(Context context, List<Support> listSupport) {
        this.context = context;
        this.listSupport = listSupport;
    }

    @NonNull
    @Override
    public SupportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_support, parent, false);

        return new SupportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupportViewHolder holder, int position) {
        Support support = listSupport.get(position);
        if(support != null) {
            holder.imgHinh.setImageResource(support.getHinh());
            holder.tvTieuDe.setText(support.getTieude());
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showBottomSheetDialog(support);
                }
            });
        }
    }

    private void showBottomSheetDialog(Support support) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewDialog = layoutInflater.inflate(R.layout.bottom_sheet_support, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(viewDialog);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) viewDialog.getParent());
        bottomSheetBehavior.setState(STATE_EXPANDED);

        // Gán dữ liệu cho dialog
        TextView tvTieuDe = (TextView) viewDialog.findViewById(R.id.textViewTieuDe);
        TextView tvNoiDung = (TextView) viewDialog.findViewById(R.id.textViewND);

        tvTieuDe.setText(support.getTieude().toUpperCase(Locale.ROOT));
        tvNoiDung.setText(support.getNoidung());

        ImageView imgBack = (ImageView) viewDialog.findViewById(R.id.imageViewBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.show();
    }

    @Override
    public int getItemCount() {
        return listSupport.size();
    }

    class SupportViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinh;
        TextView tvTieuDe;
        RelativeLayout relativeLayout;

        public SupportViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHinh = (ImageView) itemView.findViewById(R.id.imageViewHinh);
            tvTieuDe = (TextView) itemView.findViewById(R.id.textViewTieuDe);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}
