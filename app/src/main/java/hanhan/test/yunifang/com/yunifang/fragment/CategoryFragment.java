package hanhan.test.yunifang.com.yunifang.fragment;

import android.view.View;

import hanhan.test.yunifang.com.yunifang.base.BaseFragment;
import hanhan.test.yunifang.com.yunifang.view.ShowingPage;


/**
 * Created by zhiyuan on 16/9/28.
 */
public class CategoryFragment extends BaseFragment {
    @Override
    protected void onLoad() {
        CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
    }
    @Override
    public View createSuccessView() {
        return null;
    }
}
