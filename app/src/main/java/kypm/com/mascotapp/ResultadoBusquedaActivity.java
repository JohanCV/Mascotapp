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
import java.util.List;

import kypm.com.mascotapp.adaptador.ResultadosAdaptador;
import kypm.com.mascotapp.modelo.Mascota;
import kypm.com.mascotapp.modelo.Publicacion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultadoBusquedaActivity extends AppCompatActivity {
    String url = "http://127.0.0.1/";
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
        Call<List<Publicacion>> result = subirImagen.listardog();

        result.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(Call<List<Publicacion>> call, Response<List<Publicacion>> response) {
                Log.e("Codigo: ", response.code() + "");

                switch (response.code()) {
                    case 200:

                        List<Publicacion> publicaciones = response.body();
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Publicacion>> call, Throwable t) {
                Log.e("Error App: ", t.getMessage());
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
            case "dog777.jpg":
                mascotas.add(new Mascota(R.drawable.dog778));
                mascotas.add(new Mascota(R.drawable.dog354));
                mascotas.add(new Mascota(R.drawable.dog10));
                mascotas.add(new Mascota(R.drawable.dog196));
                mascotas.add(new Mascota(R.drawable.dog535));
                mascotas.add(new Mascota(R.drawable.dog559));
                mascotas.add(new Mascota(R.drawable.dog480));
                mascotas.add(new Mascota(R.drawable.dog337));
                mascotas.add(new Mascota(R.drawable.dog548));
                mascotas.add(new Mascota(R.drawable.dog88));
                mascotas.add(new Mascota(R.drawable.dog641));
                mascotas.add(new Mascota(R.drawable.dog760));
                mascotas.add(new Mascota(R.drawable.dog383));
                mascotas.add(new Mascota(R.drawable.dog143));

                break;
            case "dog762.jpg":
                mascotas.add(new Mascota(R.drawable.dog29));
                mascotas.add(new Mascota(R.drawable.dog562));
                mascotas.add(new Mascota(R.drawable.dog724));
                mascotas.add(new Mascota(R.drawable.dog628));
                mascotas.add(new Mascota(R.drawable.dog645));
                mascotas.add(new Mascota(R.drawable.dog282));
                mascotas.add(new Mascota(R.drawable.dog79));
                mascotas.add(new Mascota(R.drawable.dog219));
                mascotas.add(new Mascota(R.drawable.dog284));
                mascotas.add(new Mascota(R.drawable.dog287));
                mascotas.add(new Mascota(R.drawable.dog596));
                mascotas.add(new Mascota(R.drawable.dog473));
                mascotas.add(new Mascota(R.drawable.dog574));
                mascotas.add(new Mascota(R.drawable.dog42));
                break;
            case "dog766.jpg":
                mascotas.add(new Mascota(R.drawable.dog536));
                mascotas.add(new Mascota(R.drawable.dog104));
                mascotas.add(new Mascota(R.drawable.dog108));
                mascotas.add(new Mascota(R.drawable.dog17));
                mascotas.add(new Mascota(R.drawable.dog562));
                mascotas.add(new Mascota(R.drawable.dog322));
                mascotas.add(new Mascota(R.drawable.dog179));
                mascotas.add(new Mascota(R.drawable.dog260));
                mascotas.add(new Mascota(R.drawable.dog711));
                mascotas.add(new Mascota(R.drawable.dog490));
                mascotas.add(new Mascota(R.drawable.dog445));
                mascotas.add(new Mascota(R.drawable.dog198));
                mascotas.add(new Mascota(R.drawable.dog407));
                mascotas.add(new Mascota(R.drawable.dog279));
                break;
            case "dog765.jpg":
                mascotas.add(new Mascota(R.drawable.dog755));
                mascotas.add(new Mascota(R.drawable.dog3));
                mascotas.add(new Mascota(R.drawable.dog204));
                mascotas.add(new Mascota(R.drawable.dog144));
                mascotas.add(new Mascota(R.drawable.dog526));
                mascotas.add(new Mascota(R.drawable.dog713));
                mascotas.add(new Mascota(R.drawable.dog277));
                mascotas.add(new Mascota(R.drawable.dog587));
                mascotas.add(new Mascota(R.drawable.dog264));
                mascotas.add(new Mascota(R.drawable.dog743));
                mascotas.add(new Mascota(R.drawable.dog621));
                mascotas.add(new Mascota(R.drawable.dog519));
                mascotas.add(new Mascota(R.drawable.dog461));
                mascotas.add(new Mascota(R.drawable.dog406));
                break;
            case "dog764.jpg":
                mascotas.add(new Mascota(R.drawable.dog607));
                mascotas.add(new Mascota(R.drawable.dog581));
                mascotas.add(new Mascota(R.drawable.dog112));
                mascotas.add(new Mascota(R.drawable.dog410));
                mascotas.add(new Mascota(R.drawable.dog328));
                mascotas.add(new Mascota(R.drawable.dog71));
                mascotas.add(new Mascota(R.drawable.dog196));
                mascotas.add(new Mascota(R.drawable.dog46));
                mascotas.add(new Mascota(R.drawable.dog733));
                mascotas.add(new Mascota(R.drawable.dog545));
                mascotas.add(new Mascota(R.drawable.dog384));
                mascotas.add(new Mascota(R.drawable.dog396));
                mascotas.add(new Mascota(R.drawable.dog467));
                mascotas.add(new Mascota(R.drawable.dog329));
                break;
            case "dog767.jpg":
                mascotas.add(new Mascota(R.drawable.dog576));
                mascotas.add(new Mascota(R.drawable.dog52));
                mascotas.add(new Mascota(R.drawable.dog710));
                mascotas.add(new Mascota(R.drawable.dog310));
                mascotas.add(new Mascota(R.drawable.dog425));
                mascotas.add(new Mascota(R.drawable.dog503));
                mascotas.add(new Mascota(R.drawable.dog620));
                mascotas.add(new Mascota(R.drawable.dog565));
                mascotas.add(new Mascota(R.drawable.dog252));
                mascotas.add(new Mascota(R.drawable.dog172));
                mascotas.add(new Mascota(R.drawable.dog572));
                mascotas.add(new Mascota(R.drawable.dog207));
                mascotas.add(new Mascota(R.drawable.dog174));
                mascotas.add(new Mascota(R.drawable.dog466));
                break;
            case "dog770.jpg":
                mascotas.add(new Mascota(R.drawable.dog681));
                mascotas.add(new Mascota(R.drawable.dog295));
                mascotas.add(new Mascota(R.drawable.dog757));
                mascotas.add(new Mascota(R.drawable.dog195));
                mascotas.add(new Mascota(R.drawable.dog43));
                mascotas.add(new Mascota(R.drawable.dog645));
                mascotas.add(new Mascota(R.drawable.dog357));
                mascotas.add(new Mascota(R.drawable.dog594));
                mascotas.add(new Mascota(R.drawable.dog209));
                mascotas.add(new Mascota(R.drawable.dog560));
                mascotas.add(new Mascota(R.drawable.dog684));
                mascotas.add(new Mascota(R.drawable.dog221));
                mascotas.add(new Mascota(R.drawable.dog287));
                mascotas.add(new Mascota(R.drawable.dog658));
                break;
            case "dog768.jpg":
                mascotas.add(new Mascota(R.drawable.dog446));
                mascotas.add(new Mascota(R.drawable.dog85));
                mascotas.add(new Mascota(R.drawable.dog472));
                mascotas.add(new Mascota(R.drawable.dog574));
                mascotas.add(new Mascota(R.drawable.dog11));
                mascotas.add(new Mascota(R.drawable.dog558));
                mascotas.add(new Mascota(R.drawable.dog334));
                mascotas.add(new Mascota(R.drawable.dog29));
                mascotas.add(new Mascota(R.drawable.dog743));
                mascotas.add(new Mascota(R.drawable.dog136));
                mascotas.add(new Mascota(R.drawable.dog687));
                mascotas.add(new Mascota(R.drawable.dog161));
                mascotas.add(new Mascota(R.drawable.dog27));
                mascotas.add(new Mascota(R.drawable.dog738));
                break;
            case "dog769.jpg":
                mascotas.add(new Mascota(R.drawable.dog173));
                mascotas.add(new Mascota(R.drawable.dog433));
                mascotas.add(new Mascota(R.drawable.dog34));
                mascotas.add(new Mascota(R.drawable.dog604));
                mascotas.add(new Mascota(R.drawable.dog408));
                mascotas.add(new Mascota(R.drawable.dog251));
                mascotas.add(new Mascota(R.drawable.dog730));
                mascotas.add(new Mascota(R.drawable.dog76));
                mascotas.add(new Mascota(R.drawable.dog613));
                mascotas.add(new Mascota(R.drawable.dog551));
                mascotas.add(new Mascota(R.drawable.dog498));
                mascotas.add(new Mascota(R.drawable.dog603));
                mascotas.add(new Mascota(R.drawable.dog87));
                mascotas.add(new Mascota(R.drawable.dog758));
                break;
            case "dog757.jpg":
                mascotas.add(new Mascota(R.drawable.dog681));
                mascotas.add(new Mascota(R.drawable.dog138));
                mascotas.add(new Mascota(R.drawable.dog658));
                mascotas.add(new Mascota(R.drawable.dog655));
                mascotas.add(new Mascota(R.drawable.dog593));
                mascotas.add(new Mascota(R.drawable.dog770));
                mascotas.add(new Mascota(R.drawable.dog295));
                mascotas.add(new Mascota(R.drawable.dog209));
                mascotas.add(new Mascota(R.drawable.dog219));
                mascotas.add(new Mascota(R.drawable.dog24));
                mascotas.add(new Mascota(R.drawable.dog560));
                mascotas.add(new Mascota(R.drawable.dog157));
                mascotas.add(new Mascota(R.drawable.dog398));
                mascotas.add(new Mascota(R.drawable.dog596));
                break;
            case "dog758.jpg":
                mascotas.add(new Mascota(R.drawable.dog717));
                mascotas.add(new Mascota(R.drawable.dog175));
                mascotas.add(new Mascota(R.drawable.dog730));
                mascotas.add(new Mascota(R.drawable.dog118));
                mascotas.add(new Mascota(R.drawable.dog640));
                mascotas.add(new Mascota(R.drawable.dog292));
                mascotas.add(new Mascota(R.drawable.dog87));
                mascotas.add(new Mascota(R.drawable.dog294));
                mascotas.add(new Mascota(R.drawable.dog548));
                mascotas.add(new Mascota(R.drawable.dog315));
                mascotas.add(new Mascota(R.drawable.dog514));
                mascotas.add(new Mascota(R.drawable.dog355));
                mascotas.add(new Mascota(R.drawable.dog624));
                mascotas.add(new Mascota(R.drawable.dog685));
                break;
            case "dog760.jpg":
                mascotas.add(new Mascota(R.drawable.dog61));
                mascotas.add(new Mascota(R.drawable.dog383));
                mascotas.add(new Mascota(R.drawable.dog337));
                mascotas.add(new Mascota(R.drawable.dog636));
                mascotas.add(new Mascota(R.drawable.dog674));
                mascotas.add(new Mascota(R.drawable.dog158));
                mascotas.add(new Mascota(R.drawable.dog98));
                mascotas.add(new Mascota(R.drawable.dog143));
                mascotas.add(new Mascota(R.drawable.dog565));
                mascotas.add(new Mascota(R.drawable.dog391));
                mascotas.add(new Mascota(R.drawable.dog445));
                mascotas.add(new Mascota(R.drawable.dog471));
                mascotas.add(new Mascota(R.drawable.dog397));
                mascotas.add(new Mascota(R.drawable.dog156));
                break;
            case "dog761.jpg":
                mascotas.add(new Mascota(R.drawable.dog723));
                mascotas.add(new Mascota(R.drawable.dog720));
                mascotas.add(new Mascota(R.drawable.dog161));
                mascotas.add(new Mascota(R.drawable.dog16));
                mascotas.add(new Mascota(R.drawable.dog249));
                mascotas.add(new Mascota(R.drawable.dog641));
                mascotas.add(new Mascota(R.drawable.dog468));
                mascotas.add(new Mascota(R.drawable.dog430));
                mascotas.add(new Mascota(R.drawable.dog5));
                mascotas.add(new Mascota(R.drawable.dog393));
                mascotas.add(new Mascota(R.drawable.dog457));
                mascotas.add(new Mascota(R.drawable.dog293));
                mascotas.add(new Mascota(R.drawable.dog53));
                mascotas.add(new Mascota(R.drawable.dog269));
                break;
            case "dog778.jpg":
                mascotas.add(new Mascota(R.drawable.dog777));
                mascotas.add(new Mascota(R.drawable.dog354));
                mascotas.add(new Mascota(R.drawable.dog641));
                mascotas.add(new Mascota(R.drawable.dog548));
                mascotas.add(new Mascota(R.drawable.dog335));
                mascotas.add(new Mascota(R.drawable.dog337));
                mascotas.add(new Mascota(R.drawable.dog88));
                mascotas.add(new Mascota(R.drawable.dog156));
                mascotas.add(new Mascota(R.drawable.dog196));
                mascotas.add(new Mascota(R.drawable.dog640));
                mascotas.add(new Mascota(R.drawable.dog535));
                mascotas.add(new Mascota(R.drawable.dog135));
                mascotas.add(new Mascota(R.drawable.dog559));
                mascotas.add(new Mascota(R.drawable.dog182));
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
