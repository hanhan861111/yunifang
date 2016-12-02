package hanhan.test.yunifang.com.yunifang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import hanhan.test.yunifang.com.yunifang.Bean.HomeRoot;
import hanhan.test.yunifang.com.yunifang.base.BaseData;
import hanhan.test.yunifang.com.yunifang.base.BaseFragment;
import hanhan.test.yunifang.com.yunifang.utils.CommonUtils;
import hanhan.test.yunifang.com.yunifang.utils.URLUtils;
import hanhan.test.yunifang.com.yunifang.view.MyRoolViewPager;
import hanhan.test.yunifang.com.yunifang.view.ShowingPage;
import hanyongguang1124.test.bwie.com.yunifang.R;


/**
 * Created by zhiyuan on 16/9/28.
 */
public class HomeFragment extends BaseFragment implements SpringView.OnFreshListener {
    private String data;
    private MyHomeData myHomeData;
    private ViewPager viewpager;
    private HomeRoot homeRoot;
    private ArrayList<String> imgUrlList = new ArrayList<>();
    private ArrayList<ImageView> dotListt = new ArrayList<>();
    private MyRoolViewPager myrootviewpager;
    private int []dotarray={R.mipmap.zaker_content_praise,R.mipmap.zaker_content_praise_press};
    private LinearLayout ll_dots;

    @Override
    protected void onLoad() {
        //加载数据
        myHomeData = new MyHomeData();
        myHomeData.getData(URLUtils.homeUrl, URLUtils.homeArgs, 0, BaseData.NORMALTIME);
    }

    @Override
    public View createSuccessView() {
        View view = CommonUtils.inflate(R.layout.homefragment);
        myrootviewpager = (MyRoolViewPager) view.findViewById(R.id.myrootviewpager);
        ll_dots = (LinearLayout) view.findViewById(R.id.ll_dots);
        SpringView springView = (SpringView) view.findViewById(R.id.springView);
        DefaultHeader defaultHeader = new DefaultHeader(getActivity());
        springView.setHeader(defaultHeader);
        springView.setListener(this);
        springView.setType(SpringView.Type.FOLLOW);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Gson gson = new Gson();
        homeRoot = gson.fromJson(data, HomeRoot.class);
        List<HomeRoot.DataBean.Ad1Bean> ad1 = homeRoot.getData().getAd1();
        imgUrlList.clear();
        for (int i = 0; i < ad1.size(); i++) {
            imgUrlList.add(ad1.get(i).getImage());
        }
        for (int i = 0; i <ad1.size() ; i++) {
            ImageView img=new ImageView(getActivity());
            if(i==0){
                img.setImageResource(R.mipmap.zaker_content_praise_press);
            }else{
                img.setImageResource(R.mipmap.zaker_content_praise);
            }
            dotListt.add(img);
            ll_dots.addView(img);
        }

        myrootviewpager.initData(imgUrlList, dotListt,dotarray);
        myrootviewpager.setAdapter();

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {

    }


    class MyHomeData extends BaseData {


        @Override
        public void setResultData(String data) {

            //先设置数据
            HomeFragment.this.data = data;
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
