package jp.co.topgate.fbxrnder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.BaseObject3D;
import rajawali.animation.Animation3D;
import rajawali.animation.RotateAnimation3D;
import rajawali.math.Number3D.Axis;
import rajawali.parser.fbx.FBXParser;
import rajawali.renderer.RajawaliRenderer;
import rajawali.util.RajLog;
import android.content.Context;

public class FBXRenderer extends RajawaliRenderer {
	private Animation3D mAnim;

	public FBXRenderer(Context context) {
		super(context);
		RajLog.enableDebug(false);
		setFrameRate(60);
	}

	protected void initScene() {
		// -- Model by Sampo Rask
		// (http://www.blendswap.com/blends/characters/low-poly-rocks-character/)
		FBXParser parser = new FBXParser(this,
				R.raw.lowpolyrocks_character_blendswap);
		parser.parse();
		BaseObject3D o = parser.getParsedObject();
		addChild(o);

		mAnim = new RotateAnimation3D(Axis.Y, 360);
		mAnim.setDuration(16000);
		mAnim.setRepeatCount(Animation3D.INFINITE);
		mAnim.setTransformable3D(o);
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		((FBXActivity) mContext).showLoader();
		super.onSurfaceCreated(gl, config);
		((FBXActivity) mContext).hideLoader();
		mAnim.start();
	}
}
