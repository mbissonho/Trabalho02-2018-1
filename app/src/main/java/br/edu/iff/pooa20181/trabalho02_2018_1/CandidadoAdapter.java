package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CandidadoAdapter extends RecyclerView.Adapter{

    private List<Candidato> cadidatos;
    private Context ctx;
    private static ClickRecyclerViewListener clickRecyclerViewListener;


    public CandidadoAdapter(List<Candidato> cadidatos, Context ctx, ClickRecyclerViewListener clickRecyclerViewListener){
        this.cadidatos = cadidatos;
        this.ctx = ctx;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.ctx).inflate(R.layout.item_candidato, parent, false);
        CandidatoHolder candidatoHolder = new CandidatoHolder(view);

        return candidatoHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CandidatoHolder candidatoHolder = (CandidatoHolder) holder;

        Candidato candidato = this.cadidatos.get(position);

        candidatoHolder.nome.setText(candidato.getNome());
        candidatoHolder.numero.setText(candidato.getNumeroNaUrna());
        candidatoHolder.cargo.setText(candidato.getCargo());
        candidatoHolder.partido.setText(candidato.getPartido());
    }

    @Override
    public int getItemCount() {
        return this.cadidatos.size();
    }

    public class CandidatoHolder extends RecyclerView.ViewHolder {

        private TextView nome;
        private TextView numero;
        private TextView cargo;
        private TextView partido;

        public CandidatoHolder(View view){
            super(view);

            this.nome = view.findViewById(R.id.lNome);
            this.cargo = view.findViewById(R.id.lCargo);
            this.numero = view.findViewById(R.id.lNumero);
            this.partido = view.findViewById(R.id.lPartido);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickRecyclerViewListener.onClick(cadidatos.get(getLayoutPosition()));
                }
            });
        }

    }


}
