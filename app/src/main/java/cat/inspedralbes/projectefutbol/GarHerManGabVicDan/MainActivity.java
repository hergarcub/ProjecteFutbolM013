package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

                Intent intentHome = new Intent(this, Home.class);
                startActivity(intentHome);
                return true;

            case R.id.Login:

                return true;

            case R.id.Resultados:

                Intent intentResultado = new Intent(this, Resultados.class);
                startActivity(intentResultado);
                return true;

            case R.id.Clasificacion:

                Intent intentClasificacion = new Intent(this, Clasificacion.class);
                startActivity(intentClasificacion);
                return true;

            case R.id.Goleadores:

                Intent intentGoleador = new Intent(this, Goleadores.class);
                startActivity(intentGoleador);
                return true;

            case R.id.Jornades:

                Intent intentJornada = new Intent(this, Jornada.class);
                startActivity(intentJornada);
                return true;

            case R.id.Settings:

                Intent intent5 = new Intent(this, SettingsActivity.class);
                startActivity(intent5);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}