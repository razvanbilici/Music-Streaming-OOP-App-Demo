package com.company.services;

import com.company.artist.Artist;
import com.company.user.User;
import com.company.repos.FollowsRepo;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UserService {

    private static UserService instance = new UserService();

    private UserService() {}

    public static UserService getInstance(){

        return instance;

    }

    public static String showFollowing(User user)
            throws UnknownHostException, FileNotFoundException {

        AuditService.writeLog(user.getUsername() + " user showFollowing Method Called");

        if(user.getFollowing().size() == 0)
            return "(" + user.getUsername() + ") Not following any artists yet..";

        String all = "";
        int k = 0;


        //Show a max of 2 + "more"
        for(Artist i : user.getFollowing()){

            if(k < 3) {
                all += (i.getName() + ", ");
                k++; }

        }


        return all.substring(0, all.length() - 2) + " and more";
    }

    public static void Follow(User user, Artist artist) throws UnknownHostException, FileNotFoundException {


        if(user.getFollowing().contains(artist)){
            System.out.println("(" + user.getUsername() + ") You're already following " + artist.getName() + "\n");

            AuditService.writeLog(user.getUsername() + " Double Follow Attempt Encountered");


            return;
        }

        System.out.println("(" + user.getUsername() + ") Follow " + artist.getName() + "? (Yes / No)");

        Scanner scan = new Scanner(System.in);

        String ans = scan.next();
        scan.nextLine();

        if(ans.equalsIgnoreCase("yes")){

            AuditService.writeLog(user.getUsername() + " Successfully Followed " + artist.getName());

            FollowsRepo.insertFollow(user, artist);

            System.out.println();
            user.setNrFollow(user.getNrFollow() + 1);
            user.getFollowing().add(artist);
            return;



        }

        AuditService.writeLog(user.getUsername() + " Follow Request Declined:  " + artist.getName());

        System.out.println();

    }

    public static void Unfollow(User user, Artist artist) throws UnknownHostException, FileNotFoundException {

        System.out.println();

        if(!(user.getFollowing().contains(artist))){

            AuditService.writeLog(user.getUsername() + " Unfollow Request Error: " + artist.getName());
            System.out.println("(" + user.getUsername() + ") Error - Can't unfollow!\n");
            return;
        }

        System.out.println("(" + user.getUsername() + ") Unfollow " + artist.getName() + "? (Yes / No)");

        Scanner scan = new Scanner(System.in);

        String ans = scan.next();
        scan.nextLine();

        if(ans.equalsIgnoreCase("yes")){

            AuditService.writeLog(user.getUsername() + " Successfully Unfollowed " + artist.getName());

            FollowsRepo.deleteEntry(user, artist);

            System.out.println();
            user.setNrFollow(user.getNrFollow() - 1);
            user.getFollowing().remove(artist);
            return;


        }

        AuditService.writeLog(user.getUsername() + " Unfollow Request Declined: " + artist.getName());

    }

}
