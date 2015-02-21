package me.bahadir.bil425.xoxgame.backend;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import me.bahadir.bil425.xoxgame.backend.model.User;

public class OfyService {
 
static {
 ObjectifyService.register(User.class);
 }
 
public static Objectify ofy() {
 return ObjectifyService.ofy();
 }
 
public static ObjectifyFactory factory() {
 return ObjectifyService.factory();
 }
}
