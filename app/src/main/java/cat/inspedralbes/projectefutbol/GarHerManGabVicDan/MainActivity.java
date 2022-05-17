package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

public class MainActivity extends AppCompatActivity {
    final OkHttpClient client = new OkHttpClient();
    RecyclerView recyclerView;
    List<Noticias> noticias;
    Noticias noticia;
    String titulo;
    String noticiaTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_noticias);
        recyclerView = findViewById(R.id.recyclerviewNoticias);
        noticias = new ArrayList<>();
        noticia = new Noticias();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterNoticias adapterNoticias = new AdapterNoticias(noticias);
        consultarNoticias();
        recyclerView.setAdapter(adapterNoticias);

    }

    private void consultarNoticias() {
        Request request = new Request.Builder()
                .url("http://a18gabmantor.alumnes.labs.inspedralbes.cat/api.php/records/NOTICIAS")
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
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            //Guarda resultado del partido
                            titulo = jsonObject.getString("TITULO");
                            noticiaTexto = jsonObject.getString("TEXTO");

                             Log.i("Dentro", titulo + " " + noticiaTexto);

                            noticia.setNoticia(noticiaTexto);
                            noticia.setTitulo(titulo);
                            noticias.add(noticia);
                            //Log.i("Dentro Objeto", partido.getEquipo1() + " " + partido.getResultado() + " " + partido.getEquipo2());
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("Prova", "Falla Json");
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.Home:

                return true;

            case R.id.Login:

                return true;

            case R.id.Resultados:
                Intent sendIntent = new Intent(this, Resultados.class );

                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                return true;

            case R.id.Clasificacion:

                return true;

            case R.id.Goleadores:

                return true;

            case R.id.Jornades:

                return true;

            case R.id.Settings:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}