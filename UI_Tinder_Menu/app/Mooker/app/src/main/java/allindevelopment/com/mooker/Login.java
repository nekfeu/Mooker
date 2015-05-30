package allindevelopment.com.mooker;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Login extends ActionBarActivity {


    private LoginButton btnFacebook;
    private CallbackManager callbackManager;


    public void continueAnimation() {
        TextView version = (TextView) findViewById(R.id.version);
        version.setVisibility(View.INVISIBLE);
        ImageView logo_mooker = (ImageView) findViewById(R.id.logo_mooker);
        View back = (View) findViewById(R.id.back);
        final ImageView book = (ImageView) findViewById(R.id.book);
        final ImageView fb = (ImageView) findViewById(R.id.fb_login);
        final ImageView edx = (ImageView) findViewById(R.id.edx_login);
        final ImageView cloud = (ImageView) findViewById(R.id.cloud);
        final LoginButton btnFacebook = (LoginButton) findViewById(R.id.btnFacebook);


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
                cloud.setVisibility(View.VISIBLE);
                btnFacebook.setVisibility(View.VISIBLE);

                YoYo.with(Techniques.SlideInRight).duration(800).playOn(findViewById(R.id.book));
                YoYo.with(Techniques.FadeIn).duration(400).playOn(findViewById(R.id.fb_login));
                YoYo.with(Techniques.FadeIn).duration(400).playOn(findViewById(R.id.edx_login));
                YoYo.with(Techniques.SlideInLeft).duration(400).playOn(findViewById(R.id.cloud));
                YoYo.with(Techniques.FadeIn).duration(500).playOn(findViewById(R.id.btnFacebook));

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
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        TextView version = (TextView) findViewById(R.id.version);
        version.setVisibility(View.VISIBLE);
        ImageView logo_mooker = (ImageView) findViewById(R.id.logo_mooker);
        ImageView fb = (ImageView) findViewById(R.id.fb_login);
        logo_mooker.setVisibility(View.INVISIBLE);
        getSupportActionBar().hide();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                beginAnimation();
            }
        }, 1000);

        btnFacebook = (LoginButton) findViewById(R.id.btnFacebook);
        btnFacebook.setBackgroundResource(R.drawable.login_fb_dbordure);
        callbackManager = CallbackManager.Factory.create();
        btnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.v("FACEBOOK", "SUCCESS" + loginResult.getAccessToken().getUserId());
                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {
                Toast.makeText(Login.this, "Connect with Facebook to continue", Toast.LENGTH_LONG).show();
                Log.v("FACEBOOK", "CANCEL");
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(Login.this, "Check your network connexion", Toast.LENGTH_LONG).show();
                Log.v("FACEBOOK", "ERROR : " + exception);
                // App code
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}