package com.zasa.superduper.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import com.zasa.superduper.R;


import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    Context context;
    View view;
    private ArrayList<SliderApiModel> mData;

    public SliderAdapter(Context context, ArrayList<SliderApiModel> data) {
        this.mData = data;
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        this.context = parent.getContext();
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_slider_layout_item, parent, false);
        return new SliderAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        SliderApiModel LatestNewsResult = mData.get(position);
//        viewHolder.tv_title.setText(LatestNewsResult.getSlider_Title());
        String image = LatestNewsResult.getSlider_Image();
        Picasso.get().load(image)
                .into(viewHolder.imageViewBackground);

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public static class SliderAdapterVH extends ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView tv_title;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            tv_title = itemView.findViewById(R.id.tv_title);
            this.itemView = itemView;
        }

    }
}