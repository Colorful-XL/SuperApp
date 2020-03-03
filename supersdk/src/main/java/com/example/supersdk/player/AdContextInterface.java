package com.example.supersdk.player;

/**
 * @function: 最终通知应用层播放是否成功
 */
public interface AdContextInterface {

    void onAdSuccess();

    void onAdFailed();

    void onClickVideo(String url);
}
