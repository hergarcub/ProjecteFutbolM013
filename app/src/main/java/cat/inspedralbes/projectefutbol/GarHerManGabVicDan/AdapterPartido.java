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


    private List<Jugador> jugadores;

    public AdapterPartido(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        private final TextView nombreJugador;
        private final TextView apellidoJugador;


        public ViewHolder(View v) {
            super(v);

            nombreJugador = v.findViewById(R.id.textViewNombre);
            apellidoJugador = v.findViewById(R.id.textViewApellido);

        }

        public TextView getNombreJugador() {
            return nombreJugador;
        }

        public TextView getApellidoJugador() {
            return apellidoJugador;
        }

    }

    @Override
    public AdapterPartido.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.item_jugadores, parent, false);
        AdapterPartido.ViewHolder vh = new AdapterPartido.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterPartido.ViewHolder holder, int position) {

        holder.getNombreJugador().setText(jugadores.get(position).getNombre());
        holder.getApellidoJugador().setText(jugadores.get(position).getApellido());


    }


    @Override
    public int getItemCount() {
        return jugadores.size();
    }
}
