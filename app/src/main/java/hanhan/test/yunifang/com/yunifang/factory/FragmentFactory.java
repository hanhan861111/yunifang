package hanhan.test.yunifang.com.yunifang.factory;

import android.support.v4.app.Fragment;

import java.util.HashMap;

import hanhan.test.yunifang.com.yunifang.fragment.MineFragment;
import hanhan.test.yunifang.com.yunifang.fragment.CartFragment;
import hanhan.test.yunifang.com.yunifang.fragment.CategoryFragment;
import hanhan.test.yunifang.com.yunifang.fragment.HomeFragment;

/**
 * Created by zhiyuan on 16/9/28.
 */
public class FragmentFactory {
    //集合
    public static HashMap<Integer, Fragment> fragmentMap = new HashMap<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentMap.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment=new HomeFragment();
                break;
            case 1:
                fragment=new CategoryFragment();
                break;
            case 2:
                fragment=new CartFragment();
                break;
            case 3:
                fragment=new MineFragment();
                break;
        }
        //添加到集合中
        fragmentMap.put(position,fragment);
        return  fragment;
    }
}
