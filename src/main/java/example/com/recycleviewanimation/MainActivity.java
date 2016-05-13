package example.com.recycleviewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements onDragStartListener {
    private List<String> mDatas;
    private RecyclerView recycleView;
    private ItemTouchHelper itemtouchhelper;
    private WhAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        recycleView = (RecyclerView) findViewById(R.id.recycleView);//初始化
        recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));//定义样式

        adapter = new WhAdapter(this, mDatas);//初始化adapter
        recycleView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new WhItemTouchCallback(adapter);//初始化ItemTouchHelper.
        itemtouchhelper = new ItemTouchHelper(callback);
        itemtouchhelper.attachToRecyclerView(recycleView);//这个必须加上.让辅助类和recycleview关联上
    }

    protected void initData() {//数据
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {//执行点击图标进行拖动的效果
        itemtouchhelper.startDrag(viewHolder);
    }
}
