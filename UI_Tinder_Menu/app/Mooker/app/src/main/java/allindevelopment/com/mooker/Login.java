package allindevelopment.com.mooker;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Login extends ActionBarActivity {


    public void continueAnimation() {
        ImageView logo_mooker = (ImageView) findViewById(R.id.logo_mooker);
        View back = (View) findViewById(R.id.back);
        final ImageView book = (ImageView) findViewById(R.id.book);
        final ImageView fb = (ImageView) findViewById(R.id.fb_login);
        final ImageView edx = (ImageView) findViewById(R.id.edx_login);

        ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(logo_mooker, "translationY", 0, -580);
        ObjectAnimator objectAnimator2= ObjectAnimator.ofFloat(back, "translationY", 0, -1200);

        objectAnimator2.setDuration(1000);
        objectAnimator2.start();

        objectAnimator.setDuration(1000);
        objectAnimator.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                book.setVisibility(View.VISIBLE);
                edx.setVisibility(View.VISIBLE);
                fb.setVisibility(View.VISIBLE);

                YoYo.with(Techniques.SlideInRight).duration(800).playOn(findViewById(R.id.book));
                YoYo.with(Techniques.FadeIn).duration(400).playOn(findViewById(R.id.fb_login));
                YoYo.with(Techniques.FadeIn).duration(400).playOn(findViewById(R.id.edx_login));

            }
        }, 1000);

    }

    public void beginAnimation() {
        setContentView(R.layout.activity_login);

        YoYo.with(Techniques.Landing).duration(800).playOn(findViewById(R.id.logo_mooker));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                continueAnimation();
            }
        }, 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView logo_mooker = (ImageView) findViewById(R.id.logo_mooker);
        logo_mooker.setVisibility(View.INVISIBLE);
        getSupportActionBar().hide();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                beginAnimation();
            }
        }, 500);
    }
}