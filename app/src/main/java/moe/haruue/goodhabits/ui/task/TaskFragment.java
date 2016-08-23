package moe.haruue.goodhabits.ui.task;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.ui.BaseFragment;

/**
 * MainActivity 的第 1 个 tab
 * TODO: 20160805 - tab1 当前任务（当天）、fab 增加任务
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TaskFragment extends BaseFragment implements TaskContract.View {

    @BindView(R.id.rv_tasks)
    RecyclerView mRvTasks;
    @BindView(R.id.srw_tasks)
    SwipeRefreshLayout mSrwTasks;
    private TaskContract.Presenter mPresenter;
    private ArrayList<Task> mTasks;
    private RecyclerView.LayoutManager mLayoutManager;
    private TaskAdapter mAdapter;

    public static final String TAG = "TaskFragment";

    @Override
    public void onResume() {
        super.onResume();
        mTasks = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        mPresenter.getTodayTasks();
    }

    private void init() {
        mSrwTasks = new SwipeRefreshLayout(getContext());
        mLayoutManager = new LinearLayoutManager(getContext());
        mRvTasks.setLayoutManager(mLayoutManager);
        mSrwTasks.setOnRefreshListener(() -> mPresenter.getTodayTasks());
    }

    public void finishTheTask(int id) {
        mPresenter.setTaskFinish(id);
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRefresh(boolean isSomeNews, ArrayList<Task> newTasks) {

    }

    public void saveNote(int id) {
        mPresenter.saveNote(id);
    }

    @Override
    public void onGetTodayTasks(ArrayList<Task> tasks, boolean isSuccess) {
        if (!isSuccess) {
            Log.d(TAG, "onGetTodayTasks: fail !!!");
        } else {
            for (int i = 0; i < 5; i++) {
                Task task = new Task();
                task.title = "高数课" + i;
                mTasks.add(task);
            }
            if (tasks.size() != 0) {
                mTasks = tasks;
            }
            mAdapter = new TaskAdapter(mTasks);
            mRvTasks.setAdapter(mAdapter);
            Log.d(TAG, "onGetTodayTasks: " + mTasks.size());
        }
    }

    @Override
    public void onSetTaskFinished(boolean isSuccess) {
        if (isSuccess) {
            mAdapter.notify();
        }
    }


    // TODO: 2016/8/20 weak references~~~
    public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

        private ArrayList<Task> mTasks;

        public TaskAdapter(ArrayList<Task> tasks) {
            mTasks = tasks;
        }

        private static final float NORMAL_Z = 8;
        private static final float TODO_Z = 16;
        private static final float FINISHED_Z = 2;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            TextView tvTitle = holder.mTvTitle;
            TextView tvHint = holder.mTvHint;
            TextView tvClick = holder.mTvClick;
            ImageView ivTask = holder.mIvTask;
            CardView cardView = holder.mCardView;
            RelativeLayout relativeLayout = holder.mRelativeLayout;
            RelativeLayout RvNote = holder.mRvNote;
            TextView tvNoteSave = holder.mSave;

            RvNote.setVisibility(View.GONE);

            Task task = mTasks.get(holder.getAdapterPosition());
            cardView.setCardElevation(10);
            if (task.isFinish) {
                tvClick.setText("已完成");
                animationDown(cardView);
            } else {
                tvClick.setText("未完成");
            }
            tvTitle.setText(task.title);
            tvClick.setOnClickListener(view -> finishTheTask(holder.getAdapterPosition()));

            long startTime = task.startTime;
            long nowTime = System.currentTimeMillis();
            if (startTime - nowTime <= 7200) {
                animationUp(cardView);
            }

            cardView.setOnClickListener(view -> {
                relativeLayout.setVisibility(View.GONE);
                RvNote.setVisibility(View.VISIBLE);
            });

            tvNoteSave.setOnClickListener(view -> {
                saveNote(task.id);
                relativeLayout.setVisibility(View.VISIBLE);
                RvNote.setVisibility(View.GONE);
            });
        }

        private void animationExpand(View view) {
            ObjectAnimator expand = ObjectAnimator.ofFloat(view, "height", 2);
            expand.setDuration(300).start();
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private void animationUp(View view) {
            int nowZ = (int) view.getElevation();
            float afterZ = 0;
            switch (nowZ) {
                case (int) FINISHED_Z:
                    afterZ = TODO_Z;
                    break;
                case (int) NORMAL_Z:
                    afterZ = TODO_Z - NORMAL_Z;
                    break;
                case (int) TODO_Z:
                    afterZ = 0;
                    break;
            }

            ObjectAnimator up = ObjectAnimator.ofFloat(view, "rotationZ", afterZ);
            up.setDuration(500).start();
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private void animationDown(View view) {
            float nowZ = view.getElevation();
            ObjectAnimator down = ObjectAnimator.ofFloat(view, "rotationZ", nowZ - FINISHED_Z);
            down.setDuration(500).start();
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView mTvTitle;
            private TextView mTvHint;
            private TextView mTvClick;
            private ImageView mIvTask;
            private CardView mCardView;
            private RelativeLayout mRelativeLayout;
            private RelativeLayout mRvNote;
            private EditText mEditText;
            private TextView mSave;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                mIvTask = (ImageView) itemView.findViewById(R.id.iv_task_hint);
                mTvTitle = (TextView) itemView.findViewById(R.id.tv_task_title);
                mTvHint = (TextView) itemView.findViewById(R.id.tv_task_hint);
                mTvClick = (TextView) itemView.findViewById(R.id.tv_task_finish);
                mCardView = (CardView) itemView.getRootView();
                mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_task);
                mRvNote = (RelativeLayout) itemView.findViewById(R.id.rl_task_note);
                mEditText = (EditText) itemView.findViewById(R.id.et_task_note);
                mSave = (TextView) itemView.findViewById(R.id.tv_task_note_save);
            }
        }
    }

}
