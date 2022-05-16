package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class Resultados extends AppCompatActivity {
    final OkHttpClient client = new OkHttpClient();
    RecyclerView recyclerView;
    List<Partido> partidos;
    Partido partido;
    String nom_equipo1;
    String resultado;
    String nom_equipo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        recyclerView = findViewById(R.id.recyclerViewResultados);
        partidos = new ArrayList<>();
        partido = new Partido();

        consultaTabla();

    }

    private void consultaTabla() {
        Request request = new Request.Builder()
                .url("http://a18gabmantor.alumnes.labs.inspedralbes.cat/api.php/records/RESULTADOS?join=EQUIPOS")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.d("Prova", "FALLA");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Log.d("Response", "Entra");

                    try {

                        JSONObject jsObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsObject.getJSONArray("records");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            //Guarda resultado del partido
                            resultado = jsonObject.getString("RESULTADO");

                            //Guarda el nombre de los equipos 1 y 2
                            nom_equipo1 = jsonObject.getString("EQUIPO_1");
                            JSONObject equipo1obj = new JSONObject(nom_equipo1);
                            nom_equipo1 = equipo1obj.getString("NOMBRE");

                            nom_equipo2 = jsonObject.getString("EQUIPO_2");
                            JSONObject equipo2obj = new JSONObject(nom_equipo2);
                            nom_equipo2 = equipo2obj.getString("NOMBRE");

                            Log.i("Dentro", nom_equipo1 + " " + resultado + " " + nom_equipo2);
                            partido.setResultado(resultado);
                            partido.setEquipo1(nom_equipo1);
                            partido.setEquipo2(nom_equipo2);
                            partidos.add(partido);

                            Log.i("Dentro Objeto", partido.getEquipo1() + " " + partido.getResultado() + " " + partido.getEquipo2());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Prova", "Falla Json");
                    }
                }
            }
        });



        Log.i("Fuera", nom_equipo1 + " " + resultado + " " + nom_equipo2);
        Log.i("FueraLista", partido.getEquipo1() + " " + partido.getResultado() + " " + partido.getEquipo2());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterResultador adapterResultador = new AdapterResultador(partidos);
        recyclerView.setAdapter(adapterResultador);
    }


}

/*
for (int i = 0; i < 10; i++) {
        partido.setResultado("2-2");
        partido.setEquipo1("FC Barcelona");
        partido.setEquipo2("Real Madrid");
        partidos.add(partido);
        }
*/

