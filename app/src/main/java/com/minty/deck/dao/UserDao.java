package com.minty.deck.dao;

import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.minty.deck.interfaces.IUser;
import com.minty.deck.models.UserModel;

public class UserDao implements IUser {

    @Override
    public void setAuthResult(AuthResult authResult) {
        new UserModel(
                authResult.getAdditionalUserInfo().getUsername(),
                authResult.getUser().getDisplayName(),
                authResult.getUser().getPhotoUrl().toString(),
                "",
                ""
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
