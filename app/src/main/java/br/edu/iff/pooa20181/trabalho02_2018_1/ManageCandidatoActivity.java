package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class ManageCandidatoActivity extends AppCompatActivity {

    private EditText tNome, tPartido, tNUrna, tCargo, tNVotos, tEstado, tMunicio;
    private Button btnSalvar, btnAlterar, btnDeletar;
    private ConstraintLayout layout;

    private int id;
    private Candidato candidato;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_candidato);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.layout = findViewById(R.id.candidatoLayout);

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

        if(this.verifyEmpty()){
            launchMessage("Preencha todos os campos para cadastrar!");
        }else{
            this.realm.beginTransaction();

            Candidato c = new Candidato();
            c.setId(nextID);

            populate(c);

            this.realm.copyToRealm(c);
            this.realm.commitTransaction();
            this.realm.close();

            launchMessage("Candidato Cadastrado!");
            this.finish();
        }



    }

    private void alterar(){

        if(this.verifyEmpty()){
            launchMessage("Preencha todos os campos para alterar!");
        }else{
            realm.beginTransaction();

            populate(this.candidato);

            realm.copyToRealm(this.candidato);
            realm.commitTransaction();
            realm.close();

            launchMessage("Candidato Alterado!");
            this.finish();
        }


    }

    private void deletar(){
        realm.beginTransaction();
        this.candidato.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        launchMessage("Candidato Excluído!");
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
        this.tPartido = findViewById(R.id.tNomeDaMae);
        this.tNUrna = findViewById(R.id.tDataNascimento);
        this.tNVotos = findViewById(R.id.tNumeroTitulo);
        this.tCargo = findViewById(R.id.tZona);
        this.tEstado = findViewById(R.id.tSecao);
        this.tMunicio = findViewById(R.id.tMunicipio);

        this.btnSalvar = findViewById(R.id.btnSalvar);
        this.btnAlterar = findViewById(R.id.btnAlterar);
        this.btnDeletar = findViewById(R.id.btnDeletar);
    }

    private boolean verifyEmpty(){
        for (int i = 0; i < this.layout.getChildCount(); i++) {
            View child = this.layout.getChildAt(i);

            if (child instanceof EditText) {

                EditText editText = (EditText) child;

                if(editText.getText().toString().trim().isEmpty() || editText.getText().toString().trim().equals("")){
                    return true;
                }
            }
        }

        return false;
    }

    private void launchMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
