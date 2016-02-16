

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
