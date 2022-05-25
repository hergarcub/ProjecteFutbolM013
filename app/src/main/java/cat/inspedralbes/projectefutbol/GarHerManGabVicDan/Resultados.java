package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

public class Resultados extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    final OkHttpClient client = new OkHttpClient();
    AdapterResultador adapterResultador;
    RecyclerView recyclerView;
    List<Partido> partidos;
    Spinner spinnerJornada;
    Partido partido;
    String nom_equipo1;
    String resultado;
    String nom_equipo2;
    String id_partido;
    TextView textViewCategoriaResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        recyclerView = findViewById(R.id.recyclerViewResultados);
        partidos = new ArrayList<>();
        spinnerJornada = findViewById(R.id.spinnerJornadaResultados);
        textViewCategoriaResultado = findViewById(R.id.textViewCategoriaResultado);
        textViewCategoriaResultado.setText("LIGA PRIMERA DIVISIÓN CATALANA FUTBOL SALA");

        ArrayAdapter<CharSequence> adapterJornada = ArrayAdapter.createFromResource(this,
                R.array.jornades, android.R.layout.simple_spinner_item);
        adapterJornada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJornada.setAdapter(adapterJornada);

        spinnerJornada = (Spinner) findViewById(R.id.spinnerJornadaResultados);
        spinnerJornada.setOnItemSelectedListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterResultador = new AdapterResultador(partidos);
        recyclerView.setAdapter(adapterResultador);



        String id_jornada = (String) getIntent().getSerializableExtra("jornada");
        if(id_jornada != null){
            Log.d("Id_jornada", id_jornada);
            consultaTabla(id_jornada);
        }else{
            consultaJornadas();
        }



    }

    private void consultaJornadas() {
        Request request = new Request.Builder()
                .url("http://a18gabmantor.alumnes.labs.inspedralbes.cat/api.php/records/RESULTADOS?join=EQUIPOS")
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
                            partido = new Partido();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            //Guarda resultado del partido
                            id_partido = jsonObject.getString("ID");
                            resultado = jsonObject.getString("RESULTADO");
                            //Guarda el nombre de los equipos 1 y 2
                            nom_equipo1 = jsonObject.getString("EQUIPO_1");
                            JSONObject equipo1obj = new JSONObject(nom_equipo1);
                            nom_equipo1 = equipo1obj.getString("NOMBRE");

                            nom_equipo2 = jsonObject.getString("EQUIPO_2");
                            JSONObject equipo2obj = new JSONObject(nom_equipo2);
                            nom_equipo2 = equipo2obj.getString("NOMBRE");

                            //Log.i("Dentro", nom_equipo1 + " " + resultado + " " + nom_equipo2);
                            partido.setId_partido(id_partido);
                            partido.setResultado(resultado);
                            partido.setEquipo1(nom_equipo1);
                            partido.setEquipo2(nom_equipo2);
                            partidos.add(partido);

                        }

                        //Log.i("Dentro Objeto", partido.getEquipo1() + " " + partido.getResultado() + " " + partido.getEquipo2());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Resultados.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            adapterResultador.notifyDataSetChanged();

                        }
                    });

                }
            }

        });
    }

    public void consultaTabla(String id_jornada) {

        Request request = new Request.Builder()
                .url("http://a18gabmantor.alumnes.labs.inspedralbes.cat/api.php/records/RESULTADOS?join=EQUIPOS")
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

                            partido = new Partido();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            //Guarda resultado del partido
                                String id_JornadaConsulta = jsonObject.getString("ID_JORNADA");
                                if (id_jornada.equals(id_JornadaConsulta)) {
                                    Log.d("Comprobacion", id_jornada +" "+ id_JornadaConsulta);
                                    id_partido = jsonObject.getString("ID");
                                resultado = jsonObject.getString("RESULTADO");
                                //Guarda el nombre de los equipos 1 y 2
                                nom_equipo1 = jsonObject.getString("EQUIPO_1");
                                JSONObject equipo1obj = new JSONObject(nom_equipo1);
                                nom_equipo1 = equipo1obj.getString("NOMBRE");

                                nom_equipo2 = jsonObject.getString("EQUIPO_2");
                                JSONObject equipo2obj = new JSONObject(nom_equipo2);
                                nom_equipo2 = equipo2obj.getString("NOMBRE");

                                //Log.i("Dentro", nom_equipo1 + " " + resultado + " " + nom_equipo2);
                                partido.setId_partido(id_partido);
                                partido.setResultado(resultado);
                                partido.setEquipo1(nom_equipo1);
                                partido.setEquipo2(nom_equipo2);
                                partidos.add(partido);

                            }

                            //Log.i("Dentro Objeto", partido.getEquipo1() + " " + partido.getResultado() + " " + partido.getEquipo2());
                        }

                            Resultados.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    adapterResultador.notifyDataSetChanged();

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getItemAtPosition(position).equals("ELIGE UNA PAGINA")){

        }else {

            String jornada = parent.getItemAtPosition(position).toString();
            partidos.clear();
            consultaTabla(jornada);
        }



    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}