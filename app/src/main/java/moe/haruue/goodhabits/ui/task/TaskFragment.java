package moe.haruue.goodhabits.ui.task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private RecyclerView.Adapter mAdapter;

    public static final String TAG = "TaskFragment";

    @Override
    public void onResume() {
        super.onResume();
        mTasks = new ArrayList<>();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSrwTasks = new SwipeRefreshLayout(getContext());
        mRvTasks.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRvTasks.setLayoutManager(mLayoutManager);
        mPresenter.getTodayTasks();
        mSrwTasks.setOnRefreshListener(() -> mPresenter.getTodayTasks());
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetTodayTasks(ArrayList<Task> tasks, boolean isSuccess) {
        if (!isSuccess) {
            Log.d(TAG, "onGetTodayTasks: fail !!!");
        } else {
            mTasks = tasks;
            mAdapter = new TaskAdapter(mTasks);
            mRvTasks.setAdapter(mAdapter);
            Log.d(TAG, "onGetTodayTasks: " + mTasks.size());
        }
    }

    @Override
    public void onSetTaskFinished(boolean isSuccess) {

    }
}
