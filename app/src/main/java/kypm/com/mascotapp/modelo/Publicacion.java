package kypm.com.mascotapp.modelo;

import android.graphics.drawable.Drawable;

public class Publicacion {

    //servicio atributos
    private String Nombre;
    private String fecha_perdida;
    private String Raza;
    private String Telefono;
    private int img;


    public Publicacion(String nombre, String fecha_perdida, String raza, String telefono, int img) {
        Nombre = nombre;
        this.fecha_perdida = fecha_perdida;
        Raza = raza;
        Telefono = telefono;
        this.img = img;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFecha_perdida() {
        return fecha_perdida;
    }

    public void setFecha_perdida(String fecha_perdida) {
        this.fecha_perdida = fecha_perdida;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
