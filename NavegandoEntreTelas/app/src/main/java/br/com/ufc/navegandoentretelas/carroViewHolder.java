package br.com.ufc.navegandoentretelas;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class carroViewHolder  extends RecyclerView.ViewHolder{
    TextView modelo,cor;
    Button edit,delete;
    public carroViewHolder(@NonNull View itemView) {
        super(itemView);
        modelo = itemView.findViewById(R.id.modelo);
        cor = itemView.findViewById(R.id.cor);
        edit = itemView.findViewById(R.id.btn_edit);
        delete = itemView.findViewById(R.id.btn_apagar);
    }
}
