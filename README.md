### [个人CSDN博客地址](http://blog.csdn.net/u014408122)

> *浮躁的时候就应该休息一下了，调节好心情工作事半功倍。*

### 实现的功能

> 平时开发项目中经常遇到弹出式多选列表，现将其总结了一下

>>  1.弹框样式显示列表<br>
>>  2.实现多选、全选样式、清空选择功能<br>
>>  3.显示选择的个数，按钮状态跟随选择动态调整<br>

![这里添加图片](https://github.com/yangxiaofei-China/RecycleViewSelectCheckbox/blob/master/select.gif)



### 使用

#### 1.在app gradle添加依赖
```
dependencies {
    compile project(path: ':PopWindowLib')
}
```
#### 2.在settings.gradle中添加
```
include ':app', ':PopWindowLib'
```
#### 3.调用方法
```
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
```
