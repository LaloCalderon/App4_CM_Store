package com.example.ejercicio3cm;
import com.android.volley.Response;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {
    Context contexto;
    ImageView ivprod;
    TextView tvdata, tvname;
    ProgressBar pb;
    String url, datos, descripcion, nombre;
    RequestQueue queue;
    JsonObjectRequest request;
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ivprod=findViewById(R.id.ivprod);
        pb=findViewById(R.id.pb);
        tvdata=findViewById(R.id.tvdata);
        tvname=findViewById(R.id.tvname);
        sp=getPreferences(Context.MODE_PRIVATE);
        editor=sp.edit();
        tvdata.setMovementMethod(new ScrollingMovementMethod());
        Bundle bundle=new Bundle();
        bundle=getIntent().getExtras();
        //Recupera valor de ide para adjuntar al link
        long ide=bundle.getLong("id");
        //Toast.makeText(Main2Activity.this,"ide: "+ide ,Toast.LENGTH_SHORT).show();
        queue= Volley.newRequestQueue(this);
        url=getResources().getString(R.string.link_prod)+ide;
        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pb.setVisibility(View.GONE);
                        JSONObject jsonObject=new JSONObject();
                        try{
                            tvname.setText(response.getString("name"));
                            tvdata.setText(response.getString("desc"));
                            nombre=response.getString("name");
                            descripcion=response.getString("desc");
                            String data=response.getString("imag_url");
                            datos=data;
                            guardaobjeto(datos,descripcion,nombre);
                            Picasso.with(contexto)
                                    .load(data)
                                    .into(ivprod);
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(Main2Activity.this,getResources().getString(R.string.bad) ,Toast.LENGTH_SHORT).show();
            }
        });
        MySinglet.getInstance(Main2Activity.this).addToRequest(jsonObjectRequest);
    }
    public void guardaobjeto(String datos, String descripcion, String nombre){
        editor.putString("imagen", datos);
        editor.putString("descripcion",descripcion);
        editor.putString("nombre",nombre);
        editor.commit();
    }
}