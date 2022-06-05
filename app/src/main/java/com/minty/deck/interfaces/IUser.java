package com.minty.deck.interfaces;

import com.google.firebase.auth.AuthResult;
import com.minty.deck.models.UserModel;

public interface IUser {
    void setAuthResult(AuthResult authResult);
    void logout();
    UserModel getUser();
}
