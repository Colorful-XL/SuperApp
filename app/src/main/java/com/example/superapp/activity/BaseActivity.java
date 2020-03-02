package com.example.superapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public abstract class BaseActivity extends AppCompatActivity {
    public static String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivityPermissionsDispatcher.requestPermissionWithPermissionCheck(BaseActivity.this);
    }
    @NeedsPermission({Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA,Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.VIBRATE,Manifest.permission.WAKE_LOCK,Manifest.permission.VIBRATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    public void requestPermission(){

        Log.d(TAG,"------------授权成功--------");
        Toast.makeText(this,"授权成功!",Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale({Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA,Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.VIBRATE,Manifest.permission.WAKE_LOCK,Manifest.permission.VIBRATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    public void showDialog(final PermissionRequest request){
        new AlertDialog.Builder(this)
                .setMessage("是否授权手机权限？")
                .setPositiveButton("授权", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .show();
    }

    /**
     * 拒绝授权
     */
    @OnPermissionDenied({Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA})
    public void deniedPermission(){
        Log.d(TAG,"拒绝授权");
        Toast.makeText(this,"授权失败!",Toast.LENGTH_SHORT).show();

    }

    /**
     * 不再询问
     */
    @OnNeverAskAgain({Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA})
    public void neverAskAgain(){
        Log.d(TAG,"不再询问");
        Toast.makeText(this,"需要授权!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i(TAG, "onRequestPermissionsResult requestCod = " + requestCode + ", grantResults = " + grantResults);
        BaseActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
