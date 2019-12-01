package kypm.com.mascotapp.modelo;

import android.graphics.drawable.Drawable;

public class Publicacion {

    int id;
    String nombre;
    String direccion;
    Float precio_hora;
    int cantidad_canchas;
    String foto;

    public Publicacion(int id, String nombre, String direccion, Float precio_hora, int cantidad_canchas,String foto) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.precio_hora = precio_hora;
        this.cantidad_canchas = cantidad_canchas;
        this.foto=foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Float getPrecio_hora() {
        return precio_hora;
    }

    public void setPrecio_hora(Float precio_hora) {
        this.precio_hora = precio_hora;
    }

    public int getCantidad_canchas() {
        return cantidad_canchas;
    }

    public void setCantidad_canchas(int cantidad_canchas) {
        this.cantidad_canchas = cantidad_canchas;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


}
