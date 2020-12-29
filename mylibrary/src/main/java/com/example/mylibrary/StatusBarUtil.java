package com.example.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * @author
 * @date 2020/12/29 0029
 * @description 状态栏显示问题
 */
public class StatusBarUtil {
    private static StatusBarUtil statusBarUtil;

    private static Activity activity;

    /*
     * 单例模式（懒汉）
     * */
    public static StatusBarUtil initStatusBar(Activity act) {
        synchronized (StatusBarUtil.class) {
            if (statusBarUtil == null) {
                statusBarUtil = new StatusBarUtil();
                activity = act;
            }
        }
        return statusBarUtil;
    }
    /**
     * @methName: isFull:true状态栏背景透明，状态栏下方的布局向上移动statusBar的高度
     * false状态栏可直接设置颜色，状态栏下方的布局不会向上移动
     * view LinearLayout 添加statusBar的布局
     * isStatusView 是否显示创建的statusbar
     */
    public void isFullStatusBar(boolean isFull, View view,boolean isStatusView,int layoutColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isFull) {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (isStatusView){
                    setStatusBarLayout(view,layoutColor);
                }
            } else {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                activity.getWindow().setStatusBarColor(layoutColor);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (isStatusView){
                setStatusBarLayout(view,layoutColor);
            }else {

            }
        }
    }
    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    private void setStatusBarLayout(View view,int layoutColor){

        Log.d("dddddddddd",Build.VERSION.SDK_INT+"");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = getStatusBarHeight(activity);
        view.setBackgroundColor(layoutColor);
        view.setLayoutParams(layoutParams);
    }
}
