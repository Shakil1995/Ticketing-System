package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Show_ticket extends AppCompatActivity {
    private List<TicketModel> lstAnime;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.show_ticket );

        lstAnime = new ArrayList<> ();
        recyclerView = (RecyclerView)findViewById(R.id.allTicket);

        new loadTicket().execute (  );

    }

    public JSONObject perseJSONArray(String string){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(string);
        } catch (JSONException e) {
            jsonObject = null;
        }

        return jsonObject;
    }


    public class loadTicket extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            try {
                System.out.println("API triggering for login simpleretail pos");

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder ()
                        .header ( "User-Agent", "OkHttp Headers.java" )
                        .addHeader ( "Accept", "application/json; q=0.5" )
                        .url ( "http://192.168.0.106/ticketing_show.php" )
                        .build ();

                String result=null;


                Response response = client.newCall(request).execute();
                result = response.body().string();

                return result;
            }catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
        protected void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(Show_ticket.this,"Processing please wait...",Toast.LENGTH_SHORT).show();
        }

        protected void onPostExecute(String s){
            super.onPostExecute(s);
            System.out.println (s);
            if(s.equals ( "fail" )){

                Toast.makeText(Show_ticket.this,"Failed, PLease try again.",Toast.LENGTH_SHORT).show();
            }
            else{

                try {

                    String data =null;

                    JSONArray dataObject=new JSONArray(s);
                    System.out.println("Array Length = "+dataObject.length());
                    JSONObject row = null;
                    for(int i=0; i<=dataObject.length(); i++){
                        try {
                            row=dataObject.getJSONObject(i);
                            TicketModel dataRow = new TicketModel ();

                            dataRow.setName(row.getString("Name"));
                            dataRow.setEmail (row.getString("Gmail"));
                            dataRow.setSubject (row.getString("Subject"));
                            dataRow.setDescription (row.getString("Description"));


                            lstAnime.add(dataRow);

                        }catch (Exception e){
                            System.out.println("Failed to prase jsonARRAY"+dataObject.length());
                        }
                    }
                    setUpRecyleView(lstAnime);
                }catch (Exception e){
                    System.out.println("Json SPLITER Failed"+s);
                }


                Toast.makeText(Show_ticket.this,"Data loaded successfully",Toast.LENGTH_SHORT).show();
            }
            /*JSONObject jsonObject = perseJSONArray(s);
            String token_type = null;
            String access_token = null;
            String refresh_token = null;
            try {
                token_type = jsonObject.getString("success");
                access_token = jsonObject.getString("token");

            } catch (JSONException e) {
                Toast.makeText(Submit_ticket.this,"Processing please wait...",Toast.LENGTH_SHORT).show();
            }*/
        }
    }

    private void setUpRecyleView(List<TicketModel> lstAnime) {
        ticketShowAdapter customerAdapter = new ticketShowAdapter(this,lstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager (this));
        recyclerView.setAdapter(customerAdapter);
    }

}
