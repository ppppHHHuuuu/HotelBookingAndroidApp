package com.example.mobdev_nhom7;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ApplicationPreferredManager {
    private static final String AEGIS_RUN_COUNT = "aegis.run.count";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    public ApplicationPreferredManager(Context context, String user_id) {
        this.appSharedPrefs = context.getSharedPreferences(user_id, Activity.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }
    public int updateRunCounter() {
        int runCount = appSharedPrefs.getInt(AEGIS_RUN_COUNT, 0);

        prefsEditor.putInt(AEGIS_RUN_COUNT, ++runCount);
        prefsEditor.commit();

        return runCount;
    }

}
