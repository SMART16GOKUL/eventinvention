package com.example.eventinvention.data;

public class URLs {

    private static final String ROOT_URL = "http://eventinvention.in/api/loginapi.php?apicall=";
    public static final String ROOT_URL1 = "http://eventinvention.in/api/eventapi.php?apicall=";



    public static final String URL_REGISTER = ROOT_URL + "signup";
    public static final String URL_LOGIN= ROOT_URL + "login";
    public static final String URL_FORGOTPASSWORD= ROOT_URL + "forgot";
    public static final String URL_STATE= ROOT_URL1 + "viewstate";
    public static final String URL_CITY= ROOT_URL1 + "viewcity";
    public static final String URL_VENDOEMAN= ROOT_URL1 + "searchcatagory";
    public static final String URL_MANGERPROFILE= ROOT_URL1 + "viewprofile";
    public static final String URL_ADDRATING= ROOT_URL1 + "addrating";
    public static final String URL_SUBCATAGORY= ROOT_URL1 + "catagory";
    public static final String URL_VIEWCOMMENT= ROOT_URL1 + "viewrating";
    public static final String URL_VIEWPROFILE= ROOT_URL1 + "viewprofile";
    public static final String URL_UPDATEUSERS= ROOT_URL1 + "updateusers";
    public static final String URL_VIEWNEWSADDS= ROOT_URL1 + "viewnews";

}