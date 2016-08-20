package moe.haruue.goodhabits.network;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestPasswordResetCallback;
import com.avos.avoscloud.SignUpCallback;

import java.util.List;

import io.rx_cache.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import moe.haruue.goodhabits.App;
import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.data.database.task.func.InsertTasksOperateFunc;
import moe.haruue.goodhabits.data.CurrentUser;
import moe.haruue.goodhabits.model.SchoolCourse;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.network.callback.LoginCallback;
import moe.haruue.goodhabits.network.callback.RegisterCallback;
import moe.haruue.goodhabits.network.callback.ResetPasswordCallback;
import moe.haruue.goodhabits.network.exception.RedrockApiException;
import moe.haruue.goodhabits.network.func.RedrockApiWrapperFunc;
import moe.haruue.goodhabits.network.func.SchoolCoursesToTasksFunc;
import moe.haruue.goodhabits.network.redrock.RedrockApi;
import moe.haruue.goodhabits.network.setting.CacheProviders;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public enum RequestManager {

    INSTANCE;

    public static RequestManager getInstance() {
        try {
            return INSTANCE;
        } catch (ExceptionInInitializerError error) {
            throw (RuntimeException) error.getException();
        }
    }

    Retrofit retrofit;
    RedrockApi redrockApi;
    CacheProviders cacheProviders;

    RequestManager() {
        retrofit = new Retrofit.Builder()
                // TODO: change to true api before release
//                .baseUrl(Const.REDROCK_BASE_URL)
                .baseUrl(Const.HARUUE_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        redrockApi = retrofit.create(RedrockApi.class);


        cacheProviders = new RxCache.Builder()
                .persistence(App.getContext().getFilesDir(), new GsonSpeaker())
                .using(CacheProviders.class);

    }

    public void putMessage() {

    }

    public void login(String username, String password, final LoginCallback callback) {
        AVUser.logInInBackground(username, password, new LogInCallback<AVUser>() {

            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    callback.onLoginSuccess();
                } else {
                    // TODO: User friendly message
                    callback.onLoginFailure(e, e.getMessage());
                }
            }
        });
    }

    public void register(String username, String password, final boolean isCQUPT, final String stuNum, final RegisterCallback callback) {
        final AVUser user = new AVUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    if (isCQUPT) {
                        CurrentUser.getInstance().setIsCQUPT(true);
                        CurrentUser.getInstance().setStuNum(stuNum);
                    } else {
                        CurrentUser.getInstance().setIsCQUPT(false);
                    }
                    callback.onRegisterSuccess();
                } else {
                    // TODO: User friendly message
                    callback.onRegisterFailure(e, e.getMessage());
                }
            }
        });

    }

    public void resetPassword(String email, final ResetPasswordCallback callback) {
        AVUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    callback.onResetPasswordRequestSuccess();
                } else {
                    // TODO: User friendly message
                    callback.onResetPasswordRequestFailure(e, e.getMessage());
                }
            }
        });

    }

    public Observable<Integer> getNowWeek(String stuNum, String idNum) {
        return redrockApi.getCourse(stuNum, idNum, "0")

                .map(courseWrapper -> {
                    if (courseWrapper.status != Const.REDROCK_API_STATUS_SUCCESS) {
                        throw new RedrockApiException();
                    }
                    return Integer.parseInt(courseWrapper.nowWeek);
                });
    }

    public Subscription getFullSchoolCourseAndStorageAsTask(Subscriber<List<Task>> subscriber, String stuNum, String idNum) {
        Observable<List<Task>> observable = getNowWeek(stuNum, idNum)
                .flatMap(integer -> getCourseList(stuNum, idNum).map(new SchoolCoursesToTasksFunc(integer)))
                .map(new InsertTasksOperateFunc());

        return emitObservable(observable, subscriber);
    }

    private <T> Subscription emitObservable(Observable<T> o, Subscriber<T> s) {
        return o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public Observable<List<SchoolCourse>> getCourseList(String stuNum, String idNum) {
        return redrockApi.getCourse(stuNum, idNum, "0").map(new RedrockApiWrapperFunc<>());
    }

}
