package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class CandidatoListActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CandidatoListActivity.this, ManageCandidatoActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
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

    public List getCandidatos(){
       return (List) this.realm.where(Candidato.class).findAll();

        //ArrayList<Candidato> candidatos= new ArrayList<Candidato>();

        /*

        Candidato c = new Candidato();
        c.setNome("Doido");
        c.setCargo("Presidente");
        c.setNumeroNaUrna("13");
        c.setPartido("PT");

        candidatos.add(c);

        */

        //return candidatos;
    }

    @Override
    public void onClick(Object object) {
        Candidato c = (Candidato) object;
        Intent intent = new Intent(CandidatoListActivity.this, ManageCandidatoActivity.class);
        intent.putExtra("id", c.getId());
        startActivity(intent);
    }

    @Override
    public void finish(){
        super.finish();
        this.realm.close();
    }


}
