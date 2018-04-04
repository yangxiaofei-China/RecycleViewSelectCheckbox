package com.hanergy.popwindowlib.wheelpicker.popup;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hanergy.popwindowlib.R;
import com.hanergy.popwindowlib.wheelpicker.utils.ConvertUtils;



/**
 * 带确定及取消按钮的弹窗
 *
 * @author 李玉江[QQ:1032694760]
 * @since 2015/10/21
 */
@SuppressWarnings("WeakerAccess")
public abstract class ConfirmPopup<V extends View> extends BasicPopup<View> {
    protected boolean topLineVisible = true;
    protected int topLineColor = 0xFF33B5E5;
    protected int topLineHeightPixels = 1;//px
    protected int topBackgroundColor = Color.WHITE;
    protected int topHeight = 60;//dp
    protected int topPadding = 15;//dp
    protected int contentLeftAndRightPadding = 0;//dp
    protected int contentTopAndBottomPadding = 0;//dp
    protected boolean cancelVisible = true;
    protected CharSequence cancelText = "";
    protected CharSequence submitText = "";
    protected CharSequence titleText = "";
    protected int cancelTextColor = 0xFF33B5E5;
    protected int submitTextColor = 0xFF33B5E5;
    protected int shortcutTextColor = 0xFFc3c3c3;
    protected int titleTextColor = Color.BLACK;
    protected int pressedTextColor = 0XFF0288CE;
    protected int footerButtonBackgtoudColor = 0XFF0693ff;
    protected int cancelTextSize = 0;
    protected int submitTextSize = 0;
    protected int titleTextSize = 0;
    protected int backgroundColor = Color.WHITE;
    protected TextView cancelButton;
    protected Button submitButton;
    protected View titleView;
    protected View headerView, centerView, footerView;


    public ConfirmPopup(Activity activity) {
        super(activity);
        cancelText = activity.getString(android.R.string.cancel);
        submitText = activity.getString(android.R.string.ok);
    }

    /**
     * 设置顶部标题栏下划线颜色
     */
    public void setTopLineColor(@ColorInt int topLineColor) {
        this.topLineColor = topLineColor;
    }

    /**
     * 设置顶部标题栏下划线高度，单位为px
     */
    public void setTopLineHeight(int topLineHeightPixels) {
        this.topLineHeightPixels = topLineHeightPixels;
    }

    /**
     * 设置顶部标题栏背景颜色
     */
    public void setTopBackgroundColor(@ColorInt int topBackgroundColor) {
        this.topBackgroundColor = topBackgroundColor;
    }

    /**
     * 设置顶部标题栏高度（单位为dp）
     */
  /*  public void setTopHeight(@IntRange(from = 10, to = 80) int topHeight) {
        this.topHeight = topHeight;
    }
*/
    /**
     * 设置顶部按钮左边及右边边距（单位为dp）
     */
    public void setTopPadding(int topPadding) {
        this.topPadding = topPadding;
    }

    /**
     * 设置顶部标题栏下划线是否显示
     */
    public void setTopLineVisible(boolean topLineVisible) {
        this.topLineVisible = topLineVisible;
    }

    /**
     * 设置内容上下左右边距（单位为dp）
     */
    public void setContentPadding(int leftAndRight, int topAndBottom) {
        this.contentLeftAndRightPadding = leftAndRight;
        this.contentTopAndBottomPadding = topAndBottom;
    }

    /**
     * 设置顶部标题栏取消按钮是否显示
     */
    public void setCancelVisible(boolean cancelVisible) {
        if (null != cancelButton) {
            cancelButton.setVisibility(cancelVisible ? View.VISIBLE : View.GONE);
        } else {
            this.cancelVisible = cancelVisible;
        }
    }

    /**
     * 设置顶部标题栏取消按钮文字
     */
    public void setCancelText(CharSequence cancelText) {
        if (null != cancelButton) {
            cancelButton.setText(cancelText);
        } else {
            this.cancelText = cancelText;
        }
    }

    /**
     * 设置顶部标题栏取消按钮文字
     */
    public void setCancelText(@StringRes int textRes) {
        setCancelText(activity.getString(textRes));
    }

