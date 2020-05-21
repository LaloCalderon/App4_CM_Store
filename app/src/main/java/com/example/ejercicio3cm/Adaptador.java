package com.example.ejercicio3cm;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ejercicio3cm.modelo.producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    Context contexto;
    ArrayList<producto> array;
    int alto, ancho;
    private static LayoutInflater inflater=null;

    public Adaptador(Context contexto, ArrayList<producto> array, int ancho, int alto) {
        this.contexto = contexto;
        this.array = array;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.ancho=ancho;
        this.alto=alto;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return array.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=new ImageView(contexto);

        final View vista=inflater.inflate(R.layout.elemento_lista, null);
        ImageView iv=vista.findViewById(R.id.ivprod);
        Picasso.with(contexto)
                .load(array.get(position).getThumbnail_url())
                .into(iv);
        //iv.setImageResource(imageView);
        TextView tvprice=vista.findViewById(R.id.tvprice);
        TextView tvdel=vista.findViewById(R.id.tvdel);
        TextView tvname=vista.findViewById(R.id.tvname);
        TextView tvprov=vista.findViewById(R.id.tvprov);

        tvname.setText(array.get(position).getName());
        tvprice.setText("$"+array.get(position).getPrice());
        tvdel.setText("$"+array.get(position).getDelivery());
        tvprov.setText(array.get(position).getProvider());

        return vista;
    }
}