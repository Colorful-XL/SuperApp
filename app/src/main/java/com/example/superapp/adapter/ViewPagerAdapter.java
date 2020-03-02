package com.example.superapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.superapp.R;
import com.example.superapp.bean.recommand.RecommandBodyValue;
import com.example.supersdk.ImageLoader.ImageLoader;

import java.util.ArrayList;


public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<RecommandBodyValue> mData;
    private LayoutInflater mInflater;

    public ViewPagerAdapter(Context context , ArrayList<RecommandBodyValue> list){
        mContext = context;
        mData = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final RecommandBodyValue value = mData.get(position % mData.size());
        View rootView = mInflater.inflate(R.layout.item_hot_product_pager_layout , null);
        TextView titleView =rootView.findViewById(R.id.title_view);
        TextView infoView =  rootView.findViewById(R.id.info_view);
        TextView gonggaoView = rootView.findViewById(R.id.gonggao_view);
        TextView saleView = rootView.findViewById(R.id.sale_num_view);
        ImageView[] imageViews = new ImageView[3];
        imageViews[0] = rootView.findViewById(R.id.image_one);
        imageViews[1] = rootView.findViewById(R.id.image_two);
        imageViews[2] = rootView.findViewById(R.id.image_three);
//        rootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, CourseDetailActivity.class);
//                intent.putExtra(CourseDetailActivity.COURSE_ID, value.adid);
//                mContext.startActivity(intent);
//            }
//        });
        titleView.setText(value.title);
        infoView.setText(value.price);
        gonggaoView.setText(value.info);
        saleView.setText(value.text);
        for (int i = 0; i < imageViews.length; i++) {
            ImageLoader.load(mContext , value.url.get(i) ,imageViews[i]);
        }
        container.addView(rootView, 0);
        return rootView;
    }

    /**
     * 设置为最大数，可以无限循环
     * @return
     */
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
