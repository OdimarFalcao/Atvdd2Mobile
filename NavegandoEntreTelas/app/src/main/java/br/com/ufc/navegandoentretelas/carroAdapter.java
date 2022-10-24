package br.com.ufc.navegandoentretelas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.ufc.navegandoentretelas.model.Carro;

public class carroAdapter extends RecyclerView.Adapter<br.com.ufc.navegandoentretelas.carroViewHolder> {
    private Context context;
    private List<Carro> carros;
    private carroInteface carroInteface;
    private carroViewHolder carroViewHolder;
    public carroAdapter(Context context, List<Carro> carros, carroInteface carro) {
        this.context = context;
        this.carros = carros;
        this.carroInteface = carro;
    }

    public carroAdapter() {

    }

    @NonNull
    @Override
    public carroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carro_linha, parent, false);
        carroViewHolder carroViewHolder = new carroViewHolder(view);
        return carroViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull carroViewHolder holder, int position) {
        Carro car = carros.get(position);
        int posicao = position;
        holder.modelo.setText(car.getModelo());
        holder.cor.setText(car.getCor());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carroInteface.btnEdit(posicao,car);
            }
        });

        holder.delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                carroInteface.LgClick(car);
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return carros.size();
    }
}
