package hibernate3;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Alphonz
 */
class Ciudad implements Serializable{
    private Long id;
    private String nombre;
    private List alumnos = new ArrayList();

    public Ciudad() {
    }

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List alumnos) {
        this.alumnos = alumnos;
    }
}
