package com.minty.deck;


import static org.junit.Assert.assertTrue;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
class SplashActivityTest {
    private SplashActivity activity;

    @Before
    void setUp() {
        activity = Robolectric.buildActivity(SplashActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public  void splashTextViewTest() {
        TextView splashTextView = activity.findViewById(R.id.splash_text);
        assertTrue(splashTextView.getText().toString().equals("Deck"));
    }
}