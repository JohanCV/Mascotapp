package kypm.com.mascotapp.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import kypm.com.mascotapp.R;
import kypm.com.mascotapp.modelo.Mascota;

public class ResultadosAdaptador  extends BaseAdapter {

    private Context context;
    private ArrayList<Mascota> listItem;

    public ResultadosAdaptador(Context context, ArrayList<Mascota> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Mascota itemDog =  (Mascota) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.item_mascota,null);

        ImageView ivDog =  view.findViewById(R.id.imgMascotaResult);

        ivDog.setImageResource(itemDog.getFoto());

        return view;
    }
}
