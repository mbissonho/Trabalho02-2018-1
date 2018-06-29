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

public class CandidatoListActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato_list);
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

        RecyclerView recyclerView = findViewById(R.id.rvCandidatos);
        recyclerView.setAdapter(new CandidadoAdapter(this.getCandidatos(),this,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<Candidato> getCandidatos(){

        List<Candidato> cadidatos = new ArrayList<Candidato>();

        Candidato c = new Candidato();
        c.setCargo("Prefeito");
        c.setNome("João Doria");
        c.setPartido("PSDB");
        c.setNumeroNaUrna("24");

        cadidatos.add(c);

        return cadidatos;
    }


    @Override
    public void onClick(Object object) {
        Log.d("teste","teste");
    }
}
