/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package me.bahadir.bil425.xoxgame.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.googlecode.objectify.NotFoundException;

import static me.bahadir.bil425.xoxgame.backend.OfyService.ofy;
import javax.inject.Named;

import me.bahadir.bil425.xoxgame.backend.model.User;

/**
 * An endpoint class we are exposing
 */
@Api(name = "userApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.xoxgame.bil425.bahadir.me", ownerName = "backend.xoxgame.bil425.bahadir.me", packagePath = ""))
public class UserEndpoint {


    @ApiMethod(name="getUser")
    public User getUser(@Named("username")String username) throws UserServiceException {

        try {

            User user = ofy().load().type(User.class).id(username).safe();
            return user;
        } catch (NotFoundException e) {
            throw new UserNotFoundException(username);
        }


    }

    @ApiMethod(name="addUser")
    public Response addUser(User user) throws UserServiceException {
        User exist = ofy().load().type(User.class).id(user.getUsername()).now();

        if(exist != null) {
            throw new UserAlreadyExistException(user.getUsername());
        }

        ofy().save().entity(user).now();

        return new Response("User successfully added.");
    }

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public Response sayHi(@Named("name") String name) {
        Response response = new Response();
        response.setData("Hi, " + name);

        return response;
    }

    public static class UserServiceException extends Exception {
        public UserServiceException(String message) {
            super(message);
        }

        public UserServiceException(String message, Throwable cause) {
            super(message, cause);
        }

        public UserServiceException(Throwable cause) {
            super(cause);
        }

        public UserServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

    public static class UserNotFoundException extends UserServiceException{

        public UserNotFoundException(String userName) {
            super("User " + userName + " not found");
        }
    }

    public static class UserAlreadyExistException extends UserServiceException {

        public UserAlreadyExistException(String userName) {
            super("User " + userName + " already exists");
        }
    }
}
