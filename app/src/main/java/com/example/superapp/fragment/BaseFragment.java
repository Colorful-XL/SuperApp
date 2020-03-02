package com.example.superapp.fragment;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


/**
 * 所有Fragment的基类
 * 为所有的Fragment提供一些公共的行为或事件
 */
public class BaseFragment extends Fragment {
    protected Activity mContext;



    public void doOpenCamera() {

    }

    public void doWriteSDCard() {

    }
}
