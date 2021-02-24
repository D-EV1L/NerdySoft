package org.nerdysoft.rooms.model;

public class InvalidRoom {

    private String error;

    public InvalidRoom(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
