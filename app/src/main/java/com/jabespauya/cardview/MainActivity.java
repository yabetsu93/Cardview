package com.jabespauya.cardview;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import ViewModel.ItemClickListener;
import ViewModel.Nature;
import ViewModel.NatureViewHolder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView _image;
    private TextView _title;
    private TextView _description;
    private RecyclerView _mRecyclerView;
    private FloatingActionButton fab;


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private String TAG = MainActivity.class.getSimpleName();


    private FirebaseRecyclerAdapter<Nature, NatureViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        initLayout();
    }

    private void initLayout(){
        _image = (ImageView) findViewById(R.id.thumbnail);
        _title = (TextView) findViewById(R.id.mTitle);
        _description = (TextView) findViewById(R.id.mDescription);
        _mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        _mRecyclerView.setHasFixedSize(true);
        _mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set adapter

        adapter = new FirebaseRecyclerAdapter<Nature, NatureViewHolder>(Nature.class, R.layout.cardview_item, NatureViewHolder.class, mDatabaseReference) {
            @Override
            protected void populateViewHolder(NatureViewHolder viewHolder, Nature model, int position) {
                viewHolder.title.setText(model.get_mTitle());
                viewHolder.description.setText(model.get_mDescription());

                Picasso.with(getBaseContext()).load(model.get_mImage()).into(viewHolder.mImageView);
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        _mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab: {
                uploadData();
                break;
            }
        }
    }


    private void uploadData() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.upload_naturespot_entry);

        final EditText editTitle = dialog.findViewById(R.id.setTitle);
        final EditText editDescription = dialog.findViewById(R.id.setDescription);

        Button btnSave = dialog.findViewById(R.id.btnSaveEntry);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Perfome Action
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}



