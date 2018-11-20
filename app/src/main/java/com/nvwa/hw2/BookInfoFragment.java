package com.nvwa.hw2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookInfoFragment extends Fragment {
    public BookInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState ) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();

        Book receivedTask = intent.getParcelableExtra( MainActivity.bookExtra );
        if (receivedTask != null)
            displayTask(receivedTask);

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_book_info, container, false );
    }

    public void displayTask( Book book ) {
        View displayedTaskView = getActivity().findViewById( R.id.BookInfoFragment );
        displayedTaskView.setVisibility( View.VISIBLE );
        ((TextView)getActivity().findViewById( R.id.bookTitle  ) ).setText( book.title );
        ((TextView)getActivity().findViewById( R.id.bookAuthor ) ).setText( book.author );
        ((TextView)getActivity().findViewById( R.id.bookReleaseDate ) ).setText( book.releaseDate );
        ImageView taskImage = (ImageView) getActivity().findViewById( R.id.bookCover );
        if ( book.picPath != null && !book.picPath.isEmpty() ) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(book.picPath);
            taskImage.setImageBitmap( imageBitmap );
        } else {
            taskImage.setImageBitmap( null );
        }
    }
}
