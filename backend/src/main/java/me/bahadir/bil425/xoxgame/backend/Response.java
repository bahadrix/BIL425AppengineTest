package me.bahadir.bil425.xoxgame.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class Response {

    private String myData;

    public Response() {
    }

    public Response(String myData) {
        this.myData = myData;
    }

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}