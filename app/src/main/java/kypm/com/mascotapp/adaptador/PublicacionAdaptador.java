package kypm.com.mascotapp.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import kypm.com.mascotapp.R;
import kypm.com.mascotapp.modelo.Publicacion;

public class PublicacionAdaptador extends RecyclerView.Adapter<PublicacionAdaptador.ViewHolderPublicacion> {

    private Context context;
    private List<Publicacion> listPublicacion;

    public PublicacionAdaptador(Context context, List<Publicacion> listPublicacion){
        this.context = context;
        this.listPublicacion = listPublicacion;
    }

    @NonNull
    @Override
    public ViewHolderPublicacion onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_publicacion_recycler,viewGroup,false);
        return new ViewHolderPublicacion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPublicacion viewHolderPublicacion, int position) {

        Glide.with(context).load(listPublicacion.get(position).getFoto()).into(viewHolderPublicacion.imgMascotas);

        viewHolderPublicacion.imgMascotas.setImageResource(listPublicacion.get(position).getImagenes());

    }

    @Override
    public int getItemCount() {
        return listPublicacion.size();
    }

    public class ViewHolderPublicacion extends RecyclerView.ViewHolder {

        //TextView nombreMascota,  fechaperdida, raza, telefono;
        ImageView imgMascotas;

        public ViewHolderPublicacion(@NonNull View itemView) {
            super(itemView);

            imgMascotas = itemView.findViewById(R.id.imgMascota);
        }
    }
}
