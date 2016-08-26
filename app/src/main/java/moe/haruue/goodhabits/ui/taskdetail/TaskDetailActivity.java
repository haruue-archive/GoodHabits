package moe.haruue.goodhabits.ui.taskdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.zzhoujay.richtext.RichText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.ui.task.MessageGoneEvent;
import moe.haruue.goodhabits.ui.task.TaskFragment;

public class TaskDetailActivity extends AppCompatActivity implements TaskDetailContract.View {

    public static final String TAG = "TaskDetailActivity";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.tv_context)
    TextView mTvContext;

    private TaskDetailContract.Presenter mPresenter;

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        new TaskDetailPresenter(this);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        Intent intent = getIntent();
        String context = intent.getStringExtra(TaskFragment.EXTRA_CONTEXT);
        RichText.fromMarkdown(context).into(mTvContext);

        mFab.setOnClickListener(view -> {
            mPresenter.saveIsRead(context.hashCode());
            EventBus.getDefault().post(new MessageGoneEvent());
            finish();
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void setPresenter(TaskDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
