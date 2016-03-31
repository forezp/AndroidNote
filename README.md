# AndroidNote
this is a note for android note repostiory
I'll collect many study method of android.

### popupWindow两中常见写法

  @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                List<String> list=new ArrayList<String>(){
                    {
                        add("qqww");
                        add("qqww1");
                        add("qqww2");
                    }
                };

                new SelectionTemp(MainActivity.this,btn1,MainActivity.this,list,1).setOnDismissListener(this);
                break;

            case R.id.btn2:

                showpopup();
                break;
        }
    }
####方法1
这种相当于封装好了，很多Activity都可以用一个模板，推荐使用这种。
new SelectionTemp(MainActivity.this,btn1,MainActivity.this,list,1).setOnDismissListener(this);


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
    
    
    
     @Override
    public void onDismiss() {
        if(!TextUtils.isEmpty(selectStr)){
            btn1.setText(selectStr);
        }
    }



####方法2
 private void showpopup(){

        View contentView = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.pop_window, null);

        Button button = (Button) contentView.findViewById(R.id.btn_comfirm);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "button is pressed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(
               new BitmapDrawable());

        // 设置好参数之后再show
        popupWindow.showAsDropDown(btn2);

    }
    
    ## Fragment 向activity 传值，采用接口的方式
    
    /**
*  Fragment 想Ativity   传值，如果只是简单的用广播的方式是非常简单的
*   或者用开源框架EventBus，
*  该方法用回调 接口的方式 
/
1.定义接口
public interface IFragmentCallBack {
    public void callBackFun(Bundle arg0);
}
2. Activity  实现接口
public class MainActivity extends SlidingFragmentActivity implements IFragmentCallBack {
    @Override
    public void callBackFun(Bundle arg) {
        String city=arg.getString("city");
        tvCity.setText(city);
    }
}
3.Fragment 部分
public class MenuLeftFragment extends Fragment
{
    private IFragmentCallBack callBack;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callBack=(MainActivity)getActivity();
    }
	 private void setListener(){
        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String city=listAddCity.get(i);
                Bundle bundle=new Bundle();
                bundle.putString("city",city);
                callBack.callBackFun(bundle);
               
               
            }
        });
    }
}

如果对传值机制不了解或者很麻烦的话，建议使用广播或者框架EventBus.

###Sql学习，推荐看书《MySql入门很简单》

### 广播的注册方式
静态注册和代码注册，具体点代码
有序广播和普通广播。
####动态注册
Intent intent = new Intent("com.trilink.artapp.newStudentCount");  
		intent.putExtra("newStudentCount", String.valueOf(newStudentCount)); 
		sendBroadcast(intent);  


		//接收
		receiver = new MyReceiver();  
		IntentFilter filter = new IntentFilter();  
		filter.addAction("com.trilink.artapp.newStudentCount");  		          
		getActivity().registerReceiver(receiver, filter); 

		class MyReceiver extends BroadcastReceiver {  
				@Override  
				public void onReceive(Context context, Intent intent) {  
					String newStudentCount=intent.getStringExtra("newStudentCount");
					int newStudentNum=Integer.parseInt(newStudentCount);
					if(newStudentNum>0){
						backgroundDefaultBadge.setText(newStudentNum+""); 
						backgroundDefaultBadge.setTextSize(12);
						backgroundDefaultBadge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT); //默认值
						backgroundDefaultBadge.setBadgeMargin(5, 5);
						backgroundDefaultBadge.show();
					}else{
						backgroundDefaultBadge.hide();
					}
				}  

			}  
			
			####静态注册
			
			静态注册：在AndroidManifest.xml中用标签生命注册，并在标签内用标签设置过滤器。

　　<receiver android:name="myRecevice">    //继承BroadcastReceiver，重写onReceiver方法

　　　　<intent-filter>    

　　　　　　<action android:name="com.dragon.net"></action> //使用过滤器，接收指定action广播

　　　   </intent-filter>

　　</receiver> 
　　
　　
　class MyReceiver extends BroadcastReceiver{
　
　   onreceive(){
　   
　   }
　}
###gitbus 学习
多敲命令就会了。


    
