package hanhan.test.yunifang.com.yunifang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hanhan.test.yunifang.com.yunifang.view.ShowingPage;

/**
 * Created by zhiyuan on 16/9/28.
 */
public abstract class BaseFragment extends Fragment {


    private ShowingPage showingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //进行加载

        showingPage = new ShowingPage(getContext()) {
            @Override
            protected void onload() {

            }

            @Override
            public View CreatSuccessView() {
                //因为我们也不知道如何展示--继续抽象
                return BaseFragment.this.createSuccessView();
            }
        };
        BaseFragment.this.onLoad();
        return showingPage;
    }

    /**
     * 加载
     */
    protected abstract void onLoad();

    /**
     * 展示成功界面
     *
     * @return
     */
    public abstract View createSuccessView();

    /**
     * 对外提供方法，调用展示界面
     *
     * @param stateType
     */
    public void showCurrentPage(ShowingPage.StateType stateType) {
        //调用showingPage的方法
        if (showingPage != null) {
            showingPage.showCurrentPage(stateType);
        }

    }


}
