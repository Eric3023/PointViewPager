package com.dong.pointviewpager.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.widget.LoopViewPager;

import me.panpf.sketch.Sketch;
import me.panpf.sketch.SketchImageView;

/**
 * Created by Dong on 2018/3/20.
 */

public class ImageLoadUtil {

    public static void loadImage(Context context, LoopViewPagerBean bean, SketchImageView imageView, int defaultResource) {
        if (bean == null || imageView == null)
            return;
        String url = bean.getUrl();
        int resourceID = bean.getResourceID();
        imageView.getOptions().setDecodeGifImage(true);
        if (!TextUtils.isEmpty(url)) {
            Sketch.with(context).display(url, imageView)
                    .loadingImage(defaultResource)
                    .errorImage(defaultResource)
                    .decodeGifImage()
                    .commit();
        } else {
            Sketch.with(context).displayFromResource(resourceID, imageView)
                    .loadingImage(defaultResource)
                    .errorImage(defaultResource)
                    .decodeGifImage()
                    .commit();
        }
    }
}
