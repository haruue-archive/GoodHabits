package moe.haruue.goodhabits.ui.settings;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import com.jude.utils.JUtils;

import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.data.CurrentUser;
import moe.haruue.goodhabits.util.StandardUtils;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class SettingsFragment extends android.preference.PreferenceFragment {

    Listener listener = new Listener();

    Preference reloadSchoolCoursePreference;
    EditTextPreference stuNumPreference;

    SettingsContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SettingsPresenter(listener);
        addPreferencesFromResource(R.xml.preferences);
        initializePreferences();
        refreshPreferenceEnableState();
        refreshPreferenceSummary();
    }

    private void initializePreferences() {
        reloadSchoolCoursePreference = $(R.string.key_reload_school_course);
        stuNumPreference = $(R.string.key_stu_num);
        stuNumPreference.setDefaultValue(StandardUtils.checkNullWithDefaultValue(CurrentUser.getInstance().getStuNum(), ""));
    }

    private void refreshPreferenceEnableState() {
        reloadSchoolCoursePreference.setEnabled(CurrentUser.getInstance().isLogin() && CurrentUser.getInstance().getIsCQUPT() && CurrentUser.getInstance().getStuNum() != null);
    }

    private void refreshPreferenceSummary() {
        if (StandardUtils.checkNullWithDefaultValue(CurrentUser.getInstance().getStuNum(), "").equals("")) {
            stuNumPreference.setSummary("未设置");
        } else {
            stuNumPreference.setSummary(CurrentUser.getInstance().getStuNum());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);
    }

    class Listener implements SharedPreferences.OnSharedPreferenceChangeListener, SettingsContract.View {

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if (s.equals(getString(R.string.key_stu_num))) {
                String stuNum = sharedPreferences.getString(getString(R.string.key_stu_num), "");
                if (presenter.isStuNumModified(stuNum)) {
                    showStuNumModifyConfirmDialog(stuNum);
                }
            }
        }

        public void showStuNumModifyConfirmDialog(String stuNum) {
            new AlertDialog.Builder(SettingsFragment.this.getActivity())
                    .setTitle(R.string.set_stu_num)
                    .setMessage(R.string.tip_confirm_set_stu_num)
                    .setCancelable(true)
                    .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        presenter.applyStuNumModify(stuNum);
                    })
                    .setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.cancel())
                    .setOnCancelListener(dialogInterface -> {
                        stuNumPreference.setDefaultValue(CurrentUser.getInstance().getStuNum());
                        getPreferenceScreen().getSharedPreferences().edit().putString(getString(R.string.key_stu_num), CurrentUser.getInstance().getStuNum()).apply();
                    })
                    .create().show();
        }

        @Override
        public void setPresenter(SettingsContract.Presenter presenter) {
            SettingsFragment.this.presenter = presenter;
        }

        @Override
        public ProgressDialog onReLoadingSchoolCourse() {
            ProgressDialog dialog = new ProgressDialog(SettingsFragment.this.getActivity());
            dialog.setMessage(getString(R.string.reloading_school_course));
            dialog.show();
            return dialog;
        }

        @Override
        public void onReLoadSchoolCourseSuccess(ProgressDialog dialog) {
            dialog.dismiss();
            JUtils.Toast(getString(R.string.complete));
            refreshPreferenceEnableState();
            refreshPreferenceSummary();
        }

        @Override
        public void onReLoadSchoolCourseFailure(ProgressDialog dialog, String message, Throwable t) {
            dialog.dismiss();
            JUtils.Toast(getString(R.string.failure));
            refreshPreferenceEnableState();
            refreshPreferenceSummary();
        }
    }

    protected <P extends Preference> P $(String key) {
        return (P) findPreference(key);
    }

    protected <P extends Preference> P $(@StringRes int resId) {
        return (P) findPreference(getString(resId));
    }
}
