package com.example.rent.databindingexample;

/**
 * Created by RENT on 2017-02-22.
 */

public class AboutMe {

    private String name = "Kuba";
    private String surname = "Kowalczyk";
    private String street = "Grzybowska 39";
    private String zipCode = "00-855";
    private String city = "Warszawa";

    public String getUrl() {
        return url;
    }

    private String url = "http://autophotogallery.com/wp-content/uploads/2016/10/cristiano-ronaldo-devient-la-risee-du-web-en-postant-une-photo-de-sa-lamborghini-590243.jpg";
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }
}
