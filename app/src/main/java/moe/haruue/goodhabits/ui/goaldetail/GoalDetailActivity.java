package moe.haruue.goodhabits.ui.goaldetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.model.BaseStep;
import moe.haruue.goodhabits.ui.square.SquareFragment;

public class GoalDetailActivity extends AppCompatActivity implements GoalDetailContract.View {

    @BindView(R.id.tv_goal)
    TextView mTvGoal;
    @BindView(R.id.rl_square)
    RelativeLayout mRlSquare;
    @BindView(R.id.cv_goal)
    CardView mCvGoal;
    //@BindView(R.id.rv_goal_detail)
    //RecyclerView mRvGoalDetail;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab_detail)
    FloatingActionButton mFabDetail;
    private GoalDetailContract.Presenter mPresenter;

    public static final String TAG = "GoalDetailActivity";

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    public void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v -> {
            this.finish();
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_detail);
        ButterKnife.bind(this);
        initToolbar();
        new GoalDetailPresenter(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra(SquareFragment.EXTRA_PLAN_ID);

        mFabDetail.setOnClickListener(view -> {
            mPresenter.saveThePlan(id);
            EventBus.getDefault().post(new GoalChooseEvent());
            finish();
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        if (id.equals(mPresenter.getNowPlan())) {
            mFabDetail.hide();
        }

        // mRvGoalDetail.setLayoutManager(new LinearLayoutManager(this));

/*        mPresenter.getSteps(id, new GoalDetailContract.Callback() {
            @Override
            public void onFinish(List<BaseStep> mSteps) {
                mRvGoalDetail.setAdapter(new MyAdapter((ArrayList<BaseStep>) mSteps));
                Log.d(TAG, "onFinish: " + mSteps.size());
                for (BaseStep b :
                        mSteps) {
                    Log.d(TAG, "onFinish: "+b.title);
                }
            }

            @Override
            public void onError(String error) {

            }
        });*/
    }

    @Override
    public void setPresenter(GoalDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private ArrayList<BaseStep> mSteps;

        public MyAdapter(ArrayList<BaseStep> steps) {
            mSteps = steps;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_goal_detail, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            BaseStep step = mSteps.get(position);
            TextView textView = holder.mTvItemGoalDetail;
            textView.setText(step.title);
        }

        @Override
        public int getItemCount() {
            return mSteps.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_item_goal_detail)
            TextView mTvItemGoalDetail;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

            }
        }
    }
}
