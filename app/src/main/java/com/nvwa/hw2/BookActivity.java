package com.nvwa.hw2;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BookActivity extends AppCompatActivity implements DeleteDialog.NoticeDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        FloatingActionButton removeBook = (FloatingActionButton)findViewById(R.id.removeBook);
        removeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = DeleteDialog.newInstance();
                newFragment.show( getSupportFragmentManager(), "DeleteDialogTag" );
            }
        });

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        MainActivity.myBooks.remove(MainActivity.selected_item);
        finish();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
