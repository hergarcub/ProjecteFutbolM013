package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterResultador extends RecyclerView.Adapter<AdapterResultador.ViewHolder> {

    public List<Partido> partidos;
    Partido partido;
    public String id_partido;
    public AdapterResultador (List<Partido> partidos){
        this.partidos = partidos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final TextView equipo1;
        private final TextView equipo2;
        private final TextView resultado;


        public ViewHolder(View v) {
            super(v);

            equipo1 = v.findViewById(R.id.textViewEq1);
            equipo2 = v.findViewById(R.id.textViewEq2);
            resultado = v.findViewById(R.id.textViewRes);
            resultado.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    Intent intentDetails = new Intent(v.getContext(), DetallesPartido.class);

                    Log.d("prueba","Element " + getAdapterPosition() + " clicked.");



                    //intentDetails.putExtra("ID",id_partido);

                    v.getContext().startActivity(intentDetails);
                }

            });
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
    }

    @Override
    public AdapterResultador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.item_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterResultador.ViewHolder holder, int position) {

        holder.getEquipo1().setText(partidos.get(position).getEquipo1());
        holder.getEquipo2().setText(partidos.get(position).getEquipo2());
        holder.getResultado().setText(partidos.get(position).getResultado());


    }


    @Override
    public int getItemCount() {
        return partidos.size();
    }
}
