package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPartido extends RecyclerView.Adapter<AdapterPartido.ViewHolder> {


    private List<PartidoDetalles> partidoDetalles;

    public AdapterPartido(List<PartidoDetalles> partidoDetalles) {
        this.partidoDetalles = partidoDetalles;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        private final TextView equipo1;
        private final TextView equipo2;
        private final TextView resultado;
        private final TextView resultado2;

        public ViewHolder(View v) {
            super(v);

            equipo1 = v.findViewById(R.id.equipo1DetallesPartidos);
            equipo2 = v.findViewById(R.id.equipo2DetallesPartidos);
            resultado = v.findViewById(R.id.resultadoDetallesEquipo1Partidos);
            resultado2 = v.findViewById(R.id.resultadoDetallesEquipo2Partidos);


        }

        public TextView getEquipo1() {
            return equipo1;
        }

        public TextView getEquipo2() {
            return equipo2;
        }

        public TextView getResultado() {
            return resultado;
        }

        public TextView getResultado2() {
            return resultado2;
        }
    }

    @Override
    public AdapterPartido.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.item_adapter, parent, false);
        AdapterPartido.ViewHolder vh = new AdapterPartido.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterPartido.ViewHolder holder, int position) {

        holder.getEquipo1().setText(partidoDetalles.get(position).getEquipo1());
        holder.getEquipo2().setText(partidoDetalles.get(position).getEquipo2());
        holder.getResultado().setText(partidoDetalles.get(position).getResultado());
        holder.getResultado2().setText(partidoDetalles.get(position).getResultado2());

    }


    @Override
    public int getItemCount() {
        return partidoDetalles.size();
    }
}
