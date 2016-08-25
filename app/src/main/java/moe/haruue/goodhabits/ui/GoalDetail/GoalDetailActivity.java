package moe.haruue.goodhabits.ui.GoalDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.model.Step;
import moe.haruue.goodhabits.ui.square.SquareFragment;

public class GoalDetailActivity extends AppCompatActivity implements GoalDetailContract.View {

    @BindView(R.id.tv_goal)
    TextView mTvGoal;
    @BindView(R.id.rl_square)
    RelativeLayout mRlSquare;
    @BindView(R.id.cv_goal)
    CardView mCvGoal;
    @BindView(R.id.rv_goal_detail)
    RecyclerView mRvGoalDetail;
    private GoalDetailContract.Presenter mPresenter;

    public static final String TAG = "GoalDetailActivity";

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = new Intent();
        String id = intent.getStringExtra(SquareFragment.EXTRA_PLAN_ID);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_detail);
        fab.setOnClickListener(view -> mPresenter.saveThePlan(id));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new GoalDetailPresenter(this);

        mRvGoalDetail.setLayoutManager(new LinearLayoutManager(this));

        mPresenter.getSteps(id, new GoalDetailContract.Callback() {
            @Override
            public void onFinish(List<Step> mSteps) {
                mRvGoalDetail.setAdapter(new MyAdapter((ArrayList<Step>) mSteps));
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onError: " + error);
            }
        });
    }

    @Override
    public void setPresenter(GoalDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private ArrayList<Step> mSteps;

        public MyAdapter(ArrayList<Step> steps) {
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
            Step step = mSteps.get(position);
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
