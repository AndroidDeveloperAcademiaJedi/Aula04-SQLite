package com.academiajedi.androiddveloper.exemplosqlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alexsoaresdesiqueira on 08/02/17.
 */

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PersonViewHolder> {

    private List<Pessoa> pessoaList;
    private RecycleViewClickHack recycleViewClickHack;

    public PessoaAdapter(List<Pessoa> pessoaList){
        this.pessoaList = pessoaList;
    }


    public class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView tvNome;
        TextView tvIdade;
        TextView tvSexo;

        PersonViewHolder(View itemView) {
            super(itemView);

            tvNome = (TextView) itemView.findViewById(R.id.tvUsuario);
            tvIdade = (TextView) itemView.findViewById(R.id.tvIdade);
            tvSexo = (TextView) itemView.findViewById(R.id.tvSexo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recycleViewClickHack != null){
                        recycleViewClickHack.onClickListener(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(recycleViewClickHack != null){
                        recycleViewClickHack.onLongClickListener(getAdapterPosition());
                    }
                    return false;
                }
            });

        }
    }

    public void setRecycleViewClick(RecycleViewClickHack recycleViewClickHack){
        this.recycleViewClickHack = recycleViewClickHack;
    }

    public void mudarListaAdapter(List<Pessoa> pessoaList){
        this.pessoaList = pessoaList;
        notifyDataSetChanged();
    }

    public void removerItem(int position){
        notifyItemRemoved(position);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pessoa, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.tvNome.setText(pessoaList.get(position).getNome());
        holder.tvIdade.setText(Integer.toString(pessoaList.get(position).getIdade()));
        holder.tvSexo.setText(pessoaList.get(position).getSexo());
    }

    @Override
    public int getItemCount() {
        return pessoaList.size();
    }

}
