package com.nvwa.hw2;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    public String title, author, releaseDate, picPath;

    Book() {

    }

    Book( String t, String a ) {
        title = t;
        author = a;
        releaseDate = null;
        picPath = null;
    }

    Book( String t, String a, String r ) {
        title = t;
        author = a;
        releaseDate = r;
        picPath = null;
    }

    Book( String t, String a, String r, String pic ) {
        title = t;
        author = a;
        releaseDate = r;
        picPath = pic;
    }

    @Override
    public String toString() {
        return title;
    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        releaseDate = in.readString();
        picPath = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(releaseDate);
        dest.writeString(picPath);
    }
}
