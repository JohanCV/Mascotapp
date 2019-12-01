package kypm.com.mascotapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import io.github.wangeason.multiphotopicker.utils.PhotoPickerIntent;
import kypm.com.mascotapp.modelo.Mascota;
import kypm.com.mascotapp.modelo.Publicacion;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearPublicacionActivity extends AppCompatActivity {
    String url = "http://gohan1992.pythonanywhere.com/";
    private ImageView regresar;
    private TextView nombreCabecera;
    private CheckBox chk_recompensa;
    private EditText txt_recompensa;
    private EditText txt_Fecha_perdida;
    private static final String CERO = "0";
    private static final String BARRA = "-";
    //Calendario
    private  Calendar calendar;
    private int mes;
    private int dia;
    private int anio;
    private ImageButton ib_ObtenerFecha;

    private Button btn_publicar;
    private Spinner spinner;
    private ImageView mapa;
    private ImageButton agregarfotos;
    Uri selectedImage;

    private int posicion;
    private static final int PICK_IMAGE = 100;

    List<Mascota> mascotas;
    private Bundle datosmapa;
    private Double latitud,longitud;

    TextView nombreImagen;

    public CrearPublicacionActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_publicacion);
        findElemente();
        eventos();

    }

    private void findElemente() {
        regresar =(ImageView) findViewById(R.id.regresarPerfilEditar);
        nombreCabecera = (TextView) findViewById(R.id.perfilUsuarioNombreEditar);

        nombreImagen = (TextView) findViewById(R.id.textView4);
        //img para agregar fotos de mi galeria
        agregarfotos=(ImageButton) findViewById(R.id.imb_agregar_imagen);

        btn_publicar = (Button) findViewById(R.id.btn_publicar);

    }

    private void eventos() {

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regresarHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(regresarHome);
            }
        });

        nombreCabecera.setText("Buscar");
        habilitar_recompensa();
        Listar_mascota_usuario();


        /*ib_ObtenerFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha();
            }
        });*/

        btn_publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Crear_publicacion();

            }
        });

        /*
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                posicion = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // No seleccionaron nada
            }
        });
        /*mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentmapa = new Intent(getApplicationContext(), MapaActivity.class);
                //startActivityForResult(intentmapa,2);
            }
        });
        */
        agregarfotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
                        CrearPublicacionActivity.this);
                myAlertDialog.setTitle("Agregar foto");
                //myAlertDialog.setMessage("¿Cómo quieres configurar tu imagen?");

                myAlertDialog.setPositiveButton("Galería",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                                // Sets the type as image/*. This ensures only components of type image are selected
                                intent.setType("image/*");
                                //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
                                String[] mimeTypes = {"image/jpeg", "image/png"};
                                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                                // Launching the Intent
                                startActivityForResult(intent,1);
                            }
                        });

                /*myAlertDialog.setNegativeButton("Camera",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                                    startActivityForResult(takePictureIntent, 2);
                                }
                            }
                        });*/
                myAlertDialog.show();
            }
        });
    }


    private void Crear_publicacion() {

        File file = new File(getRealPathFromURI(selectedImage));
        String nombreImg = file.getName().toString();

        // Obtiene el Nombre y el Directorio Absoluto y los Muestra
        //nombreImagen.setText("Nombre: " + file.getName()+ "Dir. Absoluto: " + file.getAbsolutePath());
        nombreImagen.setText("Nombre: " + nombreImg);

        Intent BuscarHome = new Intent(getApplicationContext(), ResultadoBusquedaActivity.class);
        BuscarHome.putExtra("sendNombreImagen",nombreImg);
        //setResult(Activity.RESULT_OK,BuscarHome);
        //finish();
        startActivity(BuscarHome);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servicios serviciopublicacion = retrofit.create(Servicios.class);

        Call<Publicacion> registrar_publicacion = serviciopublicacion.subirFoto(
                Double.parseDouble(txt_recompensa.getText().toString()),
                txt_Fecha_perdida.getText().toString(),
                mascotas.get(posicion).getFoto(),
                latitud,
                longitud);

        registrar_publicacion.enqueue(new Callback<Publicacion>() {
            @Override
            public void onResponse(Call<Publicacion> call, Response<Publicacion> response) {
                switch (response.code()) {
                    case 201:
                        Publicacion p = response.body();
                        //Log.e("publicar", "" + p.getRecompensa());
                        SubirFoto();
                        break;
                    default:
                        Log.e("errorp", "" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Publicacion> call, Throwable t) {
                Log.e("Error publicacion", t.getMessage());
            }
        });*/
    }

    private void obtenerFecha() {
        calendar = Calendar.getInstance();
        anio = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int dia, int mes, int anio) {
               //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                /*final int mesActual = mes + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dia < 10) ? CERO + String.valueOf(dia) : String.valueOf(dia);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado

                txt_Fecha_perdida.setText(diaFormateado + BARRA + mesFormateado + BARRA + anio);*/
                txt_Fecha_perdida.setText(dia+"/"+(mes+1)+"/"+anio);

            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, anio,mes,dia);
        //Muestro el widget
        recogerFecha.getDatePicker().setMaxDate(System.currentTimeMillis());
        recogerFecha.show();
    }

    private void Listar_mascota_usuario() {
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServicioPublicacion serviciopublicacion = retrofit.create(ServicioPublicacion.class);
        Call<List<Mascota>> call = serviciopublicacion.listar_mascotas_usuario(4);
        call.enqueue(new Callback<List<Mascota>>() {
            @Override
            public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {
                Log.e("Codigo ", response.code() + "");
                switch (response.code()) {
                    case 200:
                        Log.e("msj", response.body().toString());
                        mascotas = response.body();
                        List<String> perros = new LinkedList<>();
                        for (Mascota p : mascotas) {
                            Log.e("app", p.getNombre() + "");
                            perros.add(p.getNombre());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner_escoger_mascota, perros);
                        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Mascota>> call, Throwable t) {
                Log.e("Error Appatas", t.getMessage());
            }
        });*/
    }

    public void MultiPhoto(){
        PhotoPickerIntent intent = new PhotoPickerIntent(CrearPublicacionActivity.this);
        intent.setPhotoCount(3);
        intent.setShowCamera(true);
        intent.setShowGif(true);
        intent.setMultiChoose(true);
        //https://github.com/wangeason/MultiPhotoPicker
        //https://github.com/nileshpambhar/MultiPhotoPicker
        //startActivityForResult(intent, REQUEST_CODE);
    }

    public void SubirFoto(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servicios Serviciopublicacion = retrofit.create(Servicios.class);
        File file = new File(getRealPathFromURI(selectedImage));

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("foto",file.getName(),reqFile);
        RequestBody mascota = RequestBody.create(MediaType.parse("text/plain"),"1");

        //
        Call<ResponseBody> call = Serviciopublicacion.subirFoto( body,  mascota);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("Codigo ", response.code() + "");
                Log.e("Codigo ", response.body().toString() + "");
                switch (response.code()) {
                    case 201:
                        Log.e("Foto Firulais", "Siii lo logre");
                        break;
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error Appatas", t.getMessage());
            }
        });
    }

    public void habilitar_recompensa() {
        /*
        chk_recompensa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    txt_recompensa.setEnabled(true);
                    txt_recompensa.requestFocus();
                } else {
                    txt_recompensa.setEnabled(false);
                    txt_recompensa.setText("");
                }
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            selectedImage = data.getData();
            agregarfotos.setImageURI(selectedImage);
        }
        /*if (requestCode == 2) {
            if(resultCode == MapaActivity.RESULT_OK){
                latitud=data.getDoubleExtra("latitud",0);
                longitud=data.getDoubleExtra("longitud", 0);
            }
            if (resultCode == MapaActivity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }*/
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

}
