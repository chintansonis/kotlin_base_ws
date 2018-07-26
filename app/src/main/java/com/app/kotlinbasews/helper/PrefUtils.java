package com.app.kotlinbasews.helper;

import android.content.Context;

public class PrefUtils {
    public static String USER_ID = "UserId";
    public static String USER_PROFILE_KEY = "USER_PROFILE_KEY";
    public static String LOGGED_IN = "isLogin";
    private static String FCM_TOKEN = "fcm";
    private static String PASSWORD = "pass";
    private static String PREFERRED_CITY = "city";

    public static void setFCMToken(Context ctx, String value) {
        Prefs.Companion.with(ctx).save(FCM_TOKEN, value);
    }

    public static String getFCMToken(Context ctx) {
        return Prefs.Companion.with(ctx).getString(FCM_TOKEN, " ");
    }

    public static void setLoggedIn(Context ctx, boolean value) {
        Prefs.Companion.with(ctx).save(LOGGED_IN, value);
    }

    public static void setPassword(Context ctx, String value) {
        Prefs.Companion.with(ctx).save(PASSWORD, value);
    }

    public static String getPassword(Context ctx) {
        return Prefs.Companion.with(ctx).getString(PASSWORD, "");
    }

    public static boolean isUserLoggedIn(Context ctx) {
        return Prefs.Companion.with(ctx).getBoolean(LOGGED_IN, false);
    }

    public static void setUserID(Context ctx, long value) {
        Prefs.Companion.with(ctx).save(USER_ID, value);
    }

    public static long getUserID(Context ctx) {
        return Prefs.Companion.with(ctx).getLong(USER_ID, 0);
    }



    /*public static void setUserFullProfileDetails(Context context, User userProfile) {

        String toJson = new Gson().toJson(userProfile);
        setUserID(context, (userProfile == null) ? 0 : userProfile.getLoginId());
        Prefs.Companion.with(context).save(USER_PROFILE_KEY, toJson);
    }
*/
/*
    public static User getUserFullProfileDetails(Context context) {
        Gson gson = new Gson();

        User userProfileDetails = null;

        String getUser = Prefs.Companion.with(context).getString(USER_PROFILE_KEY, "");

        try {
            userProfileDetails = gson.fromJson(getUser, User.class);

        } catch (Exception e) {

        }
        return userProfileDetails;
    }
*/

}
