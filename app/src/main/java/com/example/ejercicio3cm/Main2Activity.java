package com.example.ejercicio3cm;
import com.android.volley.Response;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
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
    String url;
    RequestQueue queue;
    JsonObjectRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ivprod=findViewById(R.id.ivprod);
        pb=findViewById(R.id.pb);
        tvdata=findViewById(R.id.tvdata);
        tvname=findViewById(R.id.tvname);

        Bundle bundle=new Bundle();
        bundle=getIntent().getExtras();
        //Recupera valor de ide para adjuntar al link
        int ide=bundle.getInt("id");

        queue= Volley.newRequestQueue(this);
        url=getResources().getString(R.string.url_base)+ide;

        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pb.setVisibility(View.GONE);
                        JSONObject jsonObject=new JSONObject();
                        try{
                            tvname.setText(response.getString("name"));
                            tvdata.setText(response.getString("desc"));
                            String data=response.getString("imag_url");

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
}