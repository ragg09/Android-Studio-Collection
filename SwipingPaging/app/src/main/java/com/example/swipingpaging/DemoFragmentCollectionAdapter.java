package com.example.swipingpaging;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DemoFragmentCollectionAdapter extends FragmentStatePagerAdapter {

    public DemoFragmentCollectionAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        DemoFragment df = new DemoFragment();
        Bundle bundle = new Bundle();
        position = position + 1;
        bundle.putString("message", "THis is my fragment no. "+position);
        df.setArguments(bundle);
        return df;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
