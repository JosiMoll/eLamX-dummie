package standard.example.elamx;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    ImageView image1;
    LinearLayout line1;

    Animation uptodown, downtoup;

    TextView TextView1, TextView2;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_aktivity);

        this.getWindow().getDecorView().setSystemUiVisibility(                  // Visibility der Navigation-Bar etc.
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        ;

        line1 = (LinearLayout) findViewById(R.id.line1);                        // Beginn der Animation
        image1 = (ImageView) findViewById(R.id.image1);

        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);

        line1.setAnimation(downtoup);
        image1.setAnimation(uptodown);

        Handler handler = new Handler();                                        // Weiterleitung zum HomeScreen nach TimeDelay
        handler.postDelayed(new Runnable() {

            public void run() {
                Intent myIntent = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(myIntent);
            }
        }, 1600);

        //     transfer();
    }

    // private void transfer() {
    //    Intent myIntent = new Intent(MainActivity.this, HomeScreen.class);
    //   startActivity(myIntent);
    //}

};
