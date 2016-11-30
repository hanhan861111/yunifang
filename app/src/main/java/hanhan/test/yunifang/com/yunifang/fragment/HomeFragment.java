package hanhan.test.yunifang.com.yunifang.fragment;

import android.view.View;
import android.widget.TextView;

import hanhan.test.yunifang.com.yunifang.base.BaseData;
import hanhan.test.yunifang.com.yunifang.base.BaseFragment;
import hanhan.test.yunifang.com.yunifang.utils.URLUtils;
import hanhan.test.yunifang.com.yunifang.view.ShowingPage;


/**
 * Created by zhiyuan on 16/9/28.
 */
public class HomeFragment extends BaseFragment {
    private String data;
    private MyHomeData myHomeData;

    @Override
    protected void onLoad() {
            //加载数据
        myHomeData = new MyHomeData();
        myHomeData.getData(URLUtils.homeUrl,URLUtils.homeArgs,0, BaseData.NORMALTIME);

    }

    @Override
    public View createSuccessView() {
        TextView textView=new TextView(getActivity());
        textView.setText(data);
        return textView;
    }
    class MyHomeData extends BaseData{



        @Override
        public void setResultData(String data) {
            //先设置数据
            HomeFragment.this.data=data;
            //再展示
            HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);

        }

        @Override
        protected void setResulttError(ShowingPage.StateType stateLoadError) {
            //失败的设置
            HomeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
        }
    }
}
