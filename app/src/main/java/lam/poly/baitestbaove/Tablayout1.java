package lam.poly.baitestbaove;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Tablayout1 extends FragmentStatePagerAdapter {
    private final String TAB_TITLES[] = {"nhập sinh viên", "thông tin sinh viên"};

    public Tablayout1(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Frab1();
                break;
            case 1:
                fragment = new Frag2();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
//                mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }

}
