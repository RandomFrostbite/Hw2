package com.nvwa.hw2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Math.random;

public class MainActivity extends AppCompatActivity {

    public static final String bookExtra = "Book";
    static public ArrayList<Book> myBooks;
    static {
        myBooks = new ArrayList<Book>();
        myBooks.add( new Book("Half-Life 3", "Gabe Newell", "2203", "drawable://" + R.drawable.cover1 ) );
        myBooks.add( new Book("How to cook", "Elon Musk", "2015" ) );
        myBooks.add( new Book("Start business", "Donald Trump", "2000" ) );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListAdapter bookListAdapter = new ArrayAdapter<Book>( this, android.R.layout.simple_list_item_1, android.R.id.text1, myBooks );
        ListView books = (ListView)findViewById(R.id.books);
        books.setAdapter(bookListAdapter);

        FloatingActionButton addBook = (FloatingActionButton) findViewById(R.id.addBook);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), CreateBookEntry.class );
                startActivity(intent);
            }
        });

        if ( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            BookInfoFragment frag = (BookInfoFragment) getSupportFragmentManager().findFragmentById(R.id.bookInfo);
            frag.displayTask(new Book("Title", "Author", "Release year"));

            FloatingActionButton removeBook = (FloatingActionButton) findViewById(R.id.removeBook);
            removeBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

        books.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
                    BookInfoFragment frag = (BookInfoFragment)getSupportFragmentManager().findFragmentById(R.id.bookInfo);
                    frag.displayTask( (Book)parent.getItemAtPosition(position) );
                } else {
                    Intent intent = new Intent( getApplicationContext(), BookActivity.class );
                    Book tmp = (Book) parent.getItemAtPosition(position);
                    intent.putExtra(bookExtra, tmp);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListAdapter bookListAdapter = new ArrayAdapter<Book>( this, android.R.layout.simple_list_item_1, android.R.id.text1, myBooks );
        ListView books = (ListView)findViewById(R.id.books);
        books.setAdapter(bookListAdapter);
        ((ArrayAdapter) bookListAdapter).notifyDataSetChanged();
    }











    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
