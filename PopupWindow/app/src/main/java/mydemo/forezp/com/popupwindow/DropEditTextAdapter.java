package mydemo.forezp.com.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DropEditTextAdapter extends BaseAdapter{
	private Context context;
	private List<String> list=new ArrayList<String>();
	public DropEditTextAdapter(Context context){
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(null==convertView){
			convertView=LayoutInflater.from(context).inflate(R.layout.item_drop_edittext, null);
			holder=new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.tv.setText(list.get(position));
		return convertView ;
		// TODO Auto-generated method stub


	}

	class ViewHolder {
		private TextView tv;
		public ViewHolder(View convertView){
			this.tv=(TextView) convertView.findViewById(R.id.tv_drop_edittext);
		}
	}
	public void setList(List<String> list){
		this.list=list;
	}
}
