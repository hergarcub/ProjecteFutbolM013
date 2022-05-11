package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Resultados extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<Partido> partidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        recyclerView = findViewById(R.id.recyclerViewResultados);
        partidos = new ArrayList<>();
        Partido partido = new Partido();
        for (int i = 0; i < 10; i++){

            partido.setResultado("4-0");
            partido.setEquipo1("FC Barcelona");
            partido.setEquipo2("Real Madrid");
            partidos.add(partido);
        }



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterResultador adapterResultador = new AdapterResultador(partidos);
        recyclerView.setAdapter(adapterResultador);
    }

}
