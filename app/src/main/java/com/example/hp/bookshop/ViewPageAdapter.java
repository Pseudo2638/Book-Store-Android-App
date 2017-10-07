package com.example.hp.bookshop;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by HP on 7/29/2017.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {
    Context context;
    public ViewPageAdapter(FragmentManager fm,Context ctx) {
        super(fm);
        context=ctx;
    }


    @Override
    public Fragment getItem(int position) {

        if (position==0) {
            return new TechFragment();
        }
        else if (position==1)
        {
            return  new FictionFragment();
        }
        else if(position==2)
        {
            return new BiographyFragment();
        }
        else
        {
            return new RomedyFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0)
        {
              return context.getString(R.string.tech);
        }
        else if (position==1)
        {
            return  context.getString(R.string.fiction);
        }
        else if(position==2)
        {
            return context.getString(R.string.biograpgy);
        }
        else
        {
            return context.getString(R.string.romedy);
        }

    }
}


