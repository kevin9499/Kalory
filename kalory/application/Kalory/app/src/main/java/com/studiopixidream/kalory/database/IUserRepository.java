package com.studiopixidream.kalory.database;

import com.studiopixidream.kalory.model.User;

public interface IUserRepository {
    public boolean add(User u);

    public boolean isRegistered();

    public User getUser();
}
