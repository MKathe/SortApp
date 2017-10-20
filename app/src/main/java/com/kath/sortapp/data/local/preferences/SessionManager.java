package com.kath.sortapp.data.local.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.kath.sortapp.data.entities.ElementEntity;

/**
 * Created by junior on 13/10/16.
 */
public class SessionManager {


    public static final String PREFERENCE_NAME = "SymbiosisClient";
    public static int PRIVATE_MODE = 0;

    /**
     USUARIO DATA SESSION - JSON
     */
    public static final String COUNT = "count";
    public static final String TOUCH = "touch";
    public static final String COLOR = "color";



    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void saveCountRed(int count){
        editor.putInt(COUNT, count);
    }
    public void updateElement(ElementEntity elementEntity){
        editor.putInt(COUNT, elementEntity.getCount());
        editor.putString(TOUCH, elementEntity.getTouch());
        editor.putString(COLOR, elementEntity.getColor());
        editor.commit();
    }

    public void getDataElement(){

    }

   /* public void setUser(UserEntity userEntity) {
        if (userEntity != null) {
            Gson gson = new Gson();
            String user = gson.toJson(userEntity);
            editor.putString(USER_JSON, user);
        }
        editor.commit();
    }

    public UserEntity getUserEntity() {
        String userData = preferences.getString(USER_JSON, null);
        return new Gson().fromJson(userData, UserEntity.class);
    }*/
}
