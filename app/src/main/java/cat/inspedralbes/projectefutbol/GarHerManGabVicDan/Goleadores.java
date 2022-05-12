package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class Goleadores extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<Jugador> jugadores;
    String nombreJugador;
    int gol;
    double promedioGol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goleadores);
        recyclerView = findViewById(R.id.goleadoresID);
        jugadores = new ArrayList<>();
    }

    public void consultaGoleadores(){

        final OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://a18gabmantor.alumnes.labs.inspedralbes.cat/api.php/records/GOLEADORES")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                Log.d("PROVA", "HA FALLAT");

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {


                parseJSONGoleadores(response);

            }
        });

    }

    void parseJSONGoleadores(Response response) throws IOException{

        ResponseBody respBody = response.body();
        String body = respBody.string();

        try {

            JSONArray jsonArray = new JSONArray(body);

            for (int i = 0; i < jsonArray.length(); i++){

                JSONObject object = jsonArray.getJSONObject(i);
                nombreJugador = object.getString("NOMBRE");
                gol = object.getInt("GOL");
                promedioGol = object.getDouble("PROMEDIO");

            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void consultaJugadores(){

        final OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://a18gabmantor.alumnes.labs.inspedralbes.cat/api.php/records/JUGADORES")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                Log.d("PROVA", "HA FALLAT");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {




            }
        });
    }

    private void parseJSONJugadores(Response response) throws IOException{

        ResponseBody respBody = response.body();
        String body = respBody.string();



    }
}