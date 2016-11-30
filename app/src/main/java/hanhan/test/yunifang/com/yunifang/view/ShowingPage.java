package hanhan.test.yunifang.com.yunifang.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import hanyongguang1124.test.bwie.com.yunifang.R;
import hanhan.test.yunifang.com.yunifang.utils.CommonUtils;

/**
 * Created by Administrator on 2016/11/28.
 */
public abstract class ShowingPage extends FrameLayout implements View.OnClickListener {
    //指定的几个状态
    public static final int STATE_UNLOAD = 1;//未加载
    public static final int STATE_LOAD = 2;//加载
    public static final int STATE_LOAD_ERROR = 3;//加载出错
    public static final int STATE_LOAD_EMPTY = 4;//加载无数据
    public static final int STATE_LOAD_SUCCESS = 5;//加载成功

    //定义一个初始状态--当前是未加载状态
    public int currentState = STATE_UNLOAD;

    private View showingpage_unload;
    private View showingpage_load_empty;
    private View showingpage_load_error;
    private View showingpage_loading;
    private View showingpage_success;
    private LayoutParams layoutParams;
    //重新加载的按钮
    private Button bt_reload;

    //从代码中调用构造方法
    public ShowingPage(Context context) {
        super(context);
        //初始化
        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        if (showingpage_load_empty == null) {
            showingpage_load_empty = CommonUtils.inflate(R.layout.showingpage_load_empty);
            this.addView(showingpage_load_empty, layoutParams);

        }
        //初始化正在加载-并添加到布局中
        if (showingpage_loading == null) {
            showingpage_loading = CommonUtils.inflate(R.layout.showingpage_loading);
            this.addView(showingpage_loading, layoutParams);
        }
        //初始化未加载状态，并添加到布局中
//        if (showingpage_unload == null) {
//            showingpage_unload = CommonUtils.inflate(R.layout.showingpage_unload);
//            this.addView(showingpage_unload, layoutParams);
//        }
        //初始化加载出错，并添加到布局中
        if (showingpage_load_error == null) {
            showingpage_load_error = CommonUtils.inflate(R.layout.showingpage_load_error);
            this.addView(showingpage_load_error, layoutParams);
            bt_reload = (Button) showingpage_load_error.findViewById(R.id.bt_reload);
            bt_reload.setOnClickListener(this);
        }
        showPage();
        //数据加载  同样为抽象方法
        onload();


    }


    //提供一个请求结束之后 设置当前状态展示界面的方法
    public void showCurrentPage(StateType stateType) {
        //获取枚举中的数字并赋值给当前类型
        this.currentState =stateType.getCurrentState();
        //展示以下
        showPage();
    }


    protected abstract void onload();


    //添加展示
    private void showPage() {
        CommonUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                showUipage();
            }
        });


    }

    //在主线程展示界面
    private void showUipage() {
        //根据当前状态进行展示
        //未加载
//        if (showingpage_unload != null) {
//            showingpage_unload.setVisibility(currentState == STATE_UNLOAD ? View.VISIBLE : View.GONE);
//        }
        if (showingpage_loading != null) {
            showingpage_loading.setVisibility(currentState == STATE_LOAD ||currentState==STATE_UNLOAD? View.VISIBLE : View.GONE);
        }
        if (showingpage_load_empty != null) {
            showingpage_load_empty.setVisibility(currentState == STATE_LOAD_EMPTY ? View.VISIBLE : View.GONE);
        }
        if (showingpage_load_error != null) {
            showingpage_load_error.setVisibility(currentState == STATE_LOAD_ERROR ? View.VISIBLE : View.GONE);
        }
        //成功的状态 没有成功的页面
        if (showingpage_success == null && currentState == STATE_LOAD_SUCCESS) {
            //加载成功的界面   添加到当前的showingpager
            showingpage_success = CreatSuccessView();
            //添加到showingpager
            this.addView(showingpage_success, layoutParams);
        }
        //判断是否是成功状态
        if (showingpage_success != null) {
            showingpage_success.setVisibility(currentState == STATE_LOAD_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }

    //创建具体成功的界面     因为具体的成功界面不知道 该方法需为抽象
    public abstract View CreatSuccessView();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_reload:
                resetView();
                break;
        }

    }

    //重置
    private void resetView() {
        //1:重置状态
        if (currentState != STATE_UNLOAD) {
            currentState = STATE_UNLOAD;
        }
        //2:展示界面效果
        showPage();
        //3:重新加载
        onload();
    }
    //枚举类
    public enum  StateType{
        //请求类型
        STATE_LOAD_ERROR(3),STATE_LOAD_EMPTY(4),STATE_LOAD_SUCCESS(5);
        private final int currentState;

        StateType(int currentState){
         this.currentState=currentState;
        }
        public int getCurrentState(){
            return currentState;
        }
    }
}
