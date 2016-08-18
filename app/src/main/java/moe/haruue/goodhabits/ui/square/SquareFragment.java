package moe.haruue.goodhabits.ui.square;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.ui.fragment.BaseFragment;

/**
 * MainActivity 的第 3 个 tab
 * TODO: 20160805 - tab3 广场
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class SquareFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_square, container, false);
        return view;
    }
}
