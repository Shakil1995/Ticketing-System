package com.example.assignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ticketShowAdapter extends RecyclerView.Adapter<ticketShowAdapter.ticketShowViewHolder> {

    private static final String TAG = "ticketShowAdapter";


    private Context mContext;
    private List<TicketModel> mData;

    public ticketShowAdapter(Context mContext, List<TicketModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public ticketShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.show_ticket_adapter,parent,false);

        return new ticketShowViewHolder(view);

       /* View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_adapter, parent, false);
        CustomerViewHolder holder = new CustomerViewHolder(view);
        return holder;*/
    }

    @Override
    public void onBindViewHolder(@NonNull ticketShowViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.tittle.setText(mData.get(position).getName());
        holder.email.setText(mData.get(position).getEmail ());
       // holder.email.setText ( mData.get ( position ).getSubject () );

        /*holder.coustomar_name_ID.setText(name.get(position));
        holder.phone_number_ID.setText(phone.get(position));
        holder.dateID.setText(created_at.get(position));*/
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ticketShowViewHolder extends RecyclerView.ViewHolder{
        TextView tittle,email;
        RelativeLayout ticketAdapter;
        LinearLayout ticketLin;

        public ticketShowViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.tittle);
            email = itemView.findViewById(R.id.email);
            ticketAdapter = itemView.findViewById(R.id.ticketAdapter);
            ticketLin = itemView.findViewById(R.id.ticketLin);
        }
    }

}
