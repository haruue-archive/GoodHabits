package moe.haruue.goodhabits.ui.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.ui.login.LoginActivity;

/**
 * Created by simonla on 2016/8/4.
 * Have a good day.
 */
public class GuideFragment extends Fragment {

    public static final String ARGUMENT_DRAWABLE = "TestFragment_drawable";
    public static final String ARGUMENT_STRING = "TestFragment_string";
    @BindView(R.id.tv_guide)
    TextView mTvGuide;
    @BindView(R.id.iv_guide)
    ImageView mIvGuide;
    @BindView(R.id.bt_guide)
    Button mBtGuide;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fratgment_guide_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvGuide.setText(getArguments().getString(ARGUMENT_STRING));
        mIvGuide.setImageResource(getArguments().getInt(ARGUMENT_DRAWABLE));
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);
        mBtGuide.setAnimation(alphaAnimation);
    }

    @OnClick(R.id.bt_guide)
    public void onClick() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
