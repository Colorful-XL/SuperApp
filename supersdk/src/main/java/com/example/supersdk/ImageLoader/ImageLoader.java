package com.example.supersdk.ImageLoader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.supersdk.R;

import java.io.File;

public class ImageLoader {
    private static int placeHolder = R.drawable.default_user_avatar;
    private static int errorHolder = R.drawable.default_user_avatar;

    public static void load(Context context , String url , ImageView container ) {
        load(context , url , container ,placeHolder ,errorHolder ,false , -1,-1,false,DiskCacheStrategy.AUTOMATIC);
    }

    public static void load(Context context , File file , ImageView container ) {
        load(context , file , container ,placeHolder ,errorHolder ,false , -1,-1,false,DiskCacheStrategy.AUTOMATIC);
    }

    public static void load(Context context ,  byte[] bytes , ImageView container ) {
        load(context , bytes , container ,placeHolder ,errorHolder ,false , -1,-1,false,DiskCacheStrategy.AUTOMATIC);
    }


    public static void load(Context context, String url , ImageView container, int placeholderRes, int errorIdRes, boolean asBitmap, int height, int width, boolean skipMemoryCache, DiskCacheStrategy diskCacheStrategy) {
        RequestOptions options = new RequestOptions();
        if (width != -1 && height != -1) {
            options = options.skipMemoryCache(skipMemoryCache).diskCacheStrategy(diskCacheStrategy).error(errorIdRes).placeholder(placeholderRes).override(height , width);
        }else {
            options = options.skipMemoryCache(skipMemoryCache).diskCacheStrategy(diskCacheStrategy).error(errorIdRes).placeholder(placeholderRes);
        }
        if (asBitmap){
            Glide.with(context).asBitmap().load(url).apply(options).into(container);
        }else {
            Glide.with(context).load(url).apply(options).into(container);
        }
    }

    public static void load(Context context, File file, ImageView container, int placeholderRes, int errorIdRes, boolean asBitmap, int height, int width, boolean skipMemoryCache, DiskCacheStrategy diskCacheStrategy){
        RequestOptions options = new RequestOptions();
        options = options.skipMemoryCache(skipMemoryCache).diskCacheStrategy(diskCacheStrategy).error(errorIdRes).placeholder(placeholderRes).override(height , width);
        if (asBitmap){
            Glide.with(context).asBitmap().load(file).apply(options).into(container);
        }else {
            Glide.with(context).load(file).apply(options).into(container);
        }
    }

    public static void load(Context context, int ResId,  ImageView container, int placeholderRes, int errorIdRes, boolean asBitmap, int height, int width, boolean skipMemoryCache, DiskCacheStrategy diskCacheStrategy){
        RequestOptions options = new RequestOptions();
        options = options.skipMemoryCache(skipMemoryCache).diskCacheStrategy(diskCacheStrategy).error(errorIdRes).placeholder(placeholderRes).override(height , width);
        if (asBitmap){
            Glide.with(context).asBitmap().load(ResId).apply(options).into(container);
        }else {
            Glide.with(context).load(ResId).apply(options).into(container);
        }
    }
    public static void load(Context context, byte[] imageBytes, ImageView container, int placeholderRes, int errorIdRes, boolean asBitmap, int height, int width, boolean skipMemoryCache, DiskCacheStrategy diskCacheStrategy){

        RequestOptions options = new RequestOptions();
        options = options.skipMemoryCache(skipMemoryCache).diskCacheStrategy(diskCacheStrategy).error(errorIdRes).placeholder(placeholderRes).override(height , width);
        if (asBitmap){
            Glide.with(context).asBitmap().load(imageBytes).apply(options).into(container);
        }else {
            Glide.with(context).load(imageBytes).apply(options).into(container);
        }
    }


}
