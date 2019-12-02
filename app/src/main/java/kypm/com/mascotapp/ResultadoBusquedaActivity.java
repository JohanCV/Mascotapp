package kypm.com.mascotapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kypm.com.mascotapp.adaptador.ResultadosAdaptador;
import kypm.com.mascotapp.modelo.Mascota;
import kypm.com.mascotapp.modelo.Publicacion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultadoBusquedaActivity extends AppCompatActivity {
    String url = "http://gohan1992.pythonanywhere.com/";
    private ImageView regresar;
    private TextView nombreCabecera,resul;

    Bundle parametros;
    String nombre_Imagen_recibida;
    ListView lvDog;
    ResultadosAdaptador resultadosAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_busqueda);

        parametros = this.getIntent().getExtras();

        findElemente();
        eventos();

        resultados();

    }

    private void findElemente() {
        regresar =(ImageView) findViewById(R.id.regresarPerfilEditar);
        nombreCabecera = (TextView) findViewById(R.id.perfilUsuarioNombreEditar);
        resul = findViewById(R.id.textViewResul);
        lvDog = findViewById(R.id.lvDogs);
    }

    private void resultados(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servicios subirImagen = retrofit.create(Servicios.class);

        Call<Publicacion> buscarDog_byImg = subirImagen.subirFotos();

        buscarDog_byImg.enqueue(new Callback<Publicacion>() {
            @Override
            public void onResponse(Call<Publicacion> call, Response<Publicacion> response) {
                switch (response.code()) {
                    case 201:
                        Publicacion p = response.body();
                        break;
                    default:
                        Log.e("errorp", "" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Publicacion> call, Throwable t) {
                Log.e("Error publicacion", t.getMessage());
            }
        });
    }
    private void eventos() {
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regresarHome = new Intent(getApplicationContext(), CrearPublicacionActivity.class);
                //startActivity(regresarHome);
                finish();
            }
        });

        nombreCabecera.setText("Resultados");

        resultadosAdaptador = new ResultadosAdaptador(getApplicationContext(),data(getNombre()));
        lvDog.setAdapter(resultadosAdaptador);

            /*int[] flags_img = new int[]{
                    R.drawable.dog100,
                    R.drawable.dog4,
            };

            for (int i=0;i< flags_img.length; i++){
                Log.e("flags_img: ", String.valueOf(flags_img[i]));
                intTostringDireccion =String.valueOf(flags_img[i]);

                Log.e("intTostringDireccion: ", intTostringDireccion);
                Log.e("completo: ",completo);
                if (completo.equals(intTostringDireccion)){
                    //iv.setImageResource(Integer.parseInt(completo));
                }
            }
            iv.setBackgroundResource(Integer.parseInt(completo));*/
            /*Drawable image  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.dog100);
            String uri = "@drawable/"+nombre_Imagen_recibida;
            Log.e("nombre img", uri);
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());*/
            //Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);

            //iv.setImageResource(R.drawable.dog4);


    }

    private ArrayList<Mascota> data(String nombre) {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        switch (nombre) {
            case "dog100.jpg":
                mascotas.add(new Mascota(R.drawable.dog90));
                mascotas.add(new Mascota(R.drawable.dog10));

                break;
            case "dog4.jpg":
                mascotas.add(new Mascota(R.drawable.dog4));
                mascotas.add(new Mascota(R.drawable.dog43));
                break;


                default: resul.setText("No hay ningun Parecido");
                break;

        }

        return mascotas;
    }

    private String getNombre(){
        if (parametros != null) {
            String inicio = "R.drawable.";
            nombre_Imagen_recibida = parametros.getString("sendNombreImagen");

            int find_point = nombre_Imagen_recibida.indexOf(".");
            String nom_img_sinjpg = nombre_Imagen_recibida.substring(0, find_point);
            String completo = inicio + nom_img_sinjpg;
        }
        return nombre_Imagen_recibida;
    }
}
