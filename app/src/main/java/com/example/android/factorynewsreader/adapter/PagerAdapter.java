package com.example.android.factorynewsreader.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.factorynewsreader.DescriptionActivity;
import com.example.android.factorynewsreader.R;

import java.util.ArrayList;

public class PagerAdapter extends android.support.v4.view.PagerAdapter implements View.OnClickListener{

    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();
    private ImageView arrow_back;
    private Context context;




    public PagerAdapter(ArrayList<String> mTitle, ArrayList<String> mImages, ArrayList<String> mDescription, Context context) {
        this.mTitle = mTitle;
        this.mImages = mImages;
        this.mDescription = mDescription;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.view_pager_item, container, false);

        arrow_back = layout.findViewById(R.id.arrow_back);
        arrow_back.setOnClickListener(this);

        TextView titleDescription = layout.findViewById(R.id.title_description);
        titleDescription.setText(mTitle.get(position));

        ImageView imageView = layout.findViewById(R.id.image_view);
        Glide.with(context)
                .asBitmap()
                .load(mImages.get(position))
                .into(imageView);

        TextView title = layout.findViewById(R.id.title);
        title.setText(mTitle.get(position));

        TextView description = layout.findViewById(R.id.description);
        description.setText(mDescription.get(position));

        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.arrow_back:
                DescriptionActivity descriptionActivity = (DescriptionActivity) context;
                descriptionActivity.finish();
                break;
        }
    }
}
