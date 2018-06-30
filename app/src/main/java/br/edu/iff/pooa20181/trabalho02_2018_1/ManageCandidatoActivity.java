package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmQuery;

public class ManageCandidatoActivity extends AppCompatActivity {

    private EditText tNome, tPartido, tNUrna, tCargo, tNVotos, tEstado, tMunicio;
    private Button btnSalvar, btnAlterar, btnDeletar;

    private int id;
    private Candidato candidato;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_candidato);
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

        this.bind();

        Intent intent = this.getIntent();
        this.id = (int) intent.getSerializableExtra("id");
        this.realm = Realm.getDefaultInstance();

        if(this.id != 0){

            this.btnSalvar.setEnabled(false);
            this.candidato = realm.where(Candidato.class).equalTo("id",this.id).findFirst();

            this.tNome.setText(this.candidato.getNome());
            this.tNUrna.setText(this.candidato.getNumeroNaUrna());
            this.tNVotos.setText(Integer.toString(this.candidato.getNumeroDeVotos()));
            this.tPartido.setText(this.candidato.getPartido());
            this.tEstado.setText(this.candidato.getEstado());
            this.tCargo.setText(this.candidato.getCargo());
            this.tMunicio.setText(this.candidato.getMunicipio());


        }else{

            this.btnDeletar.setEnabled(false);
            this.btnAlterar.setEnabled(false);

        }

        this.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageCandidatoActivity.this.salvar();
            }
        });


        this.btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageCandidatoActivity.this.alterar();
            }
        });

        this.btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageCandidatoActivity.this.deletar();
            }
        });

    }

    private void salvar(){

        int nextID = 1;

        if(this.realm.where(Candidato.class).max("id") != null){
            nextID = this.realm.where(Candidato.class).max("id").intValue() + 1;
        }

        this.realm.beginTransaction();

        Candidato c = new Candidato();
        c.setId(nextID);

        populate(c);

        this.realm.copyToRealm(c);
        this.realm.commitTransaction();
        this.realm.close();

        Toast.makeText(this,"Candidato Cadastrado!",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private void alterar(){
        realm.beginTransaction();

        populate(this.candidato);

        realm.copyToRealm(this.candidato);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Candidato Atualizado!",Toast.LENGTH_LONG).show();
        this.finish();
    }

    private void deletar(){
        realm.beginTransaction();
        this.candidato.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Candidato Excluído!",Toast.LENGTH_LONG).show();
        this.finish();
    }

    private void populate(Candidato candidato){

        candidato.setNome(this.tNome.getText().toString());
        candidato.setCargo(this.tCargo.getText().toString());
        candidato.setNumeroDeVotos(Integer.parseInt(this.tNVotos.getText().toString()));
        candidato.setNumeroNaUrna(this.tNUrna.getText().toString());
        candidato.setPartido(this.tPartido.getText().toString());
        candidato.setEstado(this.tEstado.getText().toString());
        candidato.setMunicipio(this.tMunicio.getText().toString());
    }

    private void bind(){
        this.tNome = findViewById(R.id.tNome);
        this.tPartido = findViewById(R.id.tPartido);
        this.tNUrna = findViewById(R.id.tNumeroUrna);
        this.tNVotos = findViewById(R.id.tNumeroVotos);
        this.tCargo = findViewById(R.id.tCargo);
        this.tEstado = findViewById(R.id.tEstado);
        this.tMunicio = findViewById(R.id.tMunicipio);

        this.btnSalvar = findViewById(R.id.btnSalvar);
        this.btnAlterar = findViewById(R.id.btnAlterar);
        this.btnDeletar = findViewById(R.id.btnDeletar);
    }
}
