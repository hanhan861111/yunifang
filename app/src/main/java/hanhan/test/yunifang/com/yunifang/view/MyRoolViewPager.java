package hanhan.test.yunifang.com.yunifang.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import hanhan.test.yunifang.com.yunifang.utils.ImageLoaderUtils;

/**
 * Created by Administrator on 2016/12/1.
 */
public class MyRoolViewPager extends ViewPager {

    private DisplayImageOptions options;
    private ArrayList<String> imgUrlList;
    private ArrayList<ImageView> dotList;
    private MyPagerAdapter myPagerAdapter;
    private int[] dotarray;

    public MyRoolViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public MyRoolViewPager(Context context) {
        super(context);
        init();
    }

    private void init() {
        options = ImageLoaderUtils.initOptions();
    }

    /**
     * 初始化数据
     *dotarray   小点图片
     * @param imgUrlList 图片路径集合
     * @param dotList    小点图片集合
     */
    public void initData(ArrayList<String> imgUrlList, final ArrayList<ImageView> dotList, final int []dotarray) {
        this.imgUrlList = imgUrlList;
        this.dotList = dotList;
        this.dotarray=dotarray;
        this.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i <dotList.size() ; i++) {
                    if(i==position){
                        dotList.get(i).setImageResource(dotarray[1]);
                    }else{
                        dotList.get(i).setImageResource(dotarray[0]);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setAdapter() {
        if (myPagerAdapter == null)
            myPagerAdapter = new MyPagerAdapter();
        this.setAdapter(myPagerAdapter);
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgUrlList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView img = new ImageView(getContext());
            ImageLoader.getInstance().displayImage(imgUrlList.get(position), img, options);
            container.addView(img);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
