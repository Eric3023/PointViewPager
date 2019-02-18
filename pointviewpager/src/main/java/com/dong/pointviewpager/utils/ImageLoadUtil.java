package com.dong.pointviewpager.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.widget.LoopViewPager;
import com.squareup.picasso.Picasso;

/**
 * Created by Dong on 2018/3/20.
 */

public class ImageLoadUtil {

    public static void loadImage(LoopViewPagerBean bean, ImageView imageView, int imageScale, int defaultResource) {
        if (bean == null || imageView == null)
            return;
        String url = bean.getUrl();
        int resourceID = bean.getResourceID();
        if (!TextUtils.isEmpty(url)) {
            switch (imageScale) {
                case LoopViewPager.FIT_XY:
                    Picasso.get().load(url).fit().config(Bitmap.Config.RGB_565).placeholder(defaultResource).error(defaultResource).into(imageView);
                    break;
                case LoopViewPager.FIT_CENTER:
                    Picasso.get().load(url).centerInside().fit().config(Bitmap.Config.RGB_565).placeholder(defaultResource).error(defaultResource).into(imageView);
                    break;
                case LoopViewPager.CENTER_CROP:
                    Picasso.get().load(url).centerCrop().fit().config(Bitmap.Config.RGB_565).placeholder(defaultResource).error(defaultResource).into(imageView);
                    break;
                default:
                    Picasso.get().load(url).fit().config(Bitmap.Config.RGB_565).placeholder(defaultResource).error(defaultResource).into(imageView);
            }
        } else {
            switch (imageScale) {
                case LoopViewPager.FIT_XY:
                    Picasso.get().load(resourceID).fit().config(Bitmap.Config.RGB_565).placeholder(defaultResource).error(defaultResource).into(imageView);
                    break;
                case LoopViewPager.FIT_CENTER:
                    Picasso.get().load(resourceID).fit().centerInside().config(Bitmap.Config.RGB_565).placeholder(defaultResource).error(defaultResource).into(imageView);
                    break;
                case LoopViewPager.CENTER_CROP:
                    Picasso.get().load(resourceID).centerCrop().fit().config(Bitmap.Config.RGB_565).placeholder(defaultResource).error(defaultResource).into(imageView);
                    break;
                default:
                    Picasso.get().load(resourceID).fit().config(Bitmap.Config.RGB_565).placeholder(defaultResource).error(defaultResource).into(imageView);
            }
        }
    }
}
