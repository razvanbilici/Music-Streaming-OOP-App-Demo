package com.company.user;

import com.company.artist.*;
import com.company.services.*;


import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.time.Year;
import java.util.ArrayList;


public class User {

    private String username = "noname";
    private String email = "default@nodomain.com";
    private String password = "123456789";

    private ArrayList <Artist> following = new ArrayList<>();

    private final Year yearOfReg = Year.now();

    private int nrFollow = 0;

    private int nrUser = 0;

    public User(){

        nrUser++;

    }

    public User(String username, String email, String password) {

        nrUser++;

        this.username = username;
        this.email = email;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Artist> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<Artist> following) {
        this.following = following;
    }

    public int getNrUser() {
        return nrUser;
    }

    public Year getYearOfReg() {
        return yearOfReg;
    }

    public int getNrFollow() {
        return nrFollow;
    }

    public void setNrFollow(int nrFollow) {
        this.nrFollow = nrFollow;
    }

    @Override
    public String toString() {
        try {
            return "User: " + username + "\n" +
                    "Email: " + email + "\n" +
                    "Member since: " + yearOfReg + "\n" +
                    "Following: " + UserService.showFollowing(this) + "\n";
        }
        catch (UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

}