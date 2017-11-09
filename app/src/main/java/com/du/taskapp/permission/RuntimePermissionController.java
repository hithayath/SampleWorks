package com.du.taskapp.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Hithayath.
 */

public class RuntimePermissionController {

    private static RuntimePermissionController mInstance;
    public static final int REQUEST_ACCESS_LOCATION = 121;

    private RuntimePermissionController() {}

    public static RuntimePermissionController getInstance() {
        if (mInstance == null) {
            mInstance = new RuntimePermissionController();
        }
        return mInstance;
    }

    public boolean needPermission(Activity activity, String permission){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int granted = ContextCompat.checkSelfPermission(activity, permission);
            return granted != PackageManager.PERMISSION_GRANTED;
        } else {
            return false;
        }
    }

    public void requestPermission(Activity activity, String permission, int requestId) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestId);
    }
}
