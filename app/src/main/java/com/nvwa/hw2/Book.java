package com.nvwa.hw2;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    public String title, author, releaseDate;
    int picID;

    Book() {

    }

    Book( String t, String a ) {
        title = t;
        author = a;
        releaseDate = null;
        picID = 0;
    }

    Book( String t, String a, String r ) {
        title = t;
        author = a;
        releaseDate = r;
        picID = 0;
    }

    Book( String t, String a, String r, int pic ) {
        title = t;
        author = a;
        releaseDate = r;
        picID = pic;
    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        releaseDate = in.readString();
        picID = in.readInt();
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
    public String toString() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(releaseDate);
        dest.writeInt(picID);
    }

  /*  protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        releaseDate = in.readString();
        picID = Integer.parseInt( in.readString() );
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
        dest.writeInt(picID);
    }*/
}
