package com.yxf.recycleviewselectcheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hanergy.popwindowlib.multiselect.MultiSelectPopWindow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("商品"+i);
        }

        findViewById(R.id.select_list_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MultiSelectPopWindow.Builder(MainActivity.this).setNameArray(list).setConfirmListener(new MultiSelectPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onClick(ArrayList<Integer> indexList, ArrayList<String> selectedList) {
                    }
                }).setConfirm("确定").setTitle("商品选择").build().show(v);
            }
        });
    }
}
