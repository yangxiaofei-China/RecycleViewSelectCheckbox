package com.hanergy.popwindowlib.multiselect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

/**
 * @author yangxiaofei
 * @data 2017/12/3 18:10
 * @description RecyclerView 水平分割线
 */
public class DividerItemDecoration extends ItemDecoration {

    private int mOrientation = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int[] attrs= new int[]{
            android.R.attr.listDivider
    };

    public DividerItemDecoration(Context context, int orientation) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation){
        if(orientation!=LinearLayoutManager.HORIZONTAL&&orientation!=LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("哥们,逗我ma?非水平和线性的枚举类型");
        }
        this.mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {
        //2。调用这个绘制方法， RecyclerView会毁掉该绘制方法,需要你自己去绘制条目的间隔线
        if(mOrientation == LinearLayoutManager.VERTICAL){//垂直
            drawVertical(c,parent);
        }else{//水平
            drawHorizontal(c,parent);
        }

        super.onDraw(c, parent, state);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount ; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top , right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        // 画水平线
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount ; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top , right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               State state) {
        //1.调用此方法（首先会先获取条目之间的间隙宽度---Rect矩形区域）
        // 获得条目的偏移量(所有的条目都回调用一次该方法)
        if(mOrientation == LinearLayoutManager.VERTICAL){//垂直
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }else{//水平
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0 );
        }

    }

}
