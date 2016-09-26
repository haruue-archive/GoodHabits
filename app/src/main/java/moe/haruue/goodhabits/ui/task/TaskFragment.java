package moe.haruue.goodhabits.ui.task;

import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.receiver.PushReceiver;
import moe.haruue.goodhabits.ui.BaseFragment;
import moe.haruue.goodhabits.ui.calendar.TaskFinishEvent;
import moe.haruue.goodhabits.ui.settings.CourseReloadedEvent;
import moe.haruue.goodhabits.util.ResourceUtils;

/**
 * MainActivity 的第 1 个 tab
 * TODO: 20160805 - tab1 当前任务（当天）、fab 增加任务
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TaskFragment extends BaseFragment implements TaskContract.View {

    public static final String TAG = "TaskFragment";
    public static final String EXTRA_CONTEXT = "task_fragment_context";
    public static final String EXTRA_PUSH = "task_fragment_push";
    public static final String EXTRA_LAST_TIME = "task_fragment_time";
    public static final String EXTRA_PUSH_CONTEXT = "task_fragment_push_context";

    @BindView(R.id.rv_tasks)
    RecyclerView mRvTasks;
    //  @BindView(R.id.tv_message)
    // TextView mTvMessage;
    // @BindView(R.id.cv_message)
    // CardView mCvMessage;
    private TaskContract.Presenter mPresenter;
    private ArrayList<Task> mTasks;
    private RecyclerView.LayoutManager mLayoutManager;
    private TaskAdapter mAdapter;
    private String mContext;

    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;


/*    private void tipsCardControl(String context) {
        mCvMessage.setVisibility(View.GONE);
        if (!mPresenter.isRead(context.hashCode())) {
            mCvMessage.setOnClickListener(view1 -> {
                mContext = context;
                Intent intent = new Intent();
                intent.putExtra(EXTRA_CONTEXT, context);
                intent.setClass(getActivity(), TaskDetailActivity.class);
                startActivity(intent);
            });
            mCvMessage.setVisibility(View.VISIBLE);
        }
    }*/

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserEvent(CourseReloadedEvent event) {
        mPresenter.getTodayTasks();
    }

    @Override
    public void onResume() {
        super.onResume();
        mTasks = new ArrayList<>();
    }

