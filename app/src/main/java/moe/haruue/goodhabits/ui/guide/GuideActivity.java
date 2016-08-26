package moe.haruue.goodhabits.ui.guide;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import moe.haruue.goodhabits.R;

public class GuideActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments;

    final float PARALLAX_COEFFICIENT = 1.2f;
    final float DISTANCE_COEFFICIENT = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        mFragments = new ArrayList<>();
        GuideFragment fragment = new GuideFragment();
        GuideFragment fragment1 = new GuideFragment();
        GuideFragment fragment2 = new GuideFragment();
        GuideFragment fragment3 = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(GuideFragment.ARGUMENT_DRAWABLE, R.drawable.start1);
        bundle.putString(GuideFragment.ARGUMENT_STRING, "重新设定习惯");
        fragment.setArguments(bundle);
        Bundle bundle1 = new Bundle();
        bundle1.putInt(GuideFragment.ARGUMENT_DRAWABLE, R.drawable.start2);
        bundle1.putString(GuideFragment.ARGUMENT_STRING, "改造您的身体");
        fragment1.setArguments(bundle1);
        Bundle bundle2 = new Bundle();
        bundle2.putInt(GuideFragment.ARGUMENT_DRAWABLE, R.drawable.start3);
        bundle2.putString(GuideFragment.ARGUMENT_STRING, "重新连接您的脑细胞");
        fragment2.setArguments(bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putInt(GuideFragment.ARGUMENT_DRAWABLE, R.drawable.start4);
        bundle3.putSerializable(GuideFragment.ARGUMENT_STRING, "开始全新的旅程");
        fragment3.setArguments(bundle3);
        mFragments.add(fragment);
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);

        FragmentManager manager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        mViewPager.setPageTransformer(true, new ParallaxTransformer(PARALLAX_COEFFICIENT, DISTANCE_COEFFICIENT));

        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.circle_indicator);
        circlePageIndicator.setViewPager(mViewPager);
        circlePageIndicator.setOnPageChangeListener(new PageChangeListener());

    }

    private class ParallaxTransformer implements ViewPager.PageTransformer {

        private float mParallaxCoefficient;
        private float mDistanceCoefficient;

        ParallaxTransformer(float parallaxCoefficient, float distanceCoefficient) {
            mParallaxCoefficient = parallaxCoefficient;
            mDistanceCoefficient = distanceCoefficient;
        }

        @Override
        public void transformPage(View page, float position) {
            float scrollXOffset = page.getWidth() * mParallaxCoefficient;
            page.findViewById(R.id.tv_guide).setTranslationX(scrollXOffset * position);
            scrollXOffset *= mDistanceCoefficient;
            page.findViewById(R.id.iv_guide).setTranslationX(scrollXOffset * position * 15);
        }
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        private ArgbEvaluator mColorEvaluator;

        private int mPageWidth, mTotalScrollWidth, mGuideStartBackgroundColor, mGuideEndBackgroundColor;

        PageChangeListener() {
            mColorEvaluator = new ArgbEvaluator();
            Point p = new Point();
            getWindowManager().getDefaultDisplay().getSize(p);
            mPageWidth = p.x;
            mTotalScrollWidth = mPageWidth * 4;
            mGuideStartBackgroundColor = getResources().getColor(R.color.pink);
            mGuideEndBackgroundColor = getResources().getColor(R.color.darkOrange);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float ratio = (mPageWidth * position + positionOffsetPixels) / (float) mTotalScrollWidth;
            Integer color = (Integer) mColorEvaluator.evaluate(ratio, mGuideStartBackgroundColor, mGuideEndBackgroundColor);
            mViewPager.setBackgroundColor(color);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, GuideActivity.class);
        context.startActivity(starter);
    }
}
