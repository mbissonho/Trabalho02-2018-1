package br.edu.iff.pooa20181.trabalho02_2018_1;

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

public class EleitorListActivity extends AppCompatActivity implements ClickRecyclerViewListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleitor_list);
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
    }

    @Override
    protected void onResume(){
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.rvEleitores);
        recyclerView.setAdapter(new EleitorAdapter(this.getEleitores(),this,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public List<Eleitor> getEleitores(){

        List<Eleitor> eleitores = new ArrayList<Eleitor>();

        Eleitor e = new Eleitor();

        e.setNome("Joãozinho");
        e.setNumeroDoTitulo("171");
        e.setZona(111);
        e.setSecao(15);

        eleitores.add(e);

        e = new Eleitor();

        e.setNome("Joãozinho");
        e.setNumeroDoTitulo("171");
        e.setZona(111);
        e.setSecao(15);

        eleitores.add(e);

        e = new Eleitor();

        e.setNome("Joãozinho");
        e.setNumeroDoTitulo("171");
        e.setZona(111);
        e.setSecao(15);

        eleitores.add(e);

        e = new Eleitor();

        e.setNome("Joãozinho");
        e.setNumeroDoTitulo("171");
        e.setZona(111);
        e.setSecao(15);

        eleitores.add(e);

        e = new Eleitor();

        e.setNome("Joãozinho");
        e.setNumeroDoTitulo("171");
        e.setZona(111);
        e.setSecao(15);

        eleitores.add(e);

        e = new Eleitor();

        e.setNome("Joãozinho");
        e.setNumeroDoTitulo("171");
        e.setZona(111);
        e.setSecao(15);

        eleitores.add(e);

        e = new Eleitor();

        e.setNome("Joãozinho");
        e.setNumeroDoTitulo("171");
        e.setZona(111);
        e.setSecao(15);

        eleitores.add(e);

        e = new Eleitor();

        e.setNome("Joãozinho");
        e.setNumeroDoTitulo("171");
        e.setZona(111);
        e.setSecao(15);

        eleitores.add(e);

        e = new Eleitor();

        e.setNome("Joãozinho");
        e.setNumeroDoTitulo("171");
        e.setZona(111);
        e.setSecao(15);

        eleitores.add(e);

        return eleitores;
    }

    @Override
    public void onClick(Object object) {
        Log.d("teste","teste");
    }
}
