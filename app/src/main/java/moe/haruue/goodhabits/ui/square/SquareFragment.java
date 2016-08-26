package moe.haruue.goodhabits.ui.square;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.utils.JUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.model.Plan;
import moe.haruue.goodhabits.ui.BaseFragment;
import moe.haruue.goodhabits.ui.goaldetail.GoalDetailActivity;

/**
 * MainActivity 的第 3 个 tab
 * TODO: 20160805 - tab3 广场
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class SquareFragment extends BaseFragment implements SquareContract.View {

    @BindView(R.id.rv_square)
    RecyclerView mRvSquare;
    private SquareContract.Presenter mPresenter;

    public static final String TAG = "SquareFragment";
    public static final String EXTRA_PLAN_ID = "square_adapter_intent";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_square, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRvSquare.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPresenter.getPlans(new SquareContract.Callback() {
            @Override
            public void onSuccess(List<Plan> plans) {
                mRvSquare.setAdapter(new MyAdapter((ArrayList<Plan>) plans));
                Log.d(TAG, "onSuccess: " + plans.size());
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(SquareContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private ArrayList<Plan> mPlans;

        public MyAdapter(ArrayList<Plan> plans) {
            mPlans = plans;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_square, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            TextView mPlanName = holder.mPlanName;
            RelativeLayout relativeLayout = holder.mRelativeLayout;
            CardView cv = holder.mCardView;
            ImageView iv = holder.mImageView;
            Plan plan = mPlans.get(position);
            mPlanName.setText(plan.title);

            if (plan.isDoing) {
                iv.setImageResource(R.drawable.ic_choosed);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                switch (position) {
                    case 0:
                        relativeLayout.setBackground(getResources().getDrawable(R.drawable.img_goal_3));
                        break;
                    case 1:
                        relativeLayout.setBackground(getResources().getDrawable(R.drawable.img_goal_2));
                        break;
                    case 2:
                        relativeLayout.setBackground(getResources().getDrawable(R.drawable.img_goal_4));
                        break;
                    case 3:
                        relativeLayout.setBackground(getResources().getDrawable(R.drawable.img_goal_1));
                        break;
                }
            }

            cv.setOnClickListener(view -> {
                if (position == 0) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), GoalDetailActivity.class);
                    intent.putExtra(EXTRA_PLAN_ID, plan.planId);
                    Log.d(TAG, "onBindViewHolder: ID:" + plan.planId);
                    ActivityOptionsCompat optionsCompat =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                    cv.findViewById(R.id.cv_goal), "goal_card");
                    ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
                } else {
                    JUtils.Toast("还在开发中");
                }
            });
        }

        @Override
        public int getItemCount() {
            return mPlans.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public CardView mCardView;
            public TextView mPlanName;
            public RelativeLayout mRelativeLayout;
            public ImageView mImageView;

            public ViewHolder(View itemView) {
                super(itemView);
                mCardView = (CardView) itemView.findViewById(R.id.cv_goal);
                mPlanName = (TextView) itemView.findViewById(R.id.tv_goal);
                mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_square);
                mImageView = (ImageView) itemView.findViewById(R.id.iv_chick);
            }
        }
    }

}
