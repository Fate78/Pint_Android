package com.example.projeto_pint.utilizador;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    private static final String SESSION = "session";
    private static SharedPreferences getSessionState;


    public static void SessionStateSharedPreferences(Context context, boolean state) {
        SharedPreferences getSessionState = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = getSessionState.edit();
        edit.putBoolean("session", state);
        edit.apply();
    }

    static boolean getSessionState(Context context) {
        getSessionState = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        return getSessionState.getBoolean("session", false);
    }
}
