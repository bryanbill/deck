package com.minty.deck.dao;

import com.google.firebase.auth.AuthResult;
import com.minty.deck.interfaces.IUser;
import com.minty.deck.models.UserModel;

public class UserDao implements IUser {

    @Override
    public void setAuthResult(AuthResult authResult) {

        new UserModel(
                authResult.getAdditionalUserInfo().getProfile().get("id").toString(),
                authResult.getAdditionalUserInfo().getUsername(),
                authResult.getUser().getDisplayName(),
                authResult.getUser().getPhotoUrl().toString(),
                authResult.getAdditionalUserInfo().getProfile().get("description").toString(),
                authResult.getAdditionalUserInfo().getProfile().get("location").toString(),
                authResult
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
