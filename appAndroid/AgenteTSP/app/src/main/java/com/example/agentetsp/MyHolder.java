package com.example.agentetsp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView Name,Adress, Phone;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.imageView =itemView.findViewById(R.id.imageContact);
        this.Name=itemView.findViewById(R.id.textNombre);
        this.Adress =itemView.findViewById(R.id.textDirrecion);
        this.Phone =itemView.findViewById(R.id.textTelefono);
    }
}
