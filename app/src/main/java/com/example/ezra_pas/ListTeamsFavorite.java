package com.example.ezra_pas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListTeamsFavorite extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    private RecyclerView recyclerView;
    private FavoriteAdapterTeams adapter;
    private List<ModelTeamsRealm> modelTeams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teams);
        recyclerView = findViewById(R.id.rvdata);


        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        modelTeams = new ArrayList<>();

        modelTeams = realmHelper.getAllTeams();

        adapter = new FavoriteAdapterTeams(modelTeams, new FavoriteAdapterTeams.Callback() {
            @Override
            public void onClick(int position) {
                Intent move = new Intent(getApplicationContext(), DetailTeamsFavourite.class);
                move.putExtra("judul",modelTeams.get(position).getJudul());
                move.putExtra("path",modelTeams.get(position).getPath());
                move.putExtra("date",modelTeams.get(position).getReleaseDate());
                move.putExtra("deskripsi",modelTeams.get(position).getDesc());

                startActivity(move);
            }

            @Override
            public void test() {

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