    /**
     * 设置顶部标题栏确定按钮文字
     */
    public void setSubmitText(CharSequence submitText) {
        if (null != submitButton) {
            submitButton.setText(submitText);
        } else {
            this.submitText = submitText;
        }
    }

    /**
     * 设置顶部标题栏确定按钮文字
     */
    public void setSubmitText(@StringRes int textRes) {
        setSubmitText(activity.getString(textRes));
    }

    /**
     * 设置顶部标题栏标题文字
     */
    public void setTitleText(CharSequence titleText) {
        if (titleView != null && titleView instanceof TextView) {
            ((TextView) titleView).setText(titleText);
        } else {
            this.titleText = titleText;
        }
    }

    /**
     * 设置顶部标题栏标题文字
     */
    public void setTitleText(@StringRes int textRes) {
        setTitleText(activity.getString(textRes));
    }

    /**
     * 设置顶部标题栏取消按钮文字颜色
     */
    public void setCancelTextColor(@ColorInt int cancelTextColor) {
        if (null != cancelButton) {
            cancelButton.setTextColor(cancelTextColor);
        } else {
            this.cancelTextColor = cancelTextColor;
        }
    }

    /**
     * 设置顶部标题栏确定按钮文字颜色
     */
    public void setSubmitTextColor(@ColorInt int submitTextColor) {
        if (null != submitButton) {
            submitButton.setTextColor(submitTextColor);
        } else {
            this.submitTextColor = submitTextColor;
        }
    }

    /**
     * 设置顶部标题栏标题文字颜色
     */
    public void setTitleTextColor(@ColorInt int titleTextColor) {
        if (null != titleView && titleView instanceof TextView) {
            ((TextView) titleView).setTextColor(titleTextColor);
        } else {
            this.titleTextColor = titleTextColor;
        }
    }

    /**
     * 设置底部按钮背景颜色
     */
    public void setFooterButtonBGColor(int footerButtonBackgtoudColor) {
        this.footerButtonBackgtoudColor = footerButtonBackgtoudColor;
    }
    /**
     * 设置按下时的文字颜色
     */
    public void setPressedTextColor(int pressedTextColor) {
        this.pressedTextColor = pressedTextColor;
    }

    /**
     * 设置顶部标题栏取消按钮文字大小（单位为sp）
     */
    public void setCancelTextSize(@IntRange(from = 10, to = 40) int cancelTextSize) {
        this.cancelTextSize = cancelTextSize;
    }

    /**
     * 设置顶部标题栏确定按钮文字大小（单位为sp）
     */
    public void setSubmitTextSize(@IntRange(from = 10, to = 40) int submitTextSize) {
        this.submitTextSize = submitTextSize;
    }

    /**
     * 设置顶部标题栏标题文字大小（单位为sp）
     */
    public void setTitleTextSize(@IntRange(from = 10, to = 40) int titleTextSize) {
        this.titleTextSize = titleTextSize;
    }

    /**
     * 设置选择器主体背景颜色
     */
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setTitleView(View titleView) {
        this.titleView = titleView;
    }

    public View getTitleView() {
        if (null == titleView) {
            throw new NullPointerException("please call show at first");
        }
        return titleView;
    }

    public TextView getCancelButton() {
        if (null == cancelButton) {
            throw new NullPointerException("please call show at first");
        }
        return cancelButton;
    }

    public TextView getSubmitButton() {
        if (null == submitButton) {
            throw new NullPointerException("please call show at first");
        }
        return submitButton;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
    }

    public void setFooterView(View footerView) {
        this.footerView = footerView;
    }

