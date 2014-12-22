package com.example.katecatlin.detroitlive.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

/**
 * Created by katecatlin on 12/19/14.
 */
public class Concert implements Parcelable {
    private DateTime DateTime = new DateTime("2050-01-01T12:00:00.000");
    private String Venue="";
    private String Address = "";
    private String City = "";
    private String ZipCode="";
    private String VenueURL="";
    private String Artist1="";
    private String Artist2="";
    private String Artist3="";
    private String TicketUrl="";

    public Concert(DateTime dateTime, String venue, String address, String city, String zipCode, String venueURL, String artist1, String artist2, String artist3, String ticketUrl) {
        DateTime = dateTime;
        Venue = venue;
        Address = address;
        City = city;
        ZipCode = zipCode;
        VenueURL = venueURL;
        Artist1 = artist1;
        Artist2 = artist2;
        Artist3 = artist3;
        TicketUrl = ticketUrl;
    }

    public Concert() {
    }

    public void setDateTime(DateTime dateTime) {
        DateTime = dateTime;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public void setVenueURL(String venueURL) {
        VenueURL = venueURL;
    }

    public void setArtist1(String artist1) {
        Artist1 = artist1;
    }

    public void setArtist2(String artist2) {
        Artist2 = artist2;
    }

    public void setArtist3(String artist3) {
        Artist3 = artist3;
    }

    public void setTicketUrl(String ticketUrl) {
        TicketUrl = ticketUrl;
    }

    public DateTime getDateTime () { return DateTime; }

    public String getVenue() { return Venue; }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public String getVenueURL() {
        return VenueURL;
    }

    public String getArtist1() {
        return Artist1;
    }

    public String getArtist2() {
        return Artist2;
    }

    public String getArtist3() {
        return Artist3;
    }

    public String getTicketUrl() {
        return TicketUrl;
    }

    public String convertDateTimetoDate (DateTime dateTime) {
        String date = dateTime.getMonthOfYear() + "/" + dateTime.getDayOfMonth();
        return date;
    }

    public String convertDateTimetoTime (DateTime dateTime) {
        String time = dateTime.toString("H:mm");
        return time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeSerializable(this.DateTime);
        dest.writeString(this.Venue);
        dest.writeString(this.Address);
        dest.writeString(this.City);
        dest.writeString(this.ZipCode);
        dest.writeString(this.VenueURL);
        dest.writeString(this.Artist1);
        dest.writeString(this.Artist2);
        dest.writeString(this.Artist3);
        dest.writeString(this.TicketUrl);
    }

    private Concert(Parcel in) {
        this.DateTime = (DateTime) in.readSerializable();
        this.Venue = in.readString();
        this.Address = in.readString();
        this.City = in.readString();
        this.ZipCode = in.readString();
        this.VenueURL = in.readString();
        this.Artist1 = in.readString();
        this.Artist2 = in.readString();
        this.Artist3 = in.readString();
        this.TicketUrl = in.readString();
    }

    public static final Parcelable.Creator<Concert> CREATOR = new Parcelable.Creator<Concert>() {
        public Concert createFromParcel (Parcel source) {
            return new Concert(source);
        }

        public Concert[] newArray(int size) {
            return new Concert[size];
        }
    };
}
