package com.example.ejercicio3cm;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ejercicio3cm.modelo.producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONArray> {

    ListView lv;
    ProgressBar pb;
    int alto, ancho;
    String url;
    RequestQueue queue;
    JsonArrayRequest request;
    ArrayList<producto> productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display =getWindowManager().getDefaultDisplay();
        Point size =new Point();
        display.getSize(size);

        ancho=size.x;
        alto=size.y;

        lv=findViewById(R.id.lv);
        pb=findViewById(R.id.pb);
        productos=new ArrayList<producto>();
        //Generar conexión:

        queue= Volley.newRequestQueue(this);
        url=getResources().getString( R.string.url_base);
        request=new JsonArrayRequest(Request.Method.GET,url,null,this,this);
        queue.add(request);
    }

    @Override
    //Errores por si algo sucede mal
    public void onErrorResponse(VolleyError error) {
        pb.setVisibility(View.GONE);
        finish();

    }

    @Override
    //Lo que llegue como respuesta, JSONObject, String
    public void onResponse(JSONArray response) {
        pb.setVisibility(View.GONE);
        JSONObject jsonObject;
        try{
            for(int i=0;i<response.length();i++){

                jsonObject=response.getJSONObject(i);
                int id=jsonObject.getInt("id");
                double price=jsonObject.getDouble("price");
                double delivery=jsonObject.getDouble("delivery");
                String tumb=jsonObject.getString("thumbnail_url");
                String name=jsonObject.getString("name");
                String provider=jsonObject.getString("provider");
                producto product =new producto(id, tumb, name, provider, price,delivery);
                productos.add(product);
            }

            Adaptador adaptador = new Adaptador(this, productos, ancho, alto);
            lv.setAdapter(adaptador);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent= new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                }
            });
        }catch(JSONException e){
        }
    }
}
