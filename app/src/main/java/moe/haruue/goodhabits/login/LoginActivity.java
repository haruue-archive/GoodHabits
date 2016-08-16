package moe.haruue.goodhabits.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import moe.haruue.goodhabits.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new LoginPresenter(this);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
