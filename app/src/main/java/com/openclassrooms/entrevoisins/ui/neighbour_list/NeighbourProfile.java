package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;

import java.util.List;

public class NeighbourProfile extends AppCompatActivity {

    private TextView mNeighbourName;
    private ImageView mNeighbourPicture;
    private TextView mLocal;
    private String neighbourName;
    private String  titleName;
    private  String picture;
    //private String local;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile);

        Toolbar mtitleName = (Toolbar) findViewById(R.id.toolbar);
        TextView mNeighbourName = (TextView) findViewById(R.id.neighboursName);
        mNeighbourPicture = (ImageView) findViewById(R.id.neighboursPicture);


        Intent mIntent = getIntent();
        int id = (mIntent.getIntExtra("PROFILE", -1))-1;

        titleName = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(id).getName();
        neighbourName = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(id).getName();
        picture = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(id).getAvatarUrl();

        mNeighbourName.setText(neighbourName);
        mtitleName.setTitle(titleName);
        //mNeighbourPicture.setImageURI(picture);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
