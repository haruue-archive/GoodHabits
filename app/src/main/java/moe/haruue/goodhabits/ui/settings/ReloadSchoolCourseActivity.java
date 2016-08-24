package moe.haruue.goodhabits.ui.settings;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.jude.utils.JUtils;

import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.ui.BaseActivity;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class ReloadSchoolCourseActivity extends BaseActivity {

    private Listener listener;
    ReloadSchoolCourseContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listener = new Listener();
        new ReloadSchoolCoursePresenter(listener);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.reload_school_course))
                .setMessage(R.string.tip_confirm_reload_school_course)
                .setPositiveButton(R.string.ok, listener)
                .setNegativeButton(R.string.cancel, listener)
                .setCancelable(true)
                .setOnCancelListener(listener)
                .create();
        dialog.show();
    }

    class Listener implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, ReloadSchoolCourseContract.View {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case DialogInterface.BUTTON_POSITIVE:
                    presenter.requestReloadSchoolCourse();
                    dialogInterface.dismiss();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialogInterface.cancel();
                    break;
            }
        }

        @Override
        public void onCancel(DialogInterface dialogInterface) {
            finish();
        }

        @Override
        public ProgressDialog onReloadingSchoolCourse() {
            ProgressDialog dialog = new ProgressDialog(ReloadSchoolCourseActivity.this);
            dialog.setMessage(getString(R.string.reloading_school_course));
            dialog.show();
            return dialog;
        }

        @Override
        public void onReloadSchoolCourseSuccess(ProgressDialog dialog) {
            dialog.dismiss();
            JUtils.Toast(getString(R.string.success_reload_school_course));
            finish();
        }

        @Override
        public void onReloadSchoolCourseFailure(ProgressDialog dialog, String message, Throwable t) {
            dialog.dismiss();
            JUtils.Toast(getString(R.string.failure_reload_school_course));
            finish();
        }

        @Override
        public void setPresenter(ReloadSchoolCourseContract.Presenter presenter) {
            ReloadSchoolCourseActivity.this.presenter = presenter;
        }
    }
}
