package com.hanergy.popwindowlib.multiselect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.davidecirillo.multichoicerecyclerview.MultiChoiceAdapter;
import com.hanergy.popwindowlib.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangxiaofei
 * @data 2017/12/3 18:17
 * @description RecyclerView适配器
 */
public class MultiSelectListAdapter extends MultiChoiceAdapter<MultiSelectListAdapter.ChoiceViewHolder> {

    private ArrayList<String> choiceNames = new ArrayList<>();
    private Boolean[] selectStates;
    private OnSelectChangeListener mOnSelectChangeListener;
    private OnSelectAllListener mOnSelectAllListener;

    /**
     * when you select the choices,it will be called
     */
    public interface OnSelectChangeListener{
        void onChanged(ArrayList<Integer> indexList, int selectedNumber);
    }

    /**
     * it will be auto called when you select all choices
     */
    public interface OnSelectAllListener{
        void onChanged(boolean isSelectedAll);
    }

    public MultiSelectListAdapter(ArrayList<String> list){
        choiceNames = list;

        //init the select state array
        if (choiceNames != null && choiceNames.size() >0) {
            selectStates = new Boolean[choiceNames.size()];
            Arrays.fill(selectStates,false);
        }
    }

    @Override
    public ChoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChoiceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.muli_select_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final ChoiceViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        // change the state
        holder.choiceNameBtn.setText(choiceNames.get(position));
        holder.choiceNameBtn.setSelected(selectStates[position]);
        holder.choiceNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStates[position] = !selectStates[position];
                holder.choiceNameBtn.setSelected(selectStates[position]);

                if (mOnSelectChangeListener != null){
                    mOnSelectChangeListener.onChanged(getSelectedPosition(),getSelectedNumber());
                }

                if (mOnSelectAllListener != null){
                    int num = getSelectedNumber();
                    if (num == choiceNames.size() && choiceNames.size() >0){
                        mOnSelectAllListener.onChanged(true);
                    } else {
                        mOnSelectAllListener.onChanged(false);
                    }
                }
            }
        });
    }

    /**
     * when you select the item,it will be called
     * @param holder
     * @param position
     * @return
     */
    @Override
    protected View.OnClickListener defaultItemViewClickListener(final ChoiceViewHolder holder, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStates[position] = !selectStates[position];
                holder.choiceNameBtn.setSelected(selectStates[position]);
                if (mOnSelectChangeListener != null){
                    mOnSelectChangeListener.onChanged(getSelectedPosition(),getSelectedNumber());
                }

                if (mOnSelectAllListener != null){
                    int num = getSelectedNumber();
                    if (num == choiceNames.size() && choiceNames.size() >0){
                        mOnSelectAllListener.onChanged(true);
                    } else {
                        mOnSelectAllListener.onChanged(false);
                    }
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return choiceNames.size();
    }

    /**
     * get the indexes in an array which you had selected
     * 获取您所选择的数组中的索引
     * @return
     */
    public ArrayList<Integer> getSelectedPosition(){
        ArrayList<Integer> result = new ArrayList<>();
        if (selectStates == null)return result;
        for (int i = 0; i<selectStates.length ; i++){
            if (selectStates[i]){
                result.add(i);
            }
        }
        return result;
    }

    /**
     * get the size of you had selected
     *
     * @return
     */
    public int getSelectedNumber(){
        return getSelectedPosition().size();
    }

    /**
     * 默认选择的index
     */
    public void select(List<Integer> selectIndexs){
        if (selectIndexs == null || selectIndexs.size() == 0)
            return;
        if (selectStates != null) {
            for (Integer index : selectIndexs){
                selectStates[index] = true;
            }
            notifyDataSetChanged();
            if (mOnSelectChangeListener != null){
                mOnSelectChangeListener.onChanged(getSelectedPosition(),getSelectedNumber());
            }
        }
    }
    /**
     * select all and change the states
     */
    public void selectAll(){
        if (selectStates != null) {
            Arrays.fill(selectStates, true);
            notifyDataSetChanged();
            if (mOnSelectChangeListener != null){
                mOnSelectChangeListener.onChanged(getSelectedPosition(),getSelectedNumber());
            }
        }
    }

    /**
     * cacel select all and change the states
     */
    public void cancelAll(){
        if (selectStates != null) {
            Arrays.fill(selectStates, false);
            notifyDataSetChanged();
            if (mOnSelectChangeListener != null){
                mOnSelectChangeListener.onChanged(getSelectedPosition(),getSelectedNumber());
            }
        }
    }


    public void setOnSelectChangeListener(OnSelectChangeListener listener){
        mOnSelectChangeListener = listener;
    }

    public void setOnSelectAllListener(OnSelectAllListener listener){
        mOnSelectAllListener = listener;
    }


    /**
     * viewHolder
     */
    public class ChoiceViewHolder extends RecyclerView.ViewHolder {

        public TextView choiceNameBtn;

        public ChoiceViewHolder(View itemView) {
            super(itemView);
            choiceNameBtn = (TextView) itemView.findViewById(R.id.choiceNameBtn);
        }
    }
}
