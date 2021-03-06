package com.example.readcontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Contact> contactList;
    //private List<Contact> phoneNumber;


    public Adapter(Context context, List<Contact> name){
        this.layoutInflater = LayoutInflater.from(context);
        this.contactList = name;
        //this.phoneNumber = phoneNumber;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.customview, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //bind the textView with data received
        Contact nameRes = contactList.get(i);

      //  viewHolder.name.setText((CharSequence) nameRes);

       // Contact temperatureRes = phoneNumber.get(i);
       // viewHolder.phoneNumber.setText((CharSequence) temperatureRes);

        //similarly we set the images for each card depending on the temperature (switch suitable here)
        //TODO

        //viewHolder.name.setText((CharSequence) contactList.get(i));
        /*viewHolder.parentLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(view.getContext(), name.get(i), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), detailActivity.class);
                //intent.putExtra("ville", ville.get(i));
                view.getContext().startActivity(intent);
                //startActivity(intent);
            }
        });*/
            viewHolder.name.setText(nameRes.getNiame());
            viewHolder.phoneNumber.setText(nameRes.getNumber());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, phoneNumber;
        ConstraintLayout parentLayout;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
