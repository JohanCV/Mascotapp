package kypm.com.mascotapp.modelo;

import android.graphics.drawable.Drawable;

public class Publicacion {

    int id;
    int imagenes;

    public Publicacion(int imagenes) {
        this.imagenes = imagenes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagenes() {
        return imagenes;
    }

    public void setImagenes(int imagenes) {
        this.imagenes = imagenes;
    }
}
