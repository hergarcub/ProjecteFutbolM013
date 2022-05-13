package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import android.os.Bundle;
import android.util.Log;

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


public class Resultados extends AppCompatActivity {
    final OkHttpClient client = new OkHttpClient();
    private RecyclerView recyclerView;
    List<Partido> partidos;
    Partido partido;
    int id_equipo1;
    String resultado;
    int id_equipo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        recyclerView = findViewById(R.id.recyclerViewResultados);
        partidos = new ArrayList<>();

        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void run() throws Exception {
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
                Log.d("Prova", "Entra");
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    String body = responseBody.string();
                    try {
                        JSONArray jsonArray = new JSONArray(body);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id_equipo1 = jsonObject.getInt("EQUIPO_1");
                            resultado = jsonObject.getString("RESULTADO");
                            id_equipo2 = jsonObject.getInt("EQUIPO_2");
                            Log.i("Prova", resultado);
                            partido.setResultado(resultado);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    System.out.println(responseBody.string());
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterResultador adapterResultador = new AdapterResultador(partidos);
        recyclerView.setAdapter(adapterResultador);
    }


}

















/*

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://a18gabmantor.alumnes.labs.inspedralbes.cat/api.php/records/RESULTADOS")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    String body = responseBody.string();
                    partido = new Partido();

                    try{
                        JSONArray jsonArray=new JSONArray(body);
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            id_equipo1=jsonObject.getInt("EQUIPO_1");
                            resultado=jsonObject.getString("RESULTADO");
                            id_equipo2=jsonObject.getInt("EQUIPO_2");
                            Log.i("Prova", String.valueOf(resultado));
                            partido.setResultado(resultado);

                        }

                            }



                     catch (JSONException e) {
                        e.printStackTrace();
                    }



                }






    }*/



  /*  final OkHttpClient client = new OkHttpClient();

    String  urlequips= "http://a18gabmantor.alumnes.labs.inspedralbes.cat/api.php/records/EQUIPOS?filter=ID,bt,"+id_equipo1+","+id_equipo2;

    Request request = new Request.Builder()
            .url(urlequips)
            .build();
                            client.newCall(request).enqueue(new Callback() {
@Override
public void onFailure(@NonNull Call call, @NonNull IOException e) {
        Log.d("Prova", "FALLA");
        }

@Override
public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        ResponseBody respBody = response.body();
        String body = respBody.string();

        try{
        JSONArray jsonArray=new JSONArray(body);
        for(int i=0;i<jsonArray.length();i++) {

        JSONObject jsonObject=jsonArray.getJSONObject(i);
        String nombre_equipo1=jsonObject.getString("EQUIPO_1");
        String nombre_equipo2=jsonObject.getString("EQUIPO_2");
        partido.setEquipo1(nombre_equipo1);
        partido.setEquipo2(nombre_equipo2);
        }
        partidos.add(partido);
        } catch (JSONException e) {
        e.printStackTrace();
        }*/