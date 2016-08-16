package moe.haruue.goodhabits.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.haruue.goodhabits.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    @BindView(R.id.et_login_user_name)
    AppCompatEditText usernameEditText;
    @BindView(R.id.et_login_user_password)
    AppCompatEditText passwordEditText;


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        new LoginPresenter(this);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
