package com.company.user;

import com.company.artist.Genre;

//Can create playlists, provides favorite genre for recommendations
public class Premium extends User{

    private Genre prefGenre;

    public Premium(){

        super();
    }

    public Premium(String username, String email, String password, Genre prefGenre) {
        super(username, email, password);
        this.prefGenre = prefGenre;
    }

    public Genre getPrefGenre() {
        return prefGenre;
    }

    public void setPrefGenre(Genre prefGenre) {
        this.prefGenre = prefGenre;
    }
}