/*    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserEvent(MessageGoneEvent event) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mCvMessage, "alpha", 1f, 0f);
        objectAnimator.setDuration(1000).start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mCvMessage.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                mCvMessage.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        ButterKnife.bind(this, view);
        mPresenter.getTodayTasks();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        init();
        String context = ResourceUtils.readStringFromRawResource(getResources(), R.raw.guide);
        //   tipsCardControl(context);
    }

    private void settingAlarm() {
        mAlarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        long nowTime = System.currentTimeMillis() / 1000;
        long intervalTime = 1800;
        for (int i = 0; i < mTasks.size(); i++) {
            mPendingIntent = PendingIntent.getBroadcast(getContext(), 0, intentMaker().get(i), 0);
            mAlarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + mTasks.get(i).startTime - nowTime - intervalTime, mPendingIntent);
        }
    }

    private ArrayList<Intent> intentMaker() {
        ArrayList<Intent> mInatent = new ArrayList<>();
        for (int i = 0; i < mTasks.size(); i++) {
            Intent intent = new Intent(getContext(), PushReceiver.class);
            intent.putExtra(EXTRA_PUSH, mTasks.get(i).title);
            intent.putExtra(EXTRA_LAST_TIME, mTasks.get(i).startTime);
            intent.putExtra(EXTRA_PUSH_CONTEXT, mTasks.get(i).content);
            mInatent.add(intent);
        }
        return mInatent;
    }

    private void init() {
        //mSrwTasks = new SwipeRefreshLayout(getContext());
        mLayoutManager = new LinearLayoutManager(getContext());
        mRvTasks.setLayoutManager(mLayoutManager);
        //    mCvMessage.setVisibility(View.GONE);
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
    }

    public void notTimeToFinish() {
        Snackbar.make(mRvTasks, "现在不能完成这件事 ", Snackbar.LENGTH_SHORT).show();
    }

    public void saveNote(int id, String note) {
        mPresenter.saveNote(id, note);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onGetTodayTasks(ArrayList<Task> tasks, boolean isSuccess) {
        if (isSuccess) {
            mTasks = tasks;
            mAdapter = new TaskAdapter(mTasks);
            mRvTasks.setAdapter(mAdapter);
            View header = LayoutInflater.from(getContext()).inflate(R.layout.header_task, mRvTasks,
                    false);
            mAdapter.setHeader(header);
            View footer = LayoutInflater.from(getContext()).inflate(R.layout.footer_task, mRvTasks,
                    false);
            footer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getNavigationBarHeight()));
            mAdapter.setFooter(footer);
            settingAlarm();
        } else {
            Snackbar.make(mRvTasks, "遇到错误，设置里重置课程表试试", Snackbar.LENGTH_LONG).show();
        }
    }

    private int getNavigationBarHeight() {
        Resources resources = this.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    @Override
    public void onSetTaskFinished(boolean isSuccess) {
        if (isSuccess) {
            EventBus.getDefault().post(new TaskFinishEvent());
        }
    }

    // TODO: 2016/8/20 weak references~~~
    public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

        public static final int TYPE_HEADER = 0;
        public static final int TYPE_FOOTER = 1;
        public static final int TYPE_NORMAL = 2;

        private ArrayList<Task> mTasks;
        private static final float NORMAL_Z = 8;
        private static final float TODO_Z = 20;
        private static final float FINISHED_Z = 2;
        private int latelyTaskId = 0;

        private View mHeader;
        private View mFooter;

        public View getFooter() {
            return mFooter;
        }

        public void setFooter(View footer) {
            mFooter = footer;
            notifyItemInserted(getItemCount() - 1);
        }

        public View getHeader() {
            return mHeader;
        }

        public void setHeader(View header) {
            mHeader = header;
            notifyItemInserted(0);
        }

        @Override
        public int getItemViewType(int position) {
            if (mHeader == null && mFooter == null) {
                return TYPE_NORMAL;
            }
            if (position == 0) {
                return TYPE_HEADER;
            }
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            }
            return TYPE_NORMAL;
        }

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
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mHeader != null && viewType == TYPE_HEADER) {
                return new MyViewHolder(mHeader);
            }
            if (mFooter != null && viewType == TYPE_FOOTER) {
                return new MyViewHolder(mFooter);
            }
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            if (getItemViewType(position) == TYPE_NORMAL) {
                load(holder, position);
            } else if (getItemViewType(position) == TYPE_HEADER) {
                Log.d(TAG, "onBindViewHolder: head");
                mHeader.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            } else {
                Log.d(TAG, "onBindViewHolder: foot");
            }
        }

        private void load(MyViewHolder holder, int position) {
            TextView tvTitle = holder.mTvTitle;
            TextView tvHint = holder.mTvHint;
            TextView tvClick = holder.mTvClick;
            ImageView ivTask = holder.mIvTask;
            CardView cardView = holder.mCardView;
            RelativeLayout rlTask = holder.mRelativeLayout;
            RelativeLayout rvNote = holder.mRvNote;
            TextView tvNoteSave = holder.mSave;
            EditText editText = holder.mEditText;
            Boolean isFinish;

            cardView.setCardElevation(NORMAL_Z);
            Task task = mTasks.get(position - 1);
            Log.d(TAG, "onBindViewHolder: " + task.title);
            if ("type_school_course".equals(task.type)) {
                ivTask.setImageResource(R.drawable.ic_task_1);
                rlTask.setBackgroundColor(getResources().getColor(R.color.task));
                tvHint.setText(task.content);
            } else {
                ivTask.setImageResource(R.drawable.ic_custom);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String date = simpleDateFormat.format(task.startTime * 1000);
                tvHint.setText(date);
                // tipsCardControl(task.content);
            }

            tvTitle.setText(task.title);
            if (task.isFinish) {
                tvClick.setText("已完成");
                isFinish = true;
                cardView.setCardElevation(FINISHED_Z);
                tvClick.setClickable(false);
                ivTask.setImageResource(R.drawable.ic_finish);
                rlTask.setBackgroundColor(getResources().getColor(R.color.finish));
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
                        rlTask.setBackgroundColor(getResources().getColor(R.color.finish));
                        animationDown(cardView);
                    } else {
                        notTimeToFinish();
                    }
                });
            }

            if (task.id == latelyTaskId) {
                animationUp(cardView);
                ivTask.setImageResource(R.drawable.ic_todo);
                rlTask.setBackgroundColor(getResources().getColor(R.color.goal_2));
            }
        }

        private void animationExpand(View view) {
            ObjectAnimator expand = ObjectAnimator.ofFloat(view, "height", 2);
            expand.setDuration(300).start();
        }

        private void animationUp(View view) {
            int nowZ = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                nowZ = view.getElevation();
            }
            view.invalidate();
            ObjectAnimator down = ObjectAnimator.ofFloat(view, "translationZ", -(nowZ - FINISHED_Z));
            down.setDuration(1000).start();
        }

        @Override
        public int getItemCount() {
            if (mHeader == null && mFooter == null) {
                return mTasks.size();
            } else if (mHeader == null && mFooter != null) {
                return mTasks.size() + 1;
            } else if (mHeader != null && mFooter == null) {
                return mTasks.size() + 1;
            } else {
                return mTasks.size() + 2;
            }
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView mTvTitle;
            private TextView mTvHint;
            private TextView mTvClick;
            private ImageView mIvTask;
            private CardView mCardView;
            private RelativeLayout mRelativeLayout;
            private RelativeLayout mRvNote;
            private EditText mEditText;
            private TextView mSave;

            public MyViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                if (itemView == mHeader) {
                    return;
                }
                if (itemView == mFooter) {
                    return;
                }
                mIvTask = (ImageView) itemView.findViewById(R.id.iv_task_hint);
                mTvTitle = (TextView) itemView.findViewById(R.id.tv_task_title);
                mTvHint = (TextView) itemView.findViewById(R.id.tv_task_hint);
                mTvClick = (TextView) itemView.findViewById(R.id.tv_task_finish);
                mCardView = (CardView) itemView.findViewById(R.id.cv_task);
                mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_task);
                //mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_task);
                //mRvNote = (RelativeLayout) itemView.findViewById(R.id.rl_task_note);
                //mEditText = (EditText) itemView.findViewById(R.id.et_task_note);
                //mSave = (TextView) itemView.findViewById(R.id.tv_task_note_save);
            }
        }
    }

}
