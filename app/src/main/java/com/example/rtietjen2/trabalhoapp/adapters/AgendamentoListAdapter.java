package com.example.rtietjen2.trabalhoapp.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rtietjen2.trabalhoapp.Entity.Agendamento;
import com.example.rtietjen2.trabalhoapp.MainActivity;
import com.example.rtietjen2.trabalhoapp.R;
import com.example.rtietjen2.trabalhoapp.Telas.TelaAgendamento;

import java.util.List;

public class AgendamentoListAdapter extends RecyclerView.Adapter<AgendamentoListAdapter.RecycleViewHolder> {

    private List<Agendamento> mList;
    private Context mContext;
    private TelaAgendamento activity;
    private AppCompatImageView fabRemove;

//    public ListAdapter(Context context, List<Agendamento> list) {
//        this.mContext = context;
//        this.activity = (TelaAgendamento) context;
//        this.mList = list;
//    }

    @Override
    public AgendamentoListAdapter.RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AgendamentoListAdapter.RecycleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected class RecycleViewHolder
        extends RecyclerView.ViewHolder {

        protected TextView tvTarefa;
        protected AppCompatImageView llRow;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            //TODO
//            tvTarefa = itemView.findViewById(R.id.tv_tarefa);
//            llRow = itemView.findViewById(R.id.fabRemove);
            llRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Agendamento tarefa = mList.get(getAdapterPosition());
                    activity.onClickList(tarefa);
                }
            });
        }
    }
}
