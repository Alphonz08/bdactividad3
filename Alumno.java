
package hibernate3;

import java.io.Serializable;

/**
 *
 * @author Alphonz
 */
public class Alumno implements Serializable{
    private Long id;
    protected String nombre;
    protected int edad;
    protected Long ciudad;

    public Alumno() {
    }

    public Alumno(String nombre, Integer edad, Long ciudad) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
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

    public int getEdad() {
        return edad;
    }

    public Long getCiudad() {
        return ciudad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCiudad(Long ciudad) {
        this.ciudad = ciudad;
    }
    
}
