package com.procipher.bollwoodwhatsappstickers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;

import java.util.Map;

public class StartUp_Activity extends AppCompatActivity {
    private AdView startUp_Banner_2;
    private InterstitialAd mInterstitialAd;
    private final String TAG = "Hello";
    private   Button button,rateUs_btn,youtube_btn;
    private ImageView imageView;
    private ProgressBar progressBar;
    AdRequest adRequest1 = new AdRequest.Builder().build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        Initialization();
        HideAllView();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete( @NonNull InitializationStatus initializationStatus) {
                Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                for (String adapterClass : statusMap.keySet()) {
                    AdapterStatus status = statusMap.get(adapterClass);
                    Log.d("MyApp", String.format(
                            "Adapter name: %s, Description: %s, Latency: %d",
                            adapterClass, status.getDescription(), status.getLatency()));
                }

            }
        });
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                ShowAllView();
                ShowBannerAds();
            }
        }.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StartUp_Activity.this,EntryActivity.class);
                startActivity(intent);
            }
        });
    }
    private void Initialization(){
        imageView = findViewById(R.id.welcomeAnimationView);
        button = findViewById(R.id.viewSticker_btn);
        progressBar = findViewById(R.id.startUp_ProgressBar);
        startUp_Banner_2 = findViewById(R.id.startUp_AdView_Banner2);
        rateUs_btn = findViewById(R.id.rateUs_btn);
        youtube_btn = findViewById(R.id.youtube_btn);

    }
private void HideAllView(){
        button.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        startUp_Banner_2.setVisibility(View.GONE);
        rateUs_btn.setVisibility(View.GONE);
        youtube_btn.setVisibility(View.GONE);

}
private void ShowAllView(){
    rateUs_btn.setVisibility(View.VISIBLE);
    button.setVisibility(View.VISIBLE);
    imageView.setVisibility(View.VISIBLE);
    progressBar.setVisibility(View.GONE);
    startUp_Banner_2.setVisibility(View.VISIBLE);
    youtube_btn.setVisibility(View.VISIBLE);
    Glide.with(this).asGif().load(R.drawable.welcomeanimation).into(imageView);
    }
private void ShowBannerAds(){
    startUp_Banner_2.loadAd(adRequest1);
    startUp_Banner_2.setAdListener(new AdListener() {
        @Override
        public void onAdFailedToLoad( @NonNull LoadAdError loadAdError ) {
            super.onAdFailedToLoad(loadAdError);
            startUp_Banner_2.loadAd(adRequest1);
        }

        @Override
        public void onAdClosed() {
            super.onAdClosed();
            startUp_Banner_2.loadAd(adRequest1);
        }

        @Override
        public void onAdOpened() {
            super.onAdOpened();
        }

        @Override
        public void onAdLoaded() {
            super.onAdLoaded();
            Log.d("Hello", "Banner 2 is Loaded");
        }
    });


}


    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id = item.getItemId();
            if (id == R.id.action_info) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }


    public void moreApps_btn_Click( View view ) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(
                "https://play.google.com/store/search?q=pub:Professional Cipher"));
        startActivity(intent);
    }
}
