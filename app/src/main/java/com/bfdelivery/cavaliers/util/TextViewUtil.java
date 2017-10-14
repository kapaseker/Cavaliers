package com.bfdelivery.cavaliers.util;

import android.widget.TextView;

/**
 * Created by Panoo on 2017/10/14.
 */

public class TextViewUtil {
	public static final void makeTextViewBold(TextView txtView) {
		txtView.getPaint().setFakeBoldText(true);
	}
}
