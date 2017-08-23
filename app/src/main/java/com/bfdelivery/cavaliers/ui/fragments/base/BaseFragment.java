package com.bfdelivery.cavaliers.ui.fragments.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * 基类的fragment 自动消除对父容器的事件冒泡，请将默认的layout的rootLayout的id定义为container，不然会出现异常
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().getWindow().setSoftInputMode(getSoftMode());

        if (!isRecreateView()) {
            if (mRootView != null) {
                ViewGroup parent = (ViewGroup) mRootView.getParent();
                if (parent != null) parent.removeView(mRootView);
                onRootViewLayoutDone(mRootView, savedInstanceState);
                return mRootView;
            }
        }

        if (getCustomTheme() > 0) {
            Context themeContext = new ContextThemeWrapper(getActivity(), getCustomTheme());
            mRootView = LayoutInflater.from(themeContext).inflate(getLayoutResource(), null);
        } else {
            mRootView = inflater.inflate(getLayoutResource(), null);
        }

//		mRootView = rootView;

        if (!isPopUpTouchOutside()) {
            // 拦截touch事件，防止流窜到上层Activity
            mRootView.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }

        initializeView(mRootView, savedInstanceState);

        onRootViewLayoutDone(mRootView, savedInstanceState);

        initializeViewDone(mRootView, savedInstanceState);

        mRootView.setFocusableInTouchMode(true);
        mRootView.requestFocus();
        mRootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // 监听到返回按钮点击事件
                    onBackPressed();
                }
                return false;
            }
        });

        return mRootView;
    }

    public void onBackPressed() {
    }

    public void onNewIntent(Intent intent){

    }

    /**
     * only call this when first create views <br>
     * 销毁请放到 {@link #onDestroy()} 中，而不是 {@link #onDestroyView()}
     *
     * @param rootView           the same as {@link #onRootViewLayoutDone(View, Bundle)}
     * @param savedInstanceState {@link #onRootViewLayoutDone(View, Bundle)}
     */
    protected abstract void initializeView(View rootView, Bundle savedInstanceState);

    /**
     * only call this when first create views <br>
     * 销毁请放到 {@link #onDestroy()} 中，而不是 {@link #onDestroyView()}
     *
     * @param rootView           the same as {@link #onRootViewLayoutDone(View, Bundle)}
     * @param savedInstanceState {@link #onRootViewLayoutDone(View, Bundle)}
     */
    protected void initializeViewDone(View rootView, Bundle savedInstanceState) {
    }


    /**
     * get the root view
     */
    protected View getRootView() {
        return mRootView;
    }

    /**
     * 获得当前的Fragment布局文件
     */
    public abstract int getLayoutResource();

    /**
     * 当布局文件完全在屏幕上完成布局后
     */
    public void onRootViewLayoutDone(View rootView, Bundle savedInstanceState) {
    }

	/**
     * 是否拦截touch事件到上层视图，可重写此方法
     *
     * @return false means not, true means can be
     */
    protected boolean isPopUpTouchOutside() {
        return false;
    }

    public boolean isInterceptKeyEvent() {
        return false;
    }

    /**
     * 是否重新创建根View组件
     *
     * @return true 意味着每次都会创建新的view组件，false意味着，如果已经有新的组件了，就不会重新创建了
     */
    protected boolean isRecreateView() {
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    /**
     * 如果你想自定义一个主题用于fragment，那么，重写此函数，并且不要返回-1
     */
    protected int getCustomTheme() {
        return -1;
    }

    /**
     * 获取当前的键盘模式，默认为 {@link WindowManager.LayoutParams#SOFT_INPUT_ADJUST_PAN}
     *
     * @return {@link WindowManager.LayoutParams#SOFT_INPUT_ADJUST_PAN}
     */
    protected int getSoftMode() {
        return WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
    }

}
