package com.bfdelivery.cavaliers.util;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.bfdelivery.cavaliers.R;

/**
 * Created by Panoo on 2017/8/28.
 */

public class LockScreenUtil {

	// Member variables
	private OverlayDialog mOverlayDialog;

	// Reset the variables
	public LockScreenUtil() {
		reset();
	}

	// Display overlay dialog with a view to prevent home button click
	public void lock(Activity activity) {
		if (mOverlayDialog == null) {
			mOverlayDialog = new OverlayDialog(activity);
			mOverlayDialog.show();
		}
	}

	// Reset variables
	public void reset() {
		if (mOverlayDialog != null) {
			mOverlayDialog.dismiss();
			mOverlayDialog = null;
		}
	}

	// Unlock the home button and give callback to unlock the screen
	public void unlock() {
		if (mOverlayDialog != null) {
			mOverlayDialog.dismiss();
			mOverlayDialog = null;
		}
	}

	// Create overlay dialog for lockedscreen to disable hardware buttons
	private static class OverlayDialog extends AlertDialog {

		public OverlayDialog(Activity activity) {
			super(activity, R.style.OverlayDialog);
			WindowManager.LayoutParams params = getWindow().getAttributes();
			params.type = WindowManager.LayoutParams.TYPE_TOAST;
			params.dimAmount = 0.0F;
			params.width = 0;
			params.height = 0;
			params.gravity = Gravity.BOTTOM;
			getWindow().setAttributes(params);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
					0xffffff);
			setOwnerActivity(activity);
			setCancelable(false);
		}

		// consume touch events
		public final boolean dispatchTouchEvent(MotionEvent motionevent) {
			return true;
		}

	}
}

