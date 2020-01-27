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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;

import java.util.List;

public class NeighbourProfile extends AppCompatActivity {

    private TextView mNeighbourName;
    private ImageView mNeighbourPicture;
    private TextView mLocal;
    private String neighbourName;
    private String titleName;
    private String avatarPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile);

        Toolbar mTitleName = (Toolbar) findViewById(R.id.toolbar);
        TextView mNeighbourName = (TextView) findViewById(R.id.neighboursName);
        ImageView mNeighbourPicture = (ImageView) findViewById(R.id.neighboursPicture);
        FloatingActionButton mFavorite = (FloatingActionButton) findViewById(R.id.fab);


        Intent mIntent = getIntent();
        int id = (mIntent.getIntExtra("PROFILE", -1)) - 1;

        titleName = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(id).getName();
        neighbourName = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(id).getName();
        avatarPicture = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(id).getAvatarUrl();


        mNeighbourName.setText(neighbourName);
        mTitleName.setTitle(titleName);
        Glide.with(this)
                .load(avatarPicture)
                .into(mNeighbourPicture);

        boolean fav = DummyNeighbourGenerator.generateFavoritesNeighbours().contains(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(id));
        if (fav) {
            mFavorite.setImageResource(R.drawable.ic_staron);
        } else {
            mFavorite.setImageResource(R.drawable.ic_staroff);
        }

        mFavorite.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if (fav) {
                    DummyNeighbourGenerator.favoritesList.remove(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(id));
                    mFavorite.setImageResource(R.drawable.ic_staroff);
                } else {
                    DummyNeighbourGenerator.favoritesList.add(DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(id));
                   mFavorite.setImageResource(R.drawable.ic_staron);
                }

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
