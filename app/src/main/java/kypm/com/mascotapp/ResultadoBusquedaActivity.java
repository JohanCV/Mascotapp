package kypm.com.mascotapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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
    }

    private void eventos() {

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regresarHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(regresarHome);
            }
        });

        nombreCabecera.setText("Resultados");

        if(parametros != null) {
            String datos = parametros.getString("sendNombreImagen");
            recuperarnombreImg.setText(datos);
        }

    }
}
