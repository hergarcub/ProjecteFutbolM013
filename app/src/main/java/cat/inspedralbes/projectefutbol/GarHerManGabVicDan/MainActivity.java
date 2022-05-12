package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_noticias);
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
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
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

                Intent intentGoleador = new Intent(this, Goleadores.class);
                startActivity(intentGoleador);
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