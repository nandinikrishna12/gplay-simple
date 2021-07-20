package com.example.gplay_simple;

public class App
{
    private String packagename;
    private String appname;
    private String developer;
    private String icon_filename;
    private int total_downloads;
    private int total_reviews;
    private double rating;
    private boolean warning_display;
    private String warning_message;
    private boolean installed;

    public App(String packagename, String appname, String developer, String icon_filename, int total_downloads, int total_reviews, double rating, boolean warning_display, String warning_messsage) {
        this.packagename = packagename;
        this.appname = appname;
        this.developer = developer;
        this.icon_filename = icon_filename;
        this.total_downloads = total_downloads;
        this.total_reviews = total_reviews;
        this.rating = rating;
        this.warning_display = warning_display;
        this.warning_message = warning_messsage;
    }

    @Override
    public String toString() {
        return "App{" +
                "packagename='" + packagename + '\'' +
                ", appname='" + appname + '\'' +
                ", developer='" + developer + '\'' +
                ", icon_filename='" + icon_filename + '\'' +
                ", total_downloads=" + total_downloads +
                ", total_reviews=" + total_reviews +
                ", rating=" + rating +
                ", warning_display=" + warning_display +
                ", warning_message='" + warning_message + '\'' +
                '}';
    }

    public String getPackagename() {
        return packagename;
    }

    public String getAppname() {
        return appname;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getIcon_filename() {
        return icon_filename;
    }

    public int getTotal_downloads() {
        return total_downloads;
    }

    public int getTotal_reviews() {
        return total_reviews;
    }

    public double getRating() {
        return rating;
    }

    public boolean isWarning_display() {
        return warning_display;
    }

    public String getWarning_message() {
        return warning_message;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }
}
