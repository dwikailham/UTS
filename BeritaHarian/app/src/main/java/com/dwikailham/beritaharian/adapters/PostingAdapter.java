package com.dwikailham.beritaharian.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dwikailham.beritaharian.DetailPostingActivity;
import com.dwikailham.beritaharian.R;
import com.dwikailham.beritaharian.models.Constant;
import com.dwikailham.beritaharian.models.Posting;
import com.dwikailham.beritaharian.models.Session;
import com.dwikailham.beritaharian.models.Settings;

import java.util.List;

public class PostingAdapter extends RecyclerView.Adapter<PostingAdapter.ViewHolder> {
    private Context context;
    private List<Posting> postings;
    private int layout;
    private Settings settings;
    private Session session;

    public PostingAdapter(Context context, List<Posting> postings) {
        this.context = context;
        this.postings = postings;
        this.settings = new Settings(context);
        this.session = new Session(settings);
    }

    public PostingAdapter(Context context, List<Posting> postings, int layout) {
        this.context = context;
        this.postings = postings;
        this.layout = layout;
        this.settings = new Settings(context);
        this.session = new Session(settings);
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    @Override
    public int getItemViewType(int position) {
        return layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //switch (getItemViewType(i)) {
        switch (session.getLayout()) {
            case Constant.LAYOUT_MODE_GRID:
                View gridView = LayoutInflater.from(context)
                        .inflate(R.layout.item_posting_grid, viewGroup, false);
                return new GridViewHolder(gridView);

            default:
                View listView = LayoutInflater.from(context)
                        .inflate(R.layout.item_posting_list, viewGroup, false);
                return new ListViewHolder(listView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Posting posting = postings.get(i);
        viewHolder.onBindViewHolder(posting);
    }

    @Override
    public int getItemCount() {
        return (postings != null) ? postings.size() : 0;
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) { super(itemView); }

        protected abstract void onBindViewHolder(Posting posting);
    }

    public class ListViewHolder extends ViewHolder {

        TextView titleText;
        TextView dateText;
        ImageView perusahaanImage;
        TextView namaPerusahaanText;

        public ListViewHolder(@NonNull final View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_title);
            dateText = itemView.findViewById(R.id.text_date);
            perusahaanImage = itemView.findViewById(R.id.icon_perusahaan);
            namaPerusahaanText = itemView.findViewById(R.id.text_perusahaan);
            float textSize = settings.getTextSize();
            titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Posting posting = postings.get(getAdapterPosition());
                    Intent intent = new Intent(itemView.getContext(), DetailPostingActivity.class);
                    //intent.putExtra("JUDUL", posting.getJudul());
                    intent.putExtra("DATA_POSTING", posting);
                    itemView.getContext().startActivity(intent);

                    Log.d("POSISI_ITEM", postings.get(getAdapterPosition()).getJudul());
                }
            });
        }

        @Override
        protected void onBindViewHolder(Posting posting) {
            titleText.setText(posting.getJudul());
            dateText.setText(posting.getTanggal());
            namaPerusahaanText.setText(posting.getPerusahaan());
            int img = itemView.getResources().getIdentifier(posting.getGambar(),"drawable",itemView.getContext().getPackageName());
            perusahaanImage.setImageResource(img);
//            int img = getResources().getIdentifier(data.getGambar(),"drawable",getPackageName());
        }
    }

    public class GridViewHolder extends ViewHolder {

        TextView titleText;
        TextView contentText;
        ImageView icon;

        public GridViewHolder(@NonNull final View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_title);
            contentText = itemView.findViewById(R.id.text_content);
            icon = itemView.findViewById(R.id.icon_perusahaan);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Posting posting = postings.get(getAdapterPosition());
                    Intent intent = new Intent(itemView.getContext(), DetailPostingActivity.class);
                    //intent.putExtra("JUDUL", posting.getJudul());
                    intent.putExtra("DATA_POSTING", posting);
                    itemView.getContext().startActivity(intent);

                    Log.d("POSISI_ITEM", postings.get(getAdapterPosition()).getJudul());
                }
            });
        }

        @Override
        protected void onBindViewHolder(Posting posting) {
            titleText.setText(posting.getJudul());
            contentText.setText(posting.getPerusahaan());
            int img = itemView.getResources().getIdentifier(posting.getGambar(),"drawable",itemView.getContext().getPackageName());
            icon.setImageResource(img);
        }
    }
}