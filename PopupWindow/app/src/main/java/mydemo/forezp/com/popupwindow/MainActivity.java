package mydemo.forezp.com.popupwindow;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener ,PopupWindow.OnDismissListener{


    private Button btn1;
    private Button btn2;
    public String selectStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        btn1=(Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2=(Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }

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



    @Override
    public void onDismiss() {
        if(!TextUtils.isEmpty(selectStr)){
            btn1.setText(selectStr);
        }
    }
}
