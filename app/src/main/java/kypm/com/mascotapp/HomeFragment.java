package kypm.com.mascotapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import kypm.com.mascotapp.adaptador.PublicacionAdaptador;
import kypm.com.mascotapp.modelo.Mascota;
import kypm.com.mascotapp.modelo.Publicacion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    String url = "http://gohan1992.pythonanywhere.com/";

    private RecyclerView recyclerView;
    private PublicacionAdaptador publicacionAdaptador;
    private List<Publicacion> listFotos = new ArrayList<>();

    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_publicaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaBaseDatos();

        publicacionAdaptador = new PublicacionAdaptador(getContext(),llenarListaBaseDatos());
        recyclerView.setAdapter(publicacionAdaptador);

        floatingActionButton = view.findViewById(R.id.floatingActionButtonPublicar);

        coneccion();
        events();

        return view;
    }

    private void coneccion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios servicioListarFotos = retrofit.create(Servicios.class);
        Call<List<Publicacion>> call = servicioListarFotos.listarFotos();

        call.enqueue(new Callback<List<Publicacion>>() {
            @Override
            public void onResponse(Call<List<Publicacion>> call, Response<List<Publicacion>> response) {
                Log.e("Codigo: ", response.code() + "");

                switch (response.code()) {
                    case 200:

                        List<Publicacion> publicaciones = response.body();
                        //publicacionAdaptador = new PublicacionAdaptador(getContext(),publicaciones);
                        //recyclerView.setAdapter(publicacionAdaptador);

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Publicacion>> call, Throwable t) {
                Log.e("Error App: ", t.getMessage());
            }
        });
    }

    private void events(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcrearpublicacion = new Intent(getContext(), CrearPublicacionActivity.class);
                //startActivity(intentcrearpublicacion);
                startActivityForResult(intentcrearpublicacion, 1);


            }
        });
    }

    private void showPopupPublicar(){

    }

    private  ArrayList<Publicacion> llenarListaBaseDatos(){
        ArrayList<Publicacion> mascotas = new ArrayList<>();
        mascotas.add(new Publicacion(R.drawable.dog1));
        mascotas.add(new Publicacion(R.drawable.dog10));
        mascotas.add(new Publicacion(R.drawable.dog100));
        mascotas.add(new Publicacion(R.drawable.dog13));
        mascotas.add(new Publicacion(R.drawable.dog600));
        mascotas.add(new Publicacion(R.drawable.dog12));
        mascotas.add(new Publicacion(R.drawable.dog110));
        mascotas.add(new Publicacion(R.drawable.dog18));
        mascotas.add(new Publicacion(R.drawable.dog19));
        mascotas.add(new Publicacion(R.drawable.dog187));
        mascotas.add(new Publicacion(R.drawable.dog102));

        return mascotas;
    }

}
