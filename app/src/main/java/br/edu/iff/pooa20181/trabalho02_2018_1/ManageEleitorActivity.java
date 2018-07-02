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

import java.util.Date;

import io.realm.Realm;

public class ManageEleitorActivity extends AppCompatActivity {

    private EditText tNome, tDataDeNascimento, tNumeroDoTitulo, tNomeDaMae, tZona, tSecao, tMunicipio;
    private Button btnSalvar, btnAlterar, btnDeletar;
    private ConstraintLayout layout;

    private int id;
    private Eleitor eleitor;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_eleitor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.layout = findViewById(R.id.eleitorLayout);

        this.bind();

        Intent intent = this.getIntent();
        this.id = (int) intent.getSerializableExtra("id");
        this.realm = Realm.getDefaultInstance();

        if(this.id != 0){

            this.btnSalvar.setEnabled(false);
            this.eleitor = realm.where(Eleitor.class).equalTo("id",this.id).findFirst();

            this.tNome.setText(this.eleitor.getNome());
            this.tDataDeNascimento.setText(this.eleitor.getDataDeNascimento());
            this.tNumeroDoTitulo.setText(this.eleitor.getNumeroDoTitulo());
            this.tNomeDaMae.setText(this.eleitor.getNomeDaMãe());
            this.tZona.setText(Integer.toString(this.eleitor.getZona()));
            this.tSecao.setText(Integer.toString(this.eleitor.getSecao()));
            this.tMunicipio.setText(this.eleitor.getMunicipio());

        }else{

            this.btnDeletar.setEnabled(false);
            this.btnAlterar.setEnabled(false);
        }

        this.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageEleitorActivity.this.salvar();
            }
        });


        this.btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageEleitorActivity.this.alterar();
            }
        });

        this.btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageEleitorActivity.this.deletar();
            }
        });

    }

    private void salvar(){

        int nextID = 1;

        if(this.realm.where(Candidato.class).max("id") != null){
            nextID = this.realm.where(Candidato.class).max("id").intValue() + 1;
        }

        if(this.verifyEmpty()){
            Toast.makeText(this,"Preencha todos os campos para cadastrar!",Toast.LENGTH_LONG).show();
        }else{
            this.realm.beginTransaction();

            Eleitor e = new Eleitor();
            e.setId(nextID);

            populate(e);

            this.realm.copyToRealm(e);
            this.realm.commitTransaction();
            this.realm.close();

            Toast.makeText(this,"Eleitor Cadastrado!",Toast.LENGTH_LONG).show();
            this.finish();
        }



    }

    private void alterar(){

        if(this.verifyEmpty()){
            Toast.makeText(this,"Preencha todos os campos para cadastrar!",Toast.LENGTH_LONG).show();
        }else{
            realm.beginTransaction();

            populate(this.eleitor);

            realm.copyToRealm(this.eleitor);
            realm.commitTransaction();
            realm.close();

            Toast.makeText(this,"Eleitor Atualizado!",Toast.LENGTH_LONG).show();
            this.finish();
        }


    }

    private void deletar(){
        realm.beginTransaction();
        this.eleitor.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Eleitor Excluído!",Toast.LENGTH_LONG).show();
        this.finish();
    }

    private void populate(Eleitor eleitor){
        eleitor.setNome(this.tNome.getText().toString());
        eleitor.setDataDeNascimento(this.tDataDeNascimento.getText().toString());
        eleitor.setNumeroDoTitulo(this.tNumeroDoTitulo.getText().toString());
        eleitor.setNomeDaMãe(this.tNomeDaMae.getText().toString());
        eleitor.setZona(Integer.parseInt(this.tZona.getText().toString()));
        eleitor.setSecao(Integer.parseInt(this.tSecao.getText().toString()));
        eleitor.setMunicipio(this.tMunicipio.getText().toString());
    }

    private void bind(){
        this.tNome = findViewById(R.id.tNome);
        this.tDataDeNascimento = findViewById(R.id.tDataNascimento);
        this.tNumeroDoTitulo = findViewById(R.id.tNumeroTitulo);
        this.tNomeDaMae = findViewById(R.id.tNomeDaMae);
        this.tZona = findViewById(R.id.tZona);
        this.tSecao = findViewById(R.id.tSecao);
        this.tMunicipio = findViewById(R.id.tMunicipio);

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

}
