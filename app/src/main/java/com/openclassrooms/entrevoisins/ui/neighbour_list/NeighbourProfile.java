package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class NeighbourProfile extends AppCompatActivity {

    private NeighbourApiService mApiService;

    private TextView mNeighbourName;
    private ImageView mNeighbourPicture;

    private String neighbourName;
    private String titleName;
    private String avatarPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile);
        mApiService = DI.getNeighbourApiService();

        Toolbar mTitleName = (Toolbar) findViewById(R.id.toolbar);
        TextView mNeighbourName = (TextView) findViewById(R.id.neighboursName);
        ImageView mNeighbourPicture = (ImageView) findViewById(R.id.neighboursPicture);
        FloatingActionButton mFavorite = (FloatingActionButton) findViewById(R.id.fab);

        /** TODO 3 recovery neighbour's item from MyNeighbourRecyclerViewAdapter with "SerializableExtra"
         * recovery neighbour's item from MyNeighbourRecyclerViewAdapter
         */

        Intent mIntent = getIntent();
        Neighbour neighbour = (Neighbour) mIntent.getSerializableExtra("PROFILE");

        titleName = neighbour.getName();
        neighbourName = neighbour.getName();
        avatarPicture = neighbour.getAvatarUrl();

        mNeighbourName.setText(neighbourName);
        mTitleName.setTitle(titleName);
        Glide.with(this)
                .load(avatarPicture)
                .into(mNeighbourPicture);

        /** TODO 5 check than the neighbour selected is or not in the favorites's list for the good icon
         *  check than the neighbour selected is or not in the favorites's list for the good icon
         */
       boolean fav = mApiService.getFavoritesNeighbours().contains(neighbour);
       if (fav) {
           mFavorite.setImageResource(R.drawable.ic_staron);
       } else {
           mFavorite.setImageResource(R.drawable.ic_staroff);
       }

        /**
         * and after, condition for delete or add this neighbour
         */
        mFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                boolean fav = mApiService.getFavoritesNeighbours().contains(neighbour);
                if (fav) {
                    mFavorite.setImageResource(R.drawable.ic_staron);
                } else {
                    mFavorite.setImageResource(R.drawable.ic_staroff);
                }

                if (fav) {
                    mApiService.deleteFavorite(neighbour);
                    mFavorite.setImageResource(R.drawable.ic_staroff);
                } else {
                    mApiService.addFavorite(neighbour);
                   mFavorite.setImageResource(R.drawable.ic_staron);
                }

            }
        });

        // TODO 1 add up button and add line 24 of the AndroidManifest

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
