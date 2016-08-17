package moe.haruue.goodhabits.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dd.CircularProgressButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.ui.activity.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.rv_login_title0)
    TextView mRvLoginTitle0;
    @BindView(R.id.et_login_user_name)
    AppCompatEditText mEtLoginUserName;
    @BindView(R.id.etl_login_user_name)
    TextInputLayout mEtlLoginUserName;
    @BindView(R.id.et_login_user_password)
    AppCompatEditText mEtLoginUserPassword;
    @BindView(R.id.etl_login_user_password)
    TextInputLayout mEtlLoginUserPassword;
    @BindView(R.id.tv_login_title1)
    TextView mTvLoginTitle1;
    @BindView(R.id.et_login_student_number)
    AppCompatEditText mEtLoginStudentNumber;
    @BindView(R.id.etl_login_student_number)
    TextInputLayout mEtlLoginStudentNumber;
    @BindView(R.id.activity_login)
    RelativeLayout mActivityLogin;
    @BindView(R.id.login_submit)
    CircularProgressButton mLoginSubmit;
    private LoginContract.Presenter mPresenter;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginSubmit.setIndeterminateProgressMode(true);
        new LoginPresenter(this);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void noNetError() {
        Snackbar.make(mActivityLogin, "请检查你的网络", Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void userNameError(String error) {
        mEtLoginUserName.setError(error);
    }

    @Override
    public void passwordError(String error) {
        mEtLoginUserPassword.setError(error);
    }

    @Override
    public void stuNumError(String error) {
        mEtLoginStudentNumber.setError(error);
    }

    @Override
    public void showMakeSureDialog() {
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("即将开始")
                .setMessage("该帐号还未注册，确定注册吗")
                .setNegativeButton("不", null)
                .setPositiveButton("好", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.signUp(
                                mEtLoginUserName.getText().toString(),
                                mEtLoginUserPassword.getText().toString(),
                                mEtLoginStudentNumber.getText().toString());
                    }
                })
                .show();
    }

    @Override
    public void showProgress(int per) {
        mLoginSubmit.setProgress(per);
    }

    @Override
    public void startActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_submit)
    public void onClick() {
        mPresenter.login(
                mEtLoginUserName.getText().toString(),
                mEtLoginUserPassword.getText().toString(),
                mEtLoginStudentNumber.getText().toString());
    }
}