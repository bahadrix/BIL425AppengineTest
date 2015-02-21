package me.bahadir.bil425.xoxgame.services;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import me.bahadir.bil425.xoxgame.backend.userApi.UserApi;
import me.bahadir.bil425.xoxgame.backend.userApi.model.User;

/**
 * Created by bahadir on 16/02/15.
 */
public class UserService {
    private static UserService instance = new UserService();
    private final UserApi service;

    public static UserService getInstance() {
        return instance;
    }

    private UserService() {
        UserApi.Builder userApiBuilder = new UserApi.Builder(
                AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(),
                null
        );

        //userApiBuilder.setRootUrl("http://10.0.2.2:8080/_ah/api");
        userApiBuilder.setRootUrl("https://bil425test.appspot.com/_ah/api");
        this.service = userApiBuilder.build();
    }

    public void register(User user) throws IOException {
        service.addUser(user).execute();
    }
}
