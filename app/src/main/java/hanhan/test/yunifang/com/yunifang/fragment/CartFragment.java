package hanhan.test.yunifang.com.yunifang.fragment;

import android.view.View;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import hanhan.test.yunifang.com.yunifang.base.BaseFragment;
import hanhan.test.yunifang.com.yunifang.utils.LogUtils;
import hanhan.test.yunifang.com.yunifang.view.ShowingPage;

/**
 * Created by zhiyuan on 16/9/28.
 */
public class CartFragment extends BaseFragment {
    private String s;

    @Override
    protected void onLoad() {
        RequestParams re=new RequestParams("http://www.baidu.com");
        //去加载
        x.http().get(re, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                //先赋值---
                CartFragment.this.s=s;
                CartFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                LogUtils.e("AAAAA","success-------cart-"+s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }
            @Override
            public void onCancelled(CancelledException e) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    @Override
    public View createSuccessView() {

        LogUtils.e("AAAAA","create-------cart-"+s);
        TextView textView =new TextView(getContext());
        textView.setText(s);
        return textView;
    }
}
