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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultadoBusquedaActivity extends AppCompatActivity {

    private ImageView regresar;
    private TextView nombreCabecera;

    TextView recuperarnombreImg;

    Bundle parametros;

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_busqueda);

        parametros = this.getIntent().getExtras();

        findElemente();
        eventos();

    }

    private void findElemente() {
        regresar =(ImageView) findViewById(R.id.regresarPerfilEditar);
        nombreCabecera = (TextView) findViewById(R.id.perfilUsuarioNombreEditar);

        recuperarnombreImg = findViewById(R.id.txt_nombreimg);

        iv = findViewById(R.id.imageViewDog);
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

        if(parametros != null) {
            String inicio= "R.drawable.";
            String nombre_Imagen_recibida = parametros.getString("sendNombreImagen");
            int find_point = nombre_Imagen_recibida.indexOf(".");
            String nom_img_sinjpg = nombre_Imagen_recibida.substring(0,find_point);
            String completo = inicio+nom_img_sinjpg;
            recuperarnombreImg.setText(completo);

            String intTostringDireccion;

            int[] flags_img = new int[]{
                    R.drawable.dog100,
                    R.drawable.dog4,
            };

            for (int i=0;i< flags_img.length; i++){
                Log.e("flags_img: ", String.valueOf(flags_img[i]));
                intTostringDireccion =String.valueOf(flags_img[i]);

                Log.e("intTostringDireccion: ", intTostringDireccion);
                Log.e("completo: ",completo);
                if (completo.equals(intTostringDireccion)){
                    iv.setImageResource(Integer.parseInt(completo));
                }
            }

            /*Drawable image  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.dog100);
            String uri = "@drawable/"+nombre_Imagen_recibida;
            Log.e("nombre img", uri);
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());*/
            //Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);

            //iv.setImageResource(R.drawable.dog4);
        }

    }
}
