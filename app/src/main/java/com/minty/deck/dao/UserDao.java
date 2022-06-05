package com.minty.deck.dao;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.minty.deck.interfaces.IUser;
import com.minty.deck.models.UserModel;

public class UserDao implements IUser {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setAuthResult(AuthResult authResult) {
        Log.d("UserDao", "setAuthResult: " +
                authResult.getAdditionalUserInfo().getProfile());
        new UserModel(
                authResult.getAdditionalUserInfo().getProfile().get("id").toString(),
                authResult.getAdditionalUserInfo().getUsername(),
                authResult.getUser().getDisplayName(),
                authResult.getUser().getPhotoUrl().toString(),
                authResult.getAdditionalUserInfo().getProfile().get("description").toString(),
                authResult.getAdditionalUserInfo().getProfile().get("location").toString()
        );

    }

    @Override
    public void logout() {

    }

    @Override
    public UserModel getUser() {
        return UserModel.getModel();
    }
}
