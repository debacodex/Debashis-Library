package com.app.test;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.List;

/**
* Created by Priyabrat on 21-08-2015.
*/
public class ViewPagerAdapter extends FragmentStatePagerAdapter{

	private final String[] TITLES = { "Coffees", "Sweets", "Snacks", "Drinks" };

	public ViewPagerAdapter(FragmentManager fm) {

		super(fm);
	}
	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		if (position == 0) {
			fragment = new FragmentA();
		} else if (position == 1) {
			fragment = new FragmentB();
		} else if (position == 2) {
			fragment = new FragmentC();
		} else if (position == 3) {
			fragment = new FragmentC();

		} else if (position == 4) {
			fragment = new FragmentC();
		} else if (position == 5) {
			fragment = new FragmentC();
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		String st = null;
		if (position == 0) {
			st = "Debashis";
		} else if (position == 1) {
			st = "Nikita";
		} else if (position == 2) {
			st = "Love";
		} else if (position == 3) {
			st = "Broken";
		} else if (position == 4) {
			st = "Heart";
		} else if (position == 5) {
			st = "%";  
		} 
		return st;
	}
}
