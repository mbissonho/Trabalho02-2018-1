package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnCandidato;
    private Button btnEleitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnCandidato = findViewById(R.id.btnCandidato);
        this.btnEleitor = findViewById(R.id.btnEleitor);

        this.btnCandidato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CandidatoListActivity.class);
                startActivity(intent);
            }
        });

        this.btnEleitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EleitorListActivity.class);
                startActivity(intent);
            }
        });
    }
}
