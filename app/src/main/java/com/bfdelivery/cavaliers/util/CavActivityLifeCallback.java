package com.bfdelivery.cavaliers.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.os.Bundle;

import java.util.Stack;

/**
 * Created by Panoo on 2017/9/7.
 */

public class CavActivityLifeCallback implements Application.ActivityLifecycleCallbacks {

	Stack<ComponentName> mComponentStack = new Stack<>();

	private static final CavActivityLifeCallback _SELF = new CavActivityLifeCallback();

	public static final CavActivityLifeCallback instance() {
		return _SELF;
	}

	public ComponentName getTopActivity() {
		if (mComponentStack.isEmpty()) {
			return null;
		}
		return mComponentStack.peek();
	}

	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
		mComponentStack.push(activity.getComponentName());
	}

	@Override
	public void onActivityStarted(Activity activity) {

	}

	@Override
	public void onActivityResumed(Activity activity) {

	}

	@Override
	public void onActivityPaused(Activity activity) {

	}

	@Override
	public void onActivityStopped(Activity activity) {

	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

	}

	@Override
	public void onActivityDestroyed(Activity activity) {
		mComponentStack.pop();
	}
}
