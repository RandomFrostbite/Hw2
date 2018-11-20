package com.nvwa.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Math.random;

public class CreateBookEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book_entry);
    }

    public void submit(View view) {
        String bookTitle = findViewById(R.id.newBookTitle).toString();
        String bookAuthor = findViewById(R.id.newBookAuthor).toString();
        String bookReleaseDate = findViewById(R.id.newBookReleaseDate).toString();

        if ( bookTitle.isEmpty() )
            bookTitle = getResources().getString(R.string.bookTitle);
        if ( bookAuthor.isEmpty() )
            bookAuthor = getResources().getString(R.string.bookAuthor);
        if ( bookReleaseDate.isEmpty() )
            bookReleaseDate = getResources().getString(R.string.bookReleaseDate);

        String bookCover = "cover" + (int)(  1 + random() * 5 );
        bookCover = "drawable://" + getResources().getIdentifier(bookCover, "drawable", getPackageName() );
        Book tmp = new Book( bookTitle, bookAuthor, bookReleaseDate, bookCover );
        MainActivity.myBooks.add(tmp);

        finish();
    }
}
