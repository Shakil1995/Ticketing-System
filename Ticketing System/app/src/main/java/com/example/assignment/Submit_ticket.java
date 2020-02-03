package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Submit_ticket extends AppCompatActivity {
    private Spinner spinner;
    private TextView name,mail,subject,descrition,selesct_file;
    private EditText enter_name,enter_mail,enter_description, enter_subject;
    private Button send_data,cancel_data;
    String myenter_name,myenter_mail,myenter_description, myenter_subject;
    String url="http://192.168.0.106/ticketing.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.submit_ticket );
        spinner= (Spinner) findViewById ( R.id.spinarID );
        name= (TextView) findViewById(R.id.Enter_name_ID);
        mail= (TextView) findViewById(R.id.Enter_mail_ID);
        subject=(TextView) findViewById(R.id.Enter_Subject_ID);
        descrition= (TextView) findViewById(R.id.Enter_description_ID);
        selesct_file=(TextView) findViewById(R.id.textViewSelectfileID);

        enter_name=(EditText) findViewById(R.id.Enter_name_ID);
        enter_subject=(EditText) findViewById(R.id.Enter_name_ID);
        enter_mail=(EditText) findViewById(R.id.Enter_mail_ID);
        enter_description= (EditText) findViewById(R.id.Enter_description_ID);

        send_data=(Button) findViewById(R.id.Submit_dataID);
        cancel_data=(Button) findViewById(R.id.cancel_ID);

        //        Catagoory Start
        List<String>catagories=new ArrayList<> (  );
        catagories.add(0,"Catagory");
        catagories.add ("Mahbub Sir" );
        catagories.add ( "shakil" );
        catagories.add ( "problem" );
        catagories.add ( "amin" );
        catagories.add ( "Android" );
        ArrayAdapter<String>dataAdapter;
        dataAdapter =new ArrayAdapter<> (this,android.R.layout.simple_spinner_item  ,catagories);
        dataAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter ( dataAdapter );

        spinner.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adapterView.getItemAtPosition ( i ).equals ( "Choose Catagory" ))
                {

                }
                else {
                    String item=adapterView.getItemAtPosition ( i ).toString ();
                }

               // Toast.makeText ( adapterView.getContext (),"Selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        } );
  //        Catagoory Een

        send_data.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

            InsertData();

Toast.makeText ( Submit_ticket.this,"Data sending",Toast.LENGTH_SHORT).show();

            }
        } );

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

    public class insert extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            try {
                System.out.println("API triggering for login simpleretail pos");
                String name=params[0];
                String email=params[1];
                String subject=params[2];
                String description=params[3];
                OkHttpClient client = new OkHttpClient();

                RequestBody postData = new FormBody.Builder()
                        .add("Name",name)
                        .add("Gmail",email)
                        .add("Subject",subject)

                        .add("Description",description)
                        .build();
                Request request = new Request.Builder ()
                        .header ( "User-Agent", "OkHttp Headers.java" )
                        .addHeader ( "Accept", "application/json; q=0.5" )
                        .url ( "http://192.168.0.106/ticketing.php" )
                        .post ( postData )
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
            Toast.makeText(Submit_ticket.this,"Processing please wait...",Toast.LENGTH_SHORT).show();
        }

        protected void onPostExecute(String s){
            super.onPostExecute(s);
            System.out.println ( s );
            if(s.equals ( "seccess" )){
                name.setText ( null );
                mail.setText ( null );
                subject.setText ( null );
                descrition.setText ( null );
                Toast.makeText(Submit_ticket.this,"Saved Successfully.",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(Submit_ticket.this,"Failed, Please try again.",Toast.LENGTH_SHORT).show();
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

 public void InsertData() {

     myenter_name = name.getText ().toString ();
     myenter_mail = mail.getText ().toString ();
     myenter_subject = subject.getText ().toString ();
     myenter_description = descrition.getText ().toString ();
    System.out.println ( myenter_name );
     if(!myenter_name.isEmpty () && !myenter_mail.isEmpty () && !myenter_subject.isEmpty () && !myenter_description.isEmpty ()){
         new insert ().execute ( myenter_name, myenter_mail,myenter_subject,myenter_description );
     }else{
         Toast.makeText(Submit_ticket.this,"PLease fillup all required field",Toast.LENGTH_SHORT).show();
     }



 }
}
