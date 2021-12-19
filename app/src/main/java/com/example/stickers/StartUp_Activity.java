package com.example.stickers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Map;

public class StartUp_Activity extends AppCompatActivity {
    private AdView startUp_Banner_1,startUp_Banner_2;
    private InterstitialAd mInterstitialAd;
    private String TAG = "HELLO";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        ImageView imageView = findViewById(R.id.welcomeAnimationView);
        Glide.with(this).asGif().load(R.drawable.welcomeanimation).into(imageView);
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
                ShowBannerAds();
                LoadInterstitialAds();
            }
        });

        Button button = findViewById(R.id.viewSticker_btn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ShowInterstitialAds();
                Intent intent = new Intent(StartUp_Activity.this,EntryActivity.class);
                startActivity(intent);
            }
        });
    }

private void ShowBannerAds(){
    startUp_Banner_1 = findViewById(R.id.startUp_AdView_Banner1);
    startUp_Banner_2 = findViewById(R.id.startUp_AdView_Banner2);
    AdRequest adRequest = new AdRequest.Builder()
            .build();
    startUp_Banner_1.loadAd(adRequest);
    startUp_Banner_2.loadAd(adRequest);
    startUp_Banner_2.setAdListener(new AdListener() {
        @Override
        public void onAdFailedToLoad( @NonNull LoadAdError loadAdError ) {
            super.onAdFailedToLoad(loadAdError);
            startUp_Banner_2.loadAd(adRequest);
        }

        @Override
        public void onAdClosed() {
            super.onAdClosed();
            startUp_Banner_2.loadAd(adRequest);
        }

        @Override
        public void onAdOpened() {
            super.onAdOpened();
        }

        @Override
        public void onAdLoaded() {
            super.onAdLoaded();
        }
    });

    startUp_Banner_1.setAdListener(new AdListener() {
        @Override
        public void onAdLoaded() {
            super.onAdLoaded();
        }

        @Override
        public void onAdFailedToLoad(LoadAdError adError) {
            super.onAdFailedToLoad(adError);
            startUp_Banner_1.loadAd(adRequest);
        }

        @Override
        public void onAdOpened() {
         super.onAdOpened();
        }

        @Override
        public void onAdClicked() {
            super.onAdClicked();
        }

        @Override
        public void onAdClosed() {
            super.onAdClosed();
            startUp_Banner_1.loadAd(adRequest);

        }
    });
}

    private void LoadInterstitialAds() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, getString(R.string.admob_interstitial_ad), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
            }
        });
    }
    public void ShowInterstitialAds(){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(StartUp_Activity.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {

                }
                @Override
                public void onAdShowedFullScreenContent() {
                    mInterstitialAd = null;
                }
            });
        }
    }
    public void watchYoutubeVideo( View view ) {
    }

    public void launchMail( View view ) {
    }
}