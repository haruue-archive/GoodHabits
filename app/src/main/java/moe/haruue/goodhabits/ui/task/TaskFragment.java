package moe.haruue.goodhabits.ui.task;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
    //@BindView(R.id.srw_tasks)
    //SwipeRefreshLayout mSrwTasks;
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
        //mSrwTasks = new SwipeRefreshLayout(getContext());
        mLayoutManager = new LinearLayoutManager(getContext());
        mRvTasks.setLayoutManager(mLayoutManager);
        //mSrwTasks.setOnRefreshListener(this);
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
        //mSrwTasks.setRefreshing(false);
        Log.d(TAG, "onRefresh: ");
    }

    public void notTimeToFinish() {
        Snackbar.make(mRvTasks, "因为你内裤没有穿外面，所以你现在不能完成这件事 ", Snackbar.LENGTH_SHORT).show();
    }

    public void saveNote(int id, String note) {
        mPresenter.saveNote(id, note);
    }

    @Override
    public void onGetTodayTasks(ArrayList<Task> tasks, boolean isSuccess) {
        mTasks = tasks;
        mAdapter = new TaskAdapter(mTasks);
        mRvTasks.setAdapter(mAdapter);
    }

    @Override
    public void onSetTaskFinished(boolean isSuccess) {
        if (isSuccess) {
            Log.d(TAG, "onSetTaskFinished: success");
            //mAdapter.notifyDataSetChanged();
        }
    }

    // TODO: 2016/8/20 weak references~~~
    public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

        private ArrayList<Task> mTasks;
        private static final float NORMAL_Z = 8;
        private static final float TODO_Z = 20;
        private static final float FINISHED_Z = 2;
        private int latelyTaskId = 0;

        public TaskAdapter(ArrayList<Task> tasks) {
            mTasks = tasks;
            long nowTime = System.currentTimeMillis() / 1000;
            for (Task t : mTasks) {
                if (t.startTime >= nowTime) {
                    latelyTaskId = t.id;
                    return;
                }
            }
        }

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
            RelativeLayout rvTask = holder.mRelativeLayout;
            RelativeLayout rvNote = holder.mRvNote;
            TextView tvNoteSave = holder.mSave;
            EditText editText = holder.mEditText;
            Boolean isFinish;

            //rvNote.setVisibility(View.GONE);
            cardView.setCardElevation(NORMAL_Z);

            Task task = mTasks.get(holder.getAdapterPosition());
            tvTitle.setText(task.title);
            if (task.isFinish) {
                tvClick.setText("已完成");
                isFinish = true;
                cardView.setCardElevation(FINISHED_Z);
                tvClick.setClickable(false);
                ivTask.setImageResource(R.drawable.ic_finish);
            } else {
                tvClick.setText("未完成");
                isFinish = false;
            }

            long nowTime = System.currentTimeMillis() / 1000;

            if (!isFinish) {
                tvClick.setClickable(false);
                tvClick.setOnClickListener(view -> {
                    // TODO: 2016/8/24 chick is successful
                    if (task.startTime < nowTime) {
                        finishTheTask(task.id);
                        tvClick.setText("已完成");
                        ivTask.setImageResource(R.drawable.ic_finish);
                        animationDown(cardView);
                    } else {
                        notTimeToFinish();
                    }
                });
            }

            tvHint.setText(task.content);

            if (task.id == latelyTaskId) {
                animationUp(cardView);
                ivTask.setImageResource(R.drawable.ic_todo);
            }

          /*  if (startTime - nowTime <= 7200) {
                animationUp(cardView);
            }*/

           /* cardView.setOnClickListener(view -> {
                rvTask.setVisibility(View.GONE);
                rvNote.setVisibility(View.VISIBLE);
            });*/

           /* tvNoteSave.setOnClickListener(view -> {
                saveNote(task.id, editText.getText().toString());
                rvTask.setVisibility(View.VISIBLE);
                rvNote.setVisibility(View.GONE);
            });*/
        }

        private void animationExpand(View view) {
            ObjectAnimator expand = ObjectAnimator.ofFloat(view, "height", 2);
            expand.setDuration(300).start();
        }

        private void animationUp(View view) {
            int nowZ = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                nowZ = (int) view.getElevation();
            }
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
            ObjectAnimator.ofFloat(view, "translationZ", afterZ).setDuration(500).start();
        }

        private void animationDown(View view) {
            float nowZ = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                nowZ = view.getElevation();
            }
            view.invalidate();
            ObjectAnimator down = ObjectAnimator.ofFloat(view, "translationZ", -(nowZ - FINISHED_Z));
            down.setDuration(1000).start();
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
                mCardView = (CardView) itemView.findViewById(R.id.cv_task);
                //mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_task);
                //mRvNote = (RelativeLayout) itemView.findViewById(R.id.rl_task_note);
                //mEditText = (EditText) itemView.findViewById(R.id.et_task_note);
                //mSave = (TextView) itemView.findViewById(R.id.tv_task_note_save);
            }
        }
    }

}
