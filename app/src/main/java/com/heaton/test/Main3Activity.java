package com.heaton.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.heaton.test.utils.SyncThread;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.radioButton)
    RadioButton radioButton;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.seekBar2)
    SeekBar seekBar2;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.toggl_button)
    ToggleButton togglButton;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    Disposable mDisposable = null;
    private Observable novel;
    private Observer<String> reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        getData();
       /* reader = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(String value) {
                Logger.d(value);
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("--onError");
            }

            @Override
            public void onComplete() {
                Logger.d("onComplete");
            }
        };
*/
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Logger.d("onViewClicked");
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                e.onNext("连1");
                e.onNext("连2");
                e.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.e("onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Logger.e("onNext:" + value);
            }

            @Override
            public void onError(Throwable e) {
                Logger.e("onError=" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Logger.e("onComplete()");
            }
        });


        //novel.subscribe(reader);
    }

    private void getData() {
        // Retrofit retrofit = new Retrofit.Builder().baseUrl("https://192.168.1.1").addConverterFactory(ResponseConverterFactory.create()).build();
        SyncThread syncThread1 = new SyncThread();
        SyncThread syncThread2 = new SyncThread();
        Thread thread1 = new Thread(syncThread1, "JACK");
        Thread thread2 = new Thread(syncThread1, "ROSE");
        thread1.start();
        thread2.start();

        button.post(new Runnable() {
            @Override
            public void run() {
                button.setText("dfsfdsfsd");
            }
        });
        //createRotateAnimation();
        //createScaleAnimation();
        //createAlphaAnimation();
        //createTranslateAnimation();
        createAnimationSet();
    }

    /**
     * 创建旋转动画
     */
    private void createRotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f); //动画执行时间
        rotateAnimation.setDuration(1000); //动画重复次数-1表示不停重复
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnimation.setInterpolator(lir);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        // rotateAnimation.setRepeatMode(Animation.REVERSE);
        button.startAnimation(rotateAnimation);

    }

    /**
     * 创建缩放动画
     */
    private void createScaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        button.setAnimation(scaleAnimation);
    }

    /**
     * 透明度动画
     */
    private void createAlphaAnimation() {
        AlphaAnimation scaleAnimation = new AlphaAnimation(0f, 1.0f);
        scaleAnimation.setDuration(3000);
        ivBg.setAnimation(scaleAnimation);
    }

    /**
     * 位移动画
     */
    private void createTranslateAnimation() {
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, 100);
        animation.setDuration(2000);
        ivBg.setAnimation(animation);
    }

    /**
     * 创建组合动画
     */
    private void createAnimationSet() {
    /*    AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, 100);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(2000);
        animationSet.setRepeatCount(-1);*/
        //加载动画资源
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.suofang_demo);
        //开启动画
        ivBg.startAnimation(animation);
    }
}
