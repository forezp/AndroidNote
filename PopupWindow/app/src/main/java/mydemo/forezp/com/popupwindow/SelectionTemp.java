package mydemo.forezp.com.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.List;

/**
 * Created by b508a on 2016/2/19.
 */
public class SelectionTemp extends PopupWindow{

    private ListView listView;

    private View view;
    private int flag=0;
    private List<String> list;
    MainActivity ma;

    public SelectionTemp(Context mContext, View parent, final MainActivity ma,
                         final List<String> list, final int flag){
        view = View.inflate(mContext, R.layout.item_selection, null);

        listView=(ListView) view.findViewById(R.id.slist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectInfo=list.get(position);
                if(flag==1){
                    ma.selectStr=selectInfo;
                }
                dismiss();
            }
        });
        this.flag = flag;
        this.ma = ma;
        this.list = list;
        DropEditTextAdapter adapter=new DropEditTextAdapter(mContext);
        adapter.setList(list);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        setHeight(ViewGroup.LayoutParams.FILL_PARENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAsDropDown(parent,5,0);
        update();
    }

}
