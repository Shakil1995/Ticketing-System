package com.example.assignment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ticket_options extends AppCompatActivity {

    private Button My_Ticket,Submit_Ticket;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.ticket_options );
        My_Ticket= findViewById ( R.id.My_TicketID );
        Submit_Ticket= findViewById ( R.id.Submit_TicketID );
        drawerLayout=findViewById ( R.id.shakil_dr );



        Submit_Ticket.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent ( Ticket_options.this,Submit_ticket.class );
                startActivity ( intent );

            }
        } );


        My_Ticket.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent ( Ticket_options.this,Show_ticket.class );
                startActivity ( intent );

            }
        } );

        toggle =new ActionBarDrawerToggle ( this,drawerLayout,R.string.nav_open,R.string.nav_close );
        drawerLayout.addDrawerListener ( toggle );
        toggle.syncState ();
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );

    }
}
