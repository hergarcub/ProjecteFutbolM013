package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterNoticias extends RecyclerView.Adapter<AdapterNoticias.ViewHolder> {

    private List<Noticias> noticias;
    public AdapterNoticias (List<Noticias> noticias){
        this.noticias = noticias;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final TextView textTitulo;
        private final TextView textNoticia;

        public ViewHolder(View v) {
            super(v);

            textNoticia = v.findViewById(R.id.textViewNoticia);
            textTitulo = v.findViewById(R.id.textViewTitulo);
        }

        public TextView getTextNoticia() {
            return textNoticia;
        }
        public TextView getTextTitulo() {
            return textTitulo;
        }

    }

    @Override
    public AdapterNoticias.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.item_noticas, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNoticias.ViewHolder holder, int position) {
        holder.getTextNoticia().setText(noticias.get(position).getNoticia());
        holder.getTextTitulo().setText(noticias.get(position).getTitulo());
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }
}
