package com.example.vaibh.gobiz.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.vaibh.gobiz.fragments.content.QuizResultsFragment;

import java.util.ArrayList;
import java.util.List;

public class LessonContentPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public LessonContentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void popFragment() {
        fragmentList.remove(fragmentList.size() - 1);
    }

    // needed to fix issue where the view adapter was still
    // holding onto a fragment after it was popped
    @Override
    public int getItemPosition(@NonNull Object object) {
        if (object instanceof QuizResultsFragment) {
            return POSITION_NONE;
        } else {
            return POSITION_UNCHANGED;
        }
    }
}
