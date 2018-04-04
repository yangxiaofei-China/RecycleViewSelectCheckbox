package com.hanergy.popwindowlib.multiselect;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hanergy.popwindowlib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxiaofei
 * @data 2017/12/3 18:22
 * @description PopWindow弹出框
 */
public class MultiSelectPopWindow {
    private PopupWindow mPopupWindow;
    private MultiSelectListAdapter adapter;
    private TextView selectedNumberTV;
    private TextView cancelBtn;
    private Button resetBtn;
    private Button confirmBtn;
    private TextView titleTV;
    private CheckBox selectAllBtn;
    private OnConfirmClickListener mOnConfirmListener;
    private ArrayList<Integer> mIndexList;
    private Builder mBuilder;
    private DividerItemDecoration decor;

    public interface OnConfirmClickListener{
        void onClick(ArrayList<Integer> indexList, ArrayList<String> selectedList);
    }

    static public class Builder{
        private Activity mActivity;
        private ArrayList<String> choiceNameList = new ArrayList<>();
        private String title;
        private String confirmText;
        private String cancelText;
        private String resetText;
        private boolean isOutsideTouchable;
        private View.OnClickListener mOnCancelListener;
        private OnConfirmClickListener mOnConfirmListener;
        private int mConfirmTextColor;
        private int mConfirmBGColor = 0XFF0693FF;
        protected int mConfirmBGGrayColor = 0XFFc3c3c3;
        private int mCancelTextColor;
        private int mResetTextColor;
        private int mResetBGColor;
        private int mTitleTextColor;
        private List<Integer> selectedIndexs = new ArrayList<>();

        public Builder(Activity mActivity){
            this.mActivity = mActivity;
        }
        public Builder setNameArray(ArrayList<String> list){
            this.choiceNameList = list;
            return this;
        }

        /**
         * set title
         * @param title
         * @return
         */
        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        /**
         * set confirm button text
         * @param str
         * @return
         */
        public Builder setConfirm(String str){
            this.confirmText = str;
            return this;
        }

        /**
         * set cacel button text
         * @param str
         * @return
         */
        public Builder setCancel(String str){
            this.cancelText = str;
            return this;
        }
        /**
         * set cacel button text
         * @param str
         * @return
         */
        public Builder setReset(String str){
            this.resetText = str;
            return this;
        }

        /**
         * set title's text color
         * @param color
         * @return
         */
        public Builder setTitleTextColor(int color){
            this.mTitleTextColor = color;
            return this;
        }

        /**
         * set confirm button's text color
         * @param color
         * @return
         */
        public Builder setConfirmTextColor(int color){
            this.mConfirmTextColor = color;
            return this;
        }
        /**
         * set confirm button's text color
         * @param color
         * @return
         */
        public Builder setResetTextColor(int color){
            this.mResetTextColor = color;
            return this;
        }

        /**
         * set cancel button's text color
         * @param color
         * @return
         */
        public Builder setCancelTextColor(int color){
            this.mCancelTextColor = color;
            return this;
        }
        /**
         * set confirm button's text color
         * @param color
         * @return
         */
        public Builder setConfirmBGColor(int color){
            this.mConfirmBGColor = color;
            return this;
        }
        /**
         * set confirm button's text color
         * @param color
         * @return
         */
        public Builder setResetBGColor(int color){
            this.mResetBGColor = color;
            return this;
        }
        public Builder setSelected(ArrayList<Integer> selectedIndexs){
            this.selectedIndexs = selectedIndexs;
            return this;
        }

        /**
         * set if can touchable if your finger touch outside
         * @param isOutsideTouchable
         * @return
         */
        public Builder setOutsideTouchable(boolean isOutsideTouchable){
            this.isOutsideTouchable = isOutsideTouchable;
            return this;
        }

        public MultiSelectPopWindow build(){
            return new MultiSelectPopWindow(this);
        }

        public Builder setConfirmListener(OnConfirmClickListener listener){
            this.mOnConfirmListener = listener;
            return this;
        }

