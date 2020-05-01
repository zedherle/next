package com.zed.next.domain.model;

public class UserDone {

    String user_id;
    String done_id;
    String done_type;
    boolean isFavourite;
    int vote;

    public String getDone_id() {
        return done_id;
    }

    public void setDone_id(String done_id) {
        this.done_id = done_id;
    }

    public String getDone_type() {
        return done_type;
    }

    public void setDone_type(String done_type) {
        this.done_type = done_type;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
