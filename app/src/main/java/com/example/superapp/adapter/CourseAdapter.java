package com.example.superapp.adapter;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.superapp.R;
import com.example.superapp.bean.recommand.RecommandBodyValue;
import com.example.superapp.util.Util;
import com.example.supersdk.ImageLoader.ImageLoader;
import com.example.supersdk.player.AdContextInterface;
import com.example.supersdk.player.VideoAdContext;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends BaseMultiItemQuickAdapter<RecommandBodyValue, BaseViewHolder> {

    private static final int TYPE_NUM = 4;
    private static final int VIDEO_TYPE = 0x00;
    private static final int CARD_TYPE_ONE = 0x01;//多图
    private static final int CARD_TYPE_TWO = 0x02;//单图
    private static final int CARD_TYPE_THREE = 0x03;//ViewPager

    private VideoAdContext mAdsdkContext;

    public CourseAdapter(List<RecommandBodyValue> data) {
        super(data);
        addItemType(VIDEO_TYPE, R.layout.item_video_layout);
        addItemType(CARD_TYPE_ONE, R.layout.item_product_card_one_layout);
        addItemType(CARD_TYPE_TWO, R.layout.item_product_card_two_layout);
        addItemType(CARD_TYPE_THREE, R.layout.item_product_card_three_layout);
    }


    @Override
    protected void convert(BaseViewHolder helper, final RecommandBodyValue item) {
        switch (helper.getItemViewType()) {
            case VIDEO_TYPE: {
                ImageView logoView = helper.getView(R.id.item_logo_view);
                ImageLoader.load(mContext, item.logo, logoView);
                helper.setText(R.id.item_title_view, item.title);
                helper.setText(R.id.item_info_view, item.info.concat(mContext.getString(R.string.tian_qian)));
                helper.setText(R.id.item_footer_view, item.text);
                //为对应布局创建播放器
                RelativeLayout mVieoContentLayout = helper.getView(R.id.video_ad_layout);
                mAdsdkContext = new VideoAdContext(mVieoContentLayout,
                        new Gson().toJson(item), null);
                mAdsdkContext.setAdResultListener(new AdContextInterface() {
                    @Override
                    public void onAdSuccess() {
                    }

                    @Override
                    public void onAdFailed() {
                    }

                    @Override
                    public void onClickVideo(String url) {
                    }
                });
                break;
            }
            case CARD_TYPE_ONE: {
                ImageView logoView = helper.getView(R.id.item_logo_view);
                ImageLoader.load(mContext, item.logo, logoView);
                helper.setText(R.id.item_title_view, item.title);
                helper.setText(R.id.item_info_view, item.info.concat(mContext.getString(R.string.tian_qian)));
                helper.setText(R.id.item_footer_view, item.text);
                helper.setText(R.id.item_price_view, item.price);
                helper.setText(R.id.item_zan_view, item.zan);
                helper.setText(R.id.item_from_view, item.from);
                LinearLayout mProductLayout = helper.getView(R.id.product_photo_layout);
//                mProductLayout.setOnClickListener(v -> {
//                    Intent intent = new Intent(mContext, PhotoViewActivity.class);
//                    intent.putStringArrayListExtra(PhotoViewActivity.PHOTO_LIST, item.url);
//                    mContext.startActivity(intent);
//                });
                //动态的添加多个ImageView
                for (String url : item.url) {
                    mProductLayout.addView(createImageView(url));
                }
                break;
            }
            case CARD_TYPE_TWO: {
                ImageView logoView = helper.getView(R.id.item_logo_view);
                ImageLoader.load(mContext, item.logo, logoView);
                helper.setText(R.id.item_title_view, item.title);
                helper.setText(R.id.item_info_view, item.info.concat(mContext.getString(R.string.tian_qian)));
                helper.setText(R.id.item_footer_view, item.text);
                helper.setText(R.id.item_price_view, item.price);
                helper.setText(R.id.item_zan_view, item.zan);
                helper.setText(R.id.item_from_view, item.from);
                //为单个ImageView加载远程图片
                ImageView mProductView = helper.getView(R.id.product_photo_view);
                ImageLoader.load(mContext, item.url.get(0), mProductView);
                break;
            }
            case CARD_TYPE_THREE: {
                ViewPager mViewPager = helper.getView(R.id.pager);
                //add data
                ArrayList<RecommandBodyValue> recommandList = Util.handleData(item);
                mViewPager.setAdapter(new ViewPagerAdapter(mContext , recommandList));
                mViewPager.setPageMargin(Util.dip2px(mContext,12));
                mViewPager.setCurrentItem(recommandList.size()*100);
                break;
            }
        }
    }

    /**
     * 自动播放方法
     */
    public void updateVideoInScrollView(){
        if (mAdsdkContext != null) {
            mAdsdkContext.updateAdInScrollView();
        }
    }

    /**
     * 动态添加ImageView
     *
     * @param url
     * @return
     */
    private ImageView createImageView(String url) {
        ImageView photoView = new ImageView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Util.dip2px(mContext, 100),
                LinearLayout.LayoutParams.MATCH_PARENT);
        params.leftMargin = Util.dip2px(mContext, 5);
        photoView.setLayoutParams(params);
        ImageLoader.load(mContext, url, photoView);
        return photoView;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


}