        public Builder setCancelListener(View.OnClickListener listener){
            this.mOnCancelListener = listener;
            return this;
        }


    }
    private MultiSelectPopWindow(final Builder builder){
        mBuilder = builder;

        //init PopWindow
        View popview = View.inflate(builder.mActivity, R.layout.multi_select_list_popwindow, null);
        mPopupWindow = new PopupWindow(popview, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(builder.isOutsideTouchable);

        initViews(mPopupWindow.getContentView());

        initListener();

    }

    /**
     * init listener
     */
    private void initListener() {
        this.mOnConfirmListener = mBuilder.mOnConfirmListener;

        // change the background's color
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.mOnConfirmListener != null && mIndexList != null){
                    ArrayList<String> stringList = new ArrayList<>();
                    for (int i = 0; i<mIndexList.size(); i++) {
                        stringList.add(mBuilder.choiceNameList.get(mIndexList.get(i)));
                    }
                    mOnConfirmListener.onClick(mIndexList,stringList);
                }
                dismiss();
            }
        });

        // select all or cancel all
        selectAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                adapter.selectAll();
                if (selectAllBtn.isChecked()){
                    selectAllBtn.setSelected(true);
                    adapter.selectAll();
                }else {
                    selectAllBtn.setSelected(false);
                    adapter.cancelAll();
                }

            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAllBtn.setChecked(false);
                adapter.cancelAll();
            }
        });

        if (mBuilder.mOnCancelListener != null){
            cancelBtn.setOnClickListener(mBuilder.mOnCancelListener);
        }

        cancelBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return false;
            }
        });


        // change the badge number
        adapter.setOnSelectChangeListener(new MultiSelectListAdapter.OnSelectChangeListener() {

            @Override
            public void onChanged(ArrayList<Integer> indexList, int selectdNumber) {
                if (mBuilder.choiceNameList.size() == selectdNumber){
                    selectAllBtn.setSelected(true);
                }else {
                    selectAllBtn.setSelected(false);
                }
                if (selectedNumberTV == null)return;
                if (selectdNumber > 0) {
                    selectedNumberTV.setText(selectdNumber + "");
                    selectedNumberTV.setVisibility(View.VISIBLE);
                    setBackgroudColor(confirmBtn,mBuilder.mConfirmBGColor);
                    confirmBtn.setClickable(true);
                }else {
                    setBackgroudColor(confirmBtn,mBuilder.mConfirmBGGrayColor);
                    selectedNumberTV.setVisibility(View.GONE);
                    confirmBtn.setClickable(false);
                }

                mIndexList = indexList;

            }
        });

        adapter.setOnSelectAllListener(new MultiSelectListAdapter.OnSelectAllListener() {
            @Override
            public void onChanged(boolean isSelectedAll) {
                selectAllBtn.setChecked(isSelectedAll);
            }
        });
    }

    private void initViews(View root) {
        titleTV = (TextView) root.findViewById(R.id.title);
        cancelBtn = (TextView) root.findViewById(R.id.cancelBtn);
        resetBtn = (Button) root.findViewById(R.id.reset_btn);
        confirmBtn = (Button) root.findViewById(R.id.confirm_btn);
        selectedNumberTV = (TextView) root.findViewById(R.id.selectedNumber);
        selectAllBtn = (CheckBox) root.findViewById(R.id.selectAllBtn);

        setText(titleTV,mBuilder.title);
        setText(cancelBtn,mBuilder.cancelText);
        setText(resetBtn,mBuilder.resetText);
        setText(confirmBtn,mBuilder.confirmText);

        setTextColor(titleTV,mBuilder.mTitleTextColor);
        setTextColor(cancelBtn,mBuilder.mCancelTextColor);
        setTextColor(resetBtn,mBuilder.mResetTextColor);
        setTextColor(confirmBtn,mBuilder.mConfirmTextColor);

        setBackgroudColor(resetBtn,mBuilder.mResetBGColor);
        setBackgroudColor(confirmBtn,mBuilder.mConfirmBGColor);

        RecyclerView recyclerView = (RecyclerView) mPopupWindow.getContentView().findViewById(R.id.mRecycleView);
        adapter = new MultiSelectListAdapter(mBuilder.choiceNameList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mBuilder.mActivity.getApplication()));
        recyclerView.setAdapter(adapter);
        decor = new DividerItemDecoration(mBuilder.mActivity, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(decor);
        adapter.select(mBuilder.selectedIndexs);
        int choiceSize = mBuilder.choiceNameList.size();
        int selectSize = mBuilder.selectedIndexs.size();
        if (choiceSize == selectSize){
            selectAllBtn.setSelected(true);
            selectAllBtn.setChecked(true);
        }else {
            selectAllBtn.setSelected(false);
            selectAllBtn.setChecked(false);
        }

    }

    private void setText(TextView tv, String str){
        if (tv != null && str != null){
            tv.setText(str);
        }
    }

    private void setTextColor(TextView tv, int color){
        if (tv != null && color != 0){
            tv.setTextColor(color);
        }
    }
    private void setBackgroudColor(TextView tv, int color){
        if (tv != null && color != 0){
            tv.setBackgroundColor(color);
        }
    }

    public void dismiss() {
        if (mPopupWindow != null){
            mPopupWindow.dismiss();
        }
    }

    /**
     * parent is the popwindow show location
     * @param parent
     */
    public void show(View parent){
        if (mPopupWindow != null){
            backgroundAlpha(0.8f);
            mPopupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        }
    }

    /**
     * set background alpha
     * @param alpha
     */
    public void backgroundAlpha(float alpha) {
        try {
            WindowManager.LayoutParams lp = mBuilder.mActivity.getWindow().getAttributes();
            lp.alpha = alpha; //0.0-1.0
            mBuilder.mActivity.getWindow().setAttributes(lp);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
