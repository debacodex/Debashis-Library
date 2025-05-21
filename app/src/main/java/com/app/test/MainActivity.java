package com.app.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

public class MainActivity extends AppCompatActivity {
   private ViewPager mViewPager;
	//private MagicIndicator mMagicIndicator;
	private ViewPagerAdapter viewPagerAdapter;

	private static final String[] CHANNELS = new String[] { "DEBASHIS", "NARESH", "SURESH", "FATHER", "MOTHER" };
	private List<String> mDataList = new ArrayList<String>(Arrays.asList(CHANNELS));

	private DrawerLayout drawerLayout;
	private NavigationView navigationView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		drawerLayout = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open,
				R.string.nav_close);
		drawerLayout.addDrawerListener(toggle);
		toggle.syncState();
		// Setup ViewPager and TabLayout
		mViewPager = (ViewPager) findViewById(R.id.view_pager1);
		viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

		mViewPager.setAdapter(viewPagerAdapter);

		navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(MenuItem item) {
				int itemId = item.getItemId();
				Menu navigationMenu = navigationView.getMenu();

				for (int i = 0; i < navigationMenu.size(); i++) {
					if (navigationMenu.getItem(i).getItemId() == itemId) {
						mViewPager.setCurrentItem(i);
						// Optionally handle drawer closing
						if (drawerLayout != null) {
							drawerLayout.closeDrawer(GravityCompat.START);
						}
						return true;
					}
				}
				return false;
			}
		});

		// Initial synchronization
		int initialPosition = 0;
		mViewPager.setCurrentItem(initialPosition);
		navigationView.getMenu().getItem(initialPosition).setChecked(true);

		/*	if (id == R.id.nav_home) {
				mViewPager.setCurrentItem(0);
				Toast.makeText(MainActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
			} else if (id == R.id.nav_messages) {
				mViewPager.setCurrentItem(1);
				Toast.makeText(MainActivity.this, "Gallery clicked", Toast.LENGTH_SHORT).show();
			} else if (id == R.id.nav_friends) {
				mViewPager.setCurrentItem(2);
				Toast.makeText(MainActivity.this, "Slideshow clicked", Toast.LENGTH_SHORT).show();
			}
		
			drawerLayout.closeDrawer(GravityCompat.START);
			return true;
		}
		}); */

		initMagicIndicator1();

	}

	private void initMagicIndicator1() {
		MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
		//   magicIndicator.setBackgroundColor(Color.parseColor("#FFFFFF"));
		CommonNavigator commonNavigator = new CommonNavigator(this);
		commonNavigator.setSkimOver(true);
		commonNavigator.setScrollPivotX(0.35f);
		//commonNavigator.setAdjustMode(true);
		/*  int padding = UIUtil.getScreenWidth(this) / 30;
		commonNavigator.setRightPadding(padding);
		commonNavigator.setLeftPadding(padding);  */
		commonNavigator.setAdapter(new CommonNavigatorAdapter() {

			@Override
			public int getCount() {
				return mDataList == null ? 0 : mDataList.size();
			}

			@Override
			public IPagerTitleView getTitleView(Context context, final int index) {
				ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
				clipPagerTitleView.setText(mDataList.get(index));
				clipPagerTitleView.setTextColor(getResources().getColor(R.color.magic_indicator_text));
				clipPagerTitleView.setClipColor(Color.parseColor("#FF4081"));
				clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mViewPager.setCurrentItem(index);
						drawerLayout.closeDrawer(GravityCompat.START);

					}
				});
				return clipPagerTitleView;
			}

			@Override
			public IPagerIndicator getIndicator(Context context) {
				WrapPagerIndicator indicator = new WrapPagerIndicator(context);
				indicator.setFillColor(Color.parseColor("#1BFF4081"));
				return indicator;
			}
		});
		magicIndicator.setNavigator(commonNavigator);

		ViewPagerHelper.bind(magicIndicator, mViewPager);

		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// Not needed for binding, but part of the interface
			}

			@Override
			public void onPageSelected(int position) {
				// When a ViewPager page is selected, update the NavigationView
				Menu navigationMenu = navigationView.getMenu();
				if (position < navigationMenu.size()) {
					navigationMenu.getItem(position).setChecked(true);
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// Not needed for binding, but part of the interface
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main_menu, menu);

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}

		switch (item.getItemId()) {
		case R.id.code:
			/*Intent intent = new Intent(MainActivity.this, ImageActivity.class);
			startActivity(intent); */
			break;
		case R.id.about:

			break;
		}
		return super.onOptionsItemSelected(item);

	}

}
