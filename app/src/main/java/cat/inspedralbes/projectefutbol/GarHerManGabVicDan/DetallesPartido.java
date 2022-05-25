package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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

public class DetallesPartido extends AppCompatActivity {


    final OkHttpClient client = new OkHttpClient();
    AdapterPartido adapterDetallesPartido;
    RecyclerView recyclerView;
    List<Jugador> jugadores;
    Jugador jugador;
    PartidoDetalles partidoDetalles;
    String id_partido;
    String nom_equipo1;
    String resultado;
    String resultado2;
    String nom_equipo2;
    String goles;
    String nomJugador;
    String apellidoJugador;
    String id_partidoResultados;
    TextView nom_equipo1Text;
    TextView nom_equipo2Text;
    TextView resultadoText;
    TextView resultado2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_partido);
        recyclerView = findViewById(R.id.recyclerViewDetallesPartido);
        jugadores = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterDetallesPartido = new  AdapterPartido(jugadores);
        recyclerView.setAdapter(adapterDetallesPartido);

        consultaDetallesPartido();
        consultaGolesJugadores();

    }

    public void consultaDetallesPartido() {
        Partido partido = (Partido) getIntent().getSerializableExtra("ObjetoPartido");
        nom_equipo1 = partido.getEquipo1();
        nom_equipo1Text = findViewById(R.id.equipo1DetallesPartidos);
        nom_equipo1Text.setText(nom_equipo1);

        id_partidoResultados = partido.getId_partido();

        nom_equipo2 = partido.getEquipo2();
        nom_equipo2Text = findViewById(R.id.equipo2DetallesPartidos);
        nom_equipo2Text.setText(nom_equipo2);

        resultado = partido.getResultado();
        String[] resultadosSplit = resultado.split("-");
        resultado = resultadosSplit[0];
        resultado2 = resultadosSplit[1];

        resultadoText = findViewById(R.id.resultadoDetallesEquipo1Partidos);
        resultadoText.setText(resultado);

        resultado2Text = findViewById(R.id.resultadoDetallesEquipo2Partidos);
        resultado2Text.setText(resultado2);

    }

   private void consultaGolesJugadores() {

        Request request = new Request.Builder()
                .url("http://a18gabmantor.alumnes.labs.inspedralbes.cat/api.php/records/GOLES?join=JUGADORES")
                .build();
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.d("Prova", "FALLA");
            }

            public void onResponse(Call call, Response response) throws IOException {

                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    try {

                        JSONObject jsObject = new JSONObject(responseBody.string());
                        JSONArray jsonArray = jsObject.getJSONArray("records");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jugador = new Jugador();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            id_partido = jsonObject.getString("ID_RESULTADOS");
                            goles = jsonObject.getString("GOL");
                            int golesNum = Integer.parseInt(goles);
                            Log.d("Comprobacion", id_partido +" "+ id_partidoResultados);
                            if (id_partidoResultados.equals(id_partido)) {
                                for (int j = 0; j < golesNum; j++) {
                                    Log.d("Goles", String.valueOf(golesNum));
                                    jugador = new Jugador();
                                    nomJugador = jsonObject.getString("ID_JUGADORES");
                                    JSONObject jugadorObjeto = new JSONObject(nomJugador);
                                    nomJugador = jugadorObjeto.getString("NOMBRE");
                                    apellidoJugador = jugadorObjeto.getString("APELLIDO");
                                    jugador.setNombre(nomJugador);
                                    jugador.setApellido(apellidoJugador);
                                    jugadores.add(jugador);
                                }
                            }
                            //Log.i("Dentro", nom_equipo1 + " " + resultado + " " + nom_equipo2);
                           // Log.i("Dentro Objeto", partido.getEquipo1() + " " + partido.getResultado() + " " + partido.getEquipo2());
                        }

                        DetallesPartido.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("Run","run: corre");
                             //   Log.i("Run Objeto", partido.getEquipo1() + " " + partido.getResultado() + " " + partido.getEquipo2());
                                adapterDetallesPartido.notifyDataSetChanged();

                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Prova", "Falla Json");
                    }

                }
            }
        });
    }



}
