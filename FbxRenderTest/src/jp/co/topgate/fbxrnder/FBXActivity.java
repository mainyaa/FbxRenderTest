package jp.co.topgate.fbxrnder;

import rajawali.RajawaliActivity;
import rajawali.renderer.RajawaliRenderer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class FBXActivity extends RajawaliActivity {

	private ImageView mLoaderGraphic;
	private RajawaliRenderer mRenderer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * requestWindowFeature(Window.FEATURE_NO_TITLE);
		 * getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		 * WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 */
		mRenderer = new FBXRenderer(this);
		mRenderer.setSurfaceView(mSurfaceView);
		super.setRenderer(mRenderer);

		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setGravity(Gravity.BOTTOM);

		TextView label = new TextView(this);
		label.setText("Model by Sampo Rask");
		label.setTextSize(20);
		label.setGravity(Gravity.CENTER);
		label.setHeight(100);
		ll.addView(label);

		mLayout.addView(ll);

		initLoader();
	}

	protected void initLoader() {
		mLoaderGraphic = new ImageView(this);
	}

	public void showLoader() {
		mLayout.post(new Runnable() {
			public void run() {
				mLoaderGraphic.setId(1);
				mLoaderGraphic.setScaleType(ScaleType.CENTER);
				mLoaderGraphic.setImageResource(R.drawable.loader);
				if (mLoaderGraphic.getParent() == null)
					mLayout.addView(mLoaderGraphic);

				AnimationSet animSet = new AnimationSet(false);

				RotateAnimation anim1 = new RotateAnimation(360, 0,
						Animation.RELATIVE_TO_SELF, .5f,
						Animation.RELATIVE_TO_SELF, .5f);
				anim1.setRepeatCount(Animation.INFINITE);
				anim1.setDuration(2000);
				animSet.addAnimation(anim1);

				AlphaAnimation anim2 = new AlphaAnimation(0, 1);
				anim2.setRepeatCount(0);
				anim2.setDuration(1000);
				animSet.addAnimation(anim2);

				mLoaderGraphic.setAnimation(animSet);
			}
		});
	}

	public void hideLoader() {
		mLayout.post(new Runnable() {
			public void run() {
				AlphaAnimation anim = new AlphaAnimation(1, 0);
				anim.setRepeatCount(0);
				anim.setDuration(500);
				anim.setAnimationListener(new AnimationListener() {
					public void onAnimationStart(Animation animation) {
					}

					public void onAnimationRepeat(Animation animation) {
					}

					public void onAnimationEnd(Animation animation) {
						mLoaderGraphic.setVisibility(View.INVISIBLE);
						mLayout.removeView(mLoaderGraphic);

					}
				});
				((AnimationSet) mLoaderGraphic.getAnimation())
						.addAnimation(anim);
			}
		});
	}
}
