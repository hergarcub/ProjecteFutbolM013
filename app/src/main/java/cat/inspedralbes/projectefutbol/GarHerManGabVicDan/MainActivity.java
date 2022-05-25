package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    final OkHttpClient client = new OkHttpClient();
    AdapterNoticias adapterNoticias;
    RecyclerView recyclerView;
    List<Noticias> noticias;
    String[] jornadas;
    Noticias noticia;
    String titulo;
    String noticiaTexto;
    Spinner spinnerCategoria;
    Spinner spinnerJornada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_noticias);
        recyclerView = findViewById(R.id.recyclerviewNoticias);
        spinnerJornada = findViewById(R.id.spinnerJornada);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);

        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this,
                R.array.Categorias, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);

        spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
        spinnerCategoria.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterJornada = ArrayAdapter.createFromResource(this,
                R.array.jornades, android.R.layout.simple_spinner_item);
        adapterJornada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJornada.setAdapter(adapterJornada);

        spinnerJornada = (Spinner) findViewById(R.id.spinnerJornada);
        spinnerJornada.setOnItemSelectedListener(this);

        noticias = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapterNoticias = new AdapterNoticias(noticias);
        recyclerView.setAdapter(adapterNoticias);
        consultarNoticias();



    }

    public void onClickIrResultado(View view){
        String JornadaValor = spinnerJornada.getSelectedItem().toString();
        Intent intentResultado = new Intent(this, Resultados.class);
        intentResultado.putExtra("jornada", JornadaValor);
        startActivity(intentResultado);

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
                            noticia = new Noticias();
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
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("sjdfbh","run: corre");
                        adapterNoticias.notifyDataSetChanged();
                        }
                    });

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


            case R.id.Settings:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}