package cat.inspedralbes.projectefutbol.GarHerManGabVicDan;

import java.io.Serializable;

public class Partido implements Serializable {

    String equipo1;
    String equipo2;
    String resultado;

    String id_partido;



    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


    public String getId_partido() {
        return id_partido;
    }

    public void setId_partido(String id_partido) {
        this.id_partido = id_partido;
    }

}
