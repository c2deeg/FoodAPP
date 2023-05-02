package com.app.marier.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.app.marier.R;
import com.app.marier.StaticModel.PlanSubsViewPagerAdapterModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    private final ArrayList<PlanSubsViewPagerAdapterModel> arraylist;
    // Context object
    Context context;



    // Layout Inflater
    LayoutInflater mLayoutInflater;
    BottomSheetBehavior<View>bottomSheetBehavior;



    // Viewpager Constructor
    public ViewPagerAdapter(Context context, ArrayList<PlanSubsViewPagerAdapterModel> arraylist) {
        this.arraylist = arraylist;
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    public ViewPagerAdapter(@NotNull SplashActivity2 splashActivity2, @NotNull ArrayList<ViewPagerAdapterModel> arraylist) {
//        this.context = context;
//        this.arraylist = arraylist;
//        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }

    @Override
    public int getCount() {
        // return the number of images
        return arraylist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.planssubsviewpageritem, container, false);

        // referencing the image view from the item.xml file
        TextView tvtext1 = (TextView) itemView.findViewById(R.id.tvtext1);
        TextView tvtext2 = (TextView) itemView.findViewById(R.id.tvtext2);

        // setting the image in the imageView
        tvtext1.setText(arraylist.get(position).getTitle1());
        tvtext2.setText(arraylist.get(position).getTitle2());

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }



}
