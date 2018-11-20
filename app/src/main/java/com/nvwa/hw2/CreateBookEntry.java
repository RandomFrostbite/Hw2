package com.nvwa.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
        String bookTitle = ((EditText)findViewById(R.id.newBookTitle)).getText().toString();
        String bookAuthor = ((EditText)findViewById(R.id.newBookAuthor)).getText().toString();
        String bookReleaseDate = ((EditText)findViewById(R.id.newBookReleaseDate)).getText().toString();

        if ( bookTitle.isEmpty() )
            bookTitle = getResources().getString(R.string.bookTitle);
        if ( bookAuthor.isEmpty() )
            bookAuthor = getResources().getString(R.string.bookAuthor);
        if ( bookReleaseDate.isEmpty() )
            bookReleaseDate = getResources().getString(R.string.bookReleaseDate);

        String bookCover = "cover" + (int)(  1 + random() * 5 );
        int bookCoverID = getResources().getIdentifier(bookCover, "drawable", getPackageName() );
        Book tmp = new Book( bookTitle, bookAuthor, bookReleaseDate, bookCoverID );
        MainActivity.myBooks.add(tmp);

        finish();
    }
}
