package moe.haruue.goodhabits.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import moe.haruue.goodhabits.R;

/**
 * Created by simonla on 2016/8/4.
 * Have a good day.
 */
public class GuideFragment extends Fragment {

    private ImageView mImageView;
    private TextView mTextView;
    public static final String ARGUMENT_DRAWABLE = "TestFragment_drawable";
    public static final String ARGUMENT_STRING = "TestFragment_string";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fratgment_guide_page, container, false);
        mImageView = (ImageView) view.findViewById(R.id.iv_guide);
        mTextView = (TextView) view.findViewById(R.id.tv_guide);
        mTextView.setText(getArguments().getString(ARGUMENT_STRING));
        mImageView.setImageResource(getArguments().getInt(ARGUMENT_DRAWABLE));
        return view;
    }
}
