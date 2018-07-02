package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class EleitorListActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleitor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EleitorListActivity.this, ManageEleitorActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.rvEleitores);
        recyclerView.setAdapter(new EleitorAdapter(this.getEleitores(),this,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public List<Eleitor> getEleitores(){

        return (List) this.realm.where(Eleitor.class).findAll();
    }

    @Override
    public void onClick(Object object) {
        Eleitor e = (Eleitor) object;
        Intent intent = new Intent(EleitorListActivity.this, ManageEleitorActivity.class);
        intent.putExtra("id", e.getId());
        startActivity(intent);
    }

    @Override
    public void finish(){
        super.finish();
        this.realm.close();
    }
}
