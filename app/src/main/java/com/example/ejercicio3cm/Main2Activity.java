package com.example.ejercicio3cm;
import com.android.volley.Response;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONArray> {
    Context contexto;
    ImageView ivprod;
    TextView tvdata, tvname;
    ProgressBar pb;
    String url;
    RequestQueue queue;
    JsonArrayRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ivprod=findViewById(R.id.ivprod);
        pb=findViewById(R.id.pb);
        tvdata=findViewById(R.id.tvdata);
        tvname=findViewById(R.id.tvname);

        queue= Volley.newRequestQueue(this);
        url="https://www.serverbpw.com/cm/2020-2/product_detail.php?id=2245";
        request=new JsonArrayRequest(Request.Method.GET,url,null, this,this);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pb.setVisibility(View.GONE);
        finish();
    }
    @Override
    public void onResponse(JSONArray response) {
        pb.setVisibility(View.GONE);
        JSONObject jsonObject;
        try{
//            for(int i=0;i<response.length();i++) {
            for(int i=0; i<response.length();i++) {

                jsonObject = response.getJSONObject(i);
                String name = jsonObject.getString("name");
                String image_url = jsonObject.getString("imag_url");
                String desc = jsonObject.getString("desc");
                tvname.setText(name);
                tvdata.setText(desc);
                Picasso.with(contexto)
                        .load(image_url)
                        .into(ivprod);
            }
        }catch (JSONException e){

        }
            //ivprod.setImageResource();
            //}
    }
}