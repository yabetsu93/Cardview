package com.jabespauya.cardview;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import FirebaseHelper.Helper;
import ViewModel.ItemClickListener;
import ViewModel.Nature;
import ViewModel.NatureViewHolder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Helper helper = new Helper();
    private RecyclerView _mRecyclerView;
    private FloatingActionButton fab;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private String TAG = MainActivity.class.getSimpleName();
    private FirebaseRecyclerAdapter<Nature, NatureViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        setContentView(R.layout.activity_main);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("112233444").child("NatureDetails");
        mDatabaseReference.keepSynced(true);


        initLayout();
    }

    private void initLayout(){

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

                final Nature nature = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Title " + nature.get_mTitle() + " description" + nature.get_mDescription(), Toast.LENGTH_SHORT).show();
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

        final Button btnSave = dialog.findViewById(R.id.btnSaveEntry);
        final Button btnCancel = dialog.findViewById(R.id.btnCancel);


        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Perfome Action

                if (!TextUtils.isEmpty(editTitle.getText().toString())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", editTitle.getText().toString());
                    map.put("description", editDescription.getText().toString());

                    helper.writeNewNatureDetail(map);
                    Toast.makeText(MainActivity.this, editTitle.getText().toString() + " " + editDescription.getText().toString(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Title cannot be empty. Please try again!", Toast.LENGTH_SHORT).show();
                }
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



