package com.hkm.ezwebview.Util;

import android.app.Activity;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.webviewclients.ChromeLoader;
import com.hkm.ezwebview.webviewclients.HClient;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

/**
 * Created by hesk on 6/8/15.
 * This is the container library for display the efficient commands from the APIs
 */
public class Fx9C {
    public static void startToReveal(final ViewGroup view) {
        startToReveal(view, 1800);
    }

    public static void startToReveal(final ViewGroup view, final Runnable callback) {
        startToReveal(view, 1800, callback);
    }

    public static void startToRevealFast(final ViewGroup view) {
        startToReveal(view, 800);
    }

    public static void startToRevealFast(final ViewGroup view, final Runnable callback) {
        startToReveal(view, 800, callback);
    }

    public static void startToReveal(final ViewGroup view, final int timeinit, final Runnable callback) {
        final Handler h = new Handler();
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0f);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewCompat.animate(view).setDuration((long) timeinit)
                        .alpha(1f).withEndAction(callback);
            }
        }, 80);
    }

    public static void startToReveal(final ViewGroup view, final int timeinit) {
        final Handler h = new Handler();
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0f);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewCompat.animate(view).setDuration((long) timeinit)
                        .alpha(1f);
            }
        }, 80);
    }

    private static <T> Activity with(T context) throws Exception {
        if (context instanceof AppCompatActivity) {
            Activity g = (Activity) context;
            return g;
        }
        if (context instanceof Fragment) {
            Fragment g = (Fragment) context;
            return g.getActivity();
        }
        if (context instanceof android.support.v4.app.Fragment) {
            android.support.v4.app.Fragment g = (android.support.v4.app.Fragment) context;
            return g.getActivity();
        }
        throw new Exception("please enter an activity or fragment");
    }

    public static <T> void setup_content_block_wb(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String codeing,
            final HClient.Callback c
    ) throws Exception {
        setup_content_block_wb(context, frame_holder, block, codeing, 1500, c, null);
    }


    public static <T> void setup_content_block_wb(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String codeing
    ) throws Exception {
        setup_content_block_wb(context, frame_holder, block, codeing, 1500, null, null);
    }

    public static <T> void setup_content_block_wb(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String codeing,
            final HClient.Callback c,
            final Runnable cb
    ) throws Exception {
        setup_content_block_wb(context, frame_holder, block, codeing, 1500, c, cb);
    }

    public static <T> void setup_web_video(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final CircleProgressBar circlebar,
            final String codeing,
            final HClient.Callback c
    ) throws Exception {
        setup_web_video(context, frame_holder, block, circlebar, codeing, 2000, c, null);
    }

    public static <T> void setup_web_video(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final CircleProgressBar circlebar,
            final String codeing,
            final HClient.Callback c,
            final Runnable cb
    ) throws Exception {
        setup_web_video(context, frame_holder, block, circlebar, codeing, 2000, c, cb);
    }

    private static <T> void setup_content_block_wb(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String codeing,
            final int reveal_time,
            final HClient.Callback c,
            final Runnable callback_webview) throws Exception {
        final String cs = In32.cssByContentPost(with(context)) + codeing;
        HClient I2 = HClient.with(context, block);
        if (c != null) I2.setController(c);
        block.setWebViewClient(I2);
        block.setWebViewClient(I2);
        block.loadDataWithBaseURL("", cs, "text/html; charset=utf-8", "UTF-8", null);
        block.setVisibility(View.VISIBLE);
        if (callback_webview == null)
            startToReveal(frame_holder, reveal_time);
        else
            startToReveal(frame_holder, reveal_time, callback_webview);
    }

    private static <T> void setup_web_video(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView mVideo,
            final CircleProgressBar circlebar,
            final String codeing,
            final int reveal_time,
            final HClient.Callback c,
            final Runnable callback_webview) throws Exception {
        final String embeded_code = In32.cssByVideo(with(context)) + codeing;
        mVideo.setWebChromeClient(new ChromeLoader(circlebar));
        mVideo.getSettings().setPluginState(WebSettings.PluginState.ON);
        mVideo.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        HClient I2 = HClient.with(context, mVideo);
        if (c != null) I2.setController(c);
        mVideo.setWebViewClient(I2);
        mVideo.getSettings().setJavaScriptEnabled(true);
        mVideo.loadDataWithBaseURL("", embeded_code, "text/html; charset=utf-8", "UTF-8", null);
        mVideo.setVisibility(View.VISIBLE);
        if (callback_webview == null)
            startToReveal(frame_holder, reveal_time);
        else
            startToReveal(frame_holder, reveal_time, callback_webview);
    }

    public static void killWebView(NonLeakingWebView mWebView) {
        if (mWebView == null) return;
        //http://stackoverflow.com/questions/3815090/webview-and-html5-video
        if (mWebView.getVisibility() == View.GONE) {
            mWebView.loadUrl("about:blank");
            mWebView.destroy();
        }
    }

    public static void clearVideo(RelativeLayout frame, NonLeakingWebView mv) {
        if (hideSlider(frame)) {
            mv.loadDataWithBaseURL("", "", "text/html; charset=utf-8", "UTF-8", null);
            mv.setVisibility(View.INVISIBLE);
        }
    }


    private static boolean hideSlider(final Object view) {
        boolean killable = false;
        if (view == null) return killable;
        try {
            if (view instanceof RelativeLayout) {
                RelativeLayout v = (RelativeLayout) view;
                killable = v.getVisibility() != View.GONE;
                v.setVisibility(View.GONE);
            }
        } catch (Exception e) {
        }
        return killable;
    }
}
