package kypm.com.mascotapp.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        viewHolderPublicacion.nombreMascota.setText(listPublicacion.get(position).getNombre().toString());
        viewHolderPublicacion.fechaperdida.setText(listPublicacion.get(position).getFecha_perdida().toString());
        viewHolderPublicacion.raza.setText(listPublicacion.get(position).getRaza().toString());
        viewHolderPublicacion.telefono.setText(listPublicacion.get(position).getTelefono().toString());
        viewHolderPublicacion.imgMascota.setImageResource(listPublicacion.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return listPublicacion.size();
    }

    public class ViewHolderPublicacion extends RecyclerView.ViewHolder {

        TextView nombreMascota,  fechaperdida, raza, telefono;
        ImageView imgMascota;

        public ViewHolderPublicacion(@NonNull View itemView) {
            super(itemView);

            nombreMascota = itemView.findViewById(R.id.txtNombreMascota);
            fechaperdida = itemView.findViewById(R.id.txtFechaPerdida);
            raza = itemView.findViewById(R.id.txtInpRazaMascota);
            telefono = itemView.findViewById(R.id.txtInpTeleMascota);

            imgMascota = itemView.findViewById(R.id.imgMascota);
        }
    }
}
