package org.xcube.nfc.util;

import android.view.View;
import android.view.ViewGroup;

public class LayoutUtil {
	
	public static void clearTaggedChildren(ViewGroup parentView, Object tag) {

        int childCount = parentView.getChildCount();
        for (int x = 0; x < childCount; x++) {
            View child = parentView.getChildAt(x);
            if(null != child) {
	            Object childTag = child.getTag();
	            if (null != childTag && childTag.equals(tag)) {
	                parentView.removeView(child);
	            }
            }
        }
    }

}
