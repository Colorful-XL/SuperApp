package com.example.supersdk.player;

import android.content.Intent;
import android.view.ViewGroup;

import com.example.supersdk.player.videoBean.AdValue;
import com.example.supersdk.util.ResponseEntityToModule;


/**
 * @function: 管理slot, 与外界进行通信
 */
public class VideoAdContext implements VideoAdSlot.AdSDKSlotListener {



    //the ad container
    private ViewGroup mParentView;

    private VideoAdSlot mAdSlot;
    private AdValue mInstance = null;
    //the listener to the app layer
    private AdContextInterface mListener;
    private CustomVideoView.ADFrameImageLoadListener mFrameLoadListener;

    public VideoAdContext(ViewGroup parentView, String instance,
                          CustomVideoView.ADFrameImageLoadListener frameLoadListener) {
        this.mParentView = parentView;
        this.mInstance = (AdValue) ResponseEntityToModule.
                parseJsonToModule(instance, AdValue.class);
        this.mFrameLoadListener = frameLoadListener;
        load();
    }

    /**
     * init the ad,不调用则不会创建videoview
     */
    public void load() {
        if (mInstance != null && mInstance.resource != null) {
            mAdSlot = new VideoAdSlot(mInstance, this, mFrameLoadListener);
        } else {
            mAdSlot = new VideoAdSlot(null, this, mFrameLoadListener); //创建空的slot,不响应任何事件
            if (mListener != null) {
                mListener.onAdFailed();
            }
        }
    }

    /**
     * release the video
     */
    public void destroy() {
        mAdSlot.destroy();
    }

    public void setAdResultListener(AdContextInterface listener) {
        this.mListener = listener;
    }

    /**
     * 根据滑动距离来判断是否可以自动播放, 出现超过50%自动播放，离开超过50%,自动暂停，在外部调用
     */
    public void updateAdInScrollView() {
        if (mAdSlot != null) {
            mAdSlot.updateAdInScrollView();
        }
    }

    @Override
    public ViewGroup getAdParent() {
        return mParentView;
    }

    @Override
    public void onAdVideoLoadSuccess() {
        if (mListener != null) {
            mListener.onAdSuccess();
        }
    }

    @Override
    public void onAdVideoLoadFailed() {
        if (mListener != null) {
            mListener.onAdFailed();
        }
    }

    @Override
    public void onAdVideoLoadComplete() {
    }

    @Override
    public void onClickVideo() {
        if (mAdSlot.isRealPause()){
            mAdSlot.resume();
        }else {
            mAdSlot.pause();
        }
    }

    public VideoAdSlot getAdSlot() {
        return mAdSlot;
    }
}