package com.nvwa.hw2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.random;

public class MainActivity extends AppCompatActivity implements DeleteDialog.NoticeDialogListener {

    public static final String BOOKS_FILE = "com.nvwa.hw2.BooksFile";
    public static final String NUM_BOOKS = "NumOfBooks";
    public static final String TITLE = "title_";
    public static final String AUTHOR = "author_";
    public static final String DATE = "date_";
    public static final String PIC = "pic_";

    static public int selected_item = -1;
    public static final String bookExtra = "Book";
    static public ArrayList<Book> myBooks;
    static {
        myBooks = new ArrayList<Book>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        readDataFromFile();

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
            frag.displayTask( new Book("", "", "") );

            FloatingActionButton removeBook = (FloatingActionButton) findViewById(R.id.removeBook);
            removeBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment newFragment = DeleteDialog.newInstance();
                    newFragment.show( getSupportFragmentManager(), "DeleteDialogTag" );
                }
            });
        }

        books.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_item = position;
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
        saveDataToFile();
    }

    private void readDataFromFile() {
        myBooks.clear();
        String filename = "myBooks.txt";
        String delim = ";";
        FileInputStream inputStream;
        try {
            inputStream = openFileInput(filename);
            BufferedReader reader = new BufferedReader( new FileReader( inputStream.getFD() ) );
            String line;
            while ( (line = reader.readLine() ) != null ) {
                String bookTitle = line.substring( 0, line.indexOf(delim) );
                line = line.substring( line.indexOf(delim) + 1 );
                String bookAuthor = line.substring( 0, line.indexOf(delim) );
                line = line.substring( line.indexOf(delim) + 1 );
                String bookReleaseDate = line.substring( 0, line.indexOf(delim) );
                line = line.substring( line.indexOf(delim) + 1 );
                Book tmp = new Book(bookTitle, bookAuthor, bookReleaseDate, Integer.parseInt(line) );
                myBooks.add(tmp);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void saveDataToFile() {
        String filename = "myBooks.txt";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput( filename, Context.MODE_PRIVATE );
            BufferedWriter writer = new BufferedWriter( new FileWriter( outputStream.getFD() ) );
            String delim = ";";

            for ( Integer i = 0; i < myBooks.size(); i++ ) {
                Book tmp = myBooks.get(i);
                String line = tmp.title + delim + tmp.author + delim + tmp.releaseDate + delim + tmp.picID;
                writer.write( line );
                writer.newLine();
            }
            writer.close();
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
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

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        myBooks.remove(selected_item);
        ListAdapter bookListAdapter = new ArrayAdapter<Book>( this, android.R.layout.simple_list_item_1, android.R.id.text1, myBooks );
        ListView books = (ListView)findViewById(R.id.books);
        books.setAdapter(bookListAdapter);
        ((ArrayAdapter) bookListAdapter).notifyDataSetChanged();
        BookInfoFragment frag = (BookInfoFragment) getSupportFragmentManager().findFragmentById(R.id.bookInfo);
        frag.displayTask( new Book("", "", "") );
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