    /**
     * @see #makeHeaderView()
     * @see #makeCenterView()
     * @see #makeFooterView()
     */
    @Override
    protected final View makeContentView() {
        LinearLayout rootLayout = new LinearLayout(activity);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        rootLayout.setBackgroundColor(backgroundColor);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setGravity(Gravity.CENTER);
        rootLayout.setPadding(0, 0, 0, 0);
        rootLayout.setClipToPadding(false);
        View headerView = makeHeaderView();
        if (headerView != null) {
            rootLayout.addView(headerView);
        }
        if (topLineVisible) {
            View lineView = new View(activity);
            lineView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, topLineHeightPixels));
            lineView.setBackgroundColor(topLineColor);
            rootLayout.addView(lineView);
        }
        if (centerView == null) {
            centerView = makeCenterView();
        }
        int lr = 0;
        int tb = 0;
        if (contentLeftAndRightPadding > 0) {
            lr = ConvertUtils.toPx(activity, contentLeftAndRightPadding);
        }
        if (contentTopAndBottomPadding > 0) {
            tb = ConvertUtils.toPx(activity, contentTopAndBottomPadding);
        }
        centerView.setPadding(lr, tb, lr, tb);
        ViewGroup vg = (ViewGroup) centerView.getParent();
        if (vg != null) {
            //IllegalStateException: The specified child already has a parent
            vg.removeView(centerView);
        }
        rootLayout.addView(centerView, new LinearLayout.LayoutParams(MATCH_PARENT, 0, 1.0f));
        View footerView = makeFooterView();
        if (footerView != null) {
            rootLayout.addView(footerView);
        }
        return rootLayout;
    }
    protected View addTitleButton(String itemText){
        LinearLayout topButtonLayout = new LinearLayout(activity);
        topButtonLayout.setOrientation(LinearLayout.VERTICAL);
        topButtonLayout.setLayoutParams(new LinearLayout.LayoutParams(0, WRAP_CONTENT,1.0f));
        topButtonLayout.setGravity(Gravity.CENTER);
        TextView button = new TextView(activity);
        LinearLayout.LayoutParams cancelParams = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        button.setLayoutParams(cancelParams);
        button.setTextColor(ConvertUtils.toColorStateList(shortcutTextColor, pressedTextColor));
        button.setBackgroundResource(R.drawable.button_bg);
        button.setGravity(Gravity.CENTER);
        button.setText(itemText);
        button.setTextSize(14);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                onShortcutOptions(v);
            }
        });
        topButtonLayout.addView(button);
        return topButtonLayout;
    }
    @Nullable
    protected View makeHeaderView() {
        if (null != headerView) {
            return headerView;
        }
        LinearLayout topButtonLayout = new LinearLayout(activity);
        topButtonLayout.setOrientation(LinearLayout.HORIZONTAL);
        int height = ConvertUtils.toPx(activity, topHeight);
        topButtonLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
//        topButtonLayout.setBackgroundColor(topBackgroundColor);
        int paddingTopBouttom = ConvertUtils.toPx(activity, 22);
        topButtonLayout.setPadding(0,paddingTopBouttom,0,paddingTopBouttom);
        topButtonLayout.setGravity(Gravity.CENTER_VERTICAL);
        View day = addTitleButton(getContext().getString(R.string.today));
        View week = addTitleButton(getContext().getString(R.string.week));
        View month = addTitleButton(getContext().getString(R.string.month));
        View year = addTitleButton(getContext().getString(R.string.year));
        topButtonLayout.addView(day);
        topButtonLayout.addView(week);
        topButtonLayout.addView(month);
        topButtonLayout.addView(year);
        return topButtonLayout;
    }

    @NonNull
    protected abstract V makeCenterView();

    @Nullable
    protected View makeFooterView() {
        if (null != footerView) {
            return footerView;
        }
        LinearLayout footerLayout = new LinearLayout(activity);
        footerLayout.setOrientation(LinearLayout.HORIZONTAL);
        footerLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        int paddingLeftRight = ConvertUtils.toPx(activity, 16);
        int paddingBouttom = ConvertUtils.toPx(activity, 27);
        footerLayout.setPadding(paddingLeftRight,0,paddingLeftRight,paddingBouttom);
        footerLayout.setGravity(Gravity.CENTER);
        submitButton = new Button(activity);
        submitButton.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ConvertUtils.toPx(activity, 43)));
        submitButton.setBackgroundColor(footerButtonBackgtoudColor);
        submitButton.setTextColor(Color.WHITE);
        if (!TextUtils.isEmpty(submitText)) {
            submitButton.setText(submitText);
        }
        if (submitTextSize != 0) {
            submitButton.setTextSize(submitTextSize);
        }
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                onSubmit();
            }
        });
        footerLayout.addView(submitButton);
        return footerLayout;
    }

    protected void onSubmit() {

    }
    protected void onShortcutOptions(View view) {
    }

    protected void onCancel() {

    }

}
