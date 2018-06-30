package br.edu.iff.pooa20181.trabalho02_2018_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EleitorAdapter extends RecyclerView.Adapter{

    private List<Eleitor> eleitores;
    private Context ctx;
    private static ClickRecyclerViewListener clickRecyclerViewListener;


    public EleitorAdapter(List<Eleitor> eleitores, Context ctx, ClickRecyclerViewListener clickRecyclerViewListener){
        this.eleitores = eleitores;
        this.ctx = ctx;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.ctx).inflate(R.layout.item_eleitor, parent, false);
        EleitorHolder eleitorHolder = new EleitorHolder(view);

        return eleitorHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EleitorHolder eleitorHolder = (EleitorHolder) holder;

        Eleitor eleitor = this.eleitores.get(position);

        eleitorHolder.nomeEleitor.setText(eleitor.getNome());
        eleitorHolder.numeroTitulo.setText(eleitor.getNumeroDoTitulo());
        eleitorHolder.zona.setText(eleitor.getZona());
        eleitorHolder.secao.setText(eleitor.getSecao());
    }

    @Override
    public int getItemCount() {
        return this.eleitores.size();
    }

    public class EleitorHolder extends RecyclerView.ViewHolder {

        private TextView nomeEleitor;
        private TextView numeroTitulo;
        private TextView zona;
        private TextView secao;

        public EleitorHolder(View view){
            super(view);

            this.nomeEleitor = view.findViewById(R.id.lNomeEleitor);
            this.numeroTitulo = view.findViewById(R.id.lNumeroTitulo);
            this.zona = view.findViewById(R.id.lZona);
            this.secao = view.findViewById(R.id.lSecao);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickRecyclerViewListener.onClick(eleitores.get(getLayoutPosition()));
                }
            });
        }

    }

}
