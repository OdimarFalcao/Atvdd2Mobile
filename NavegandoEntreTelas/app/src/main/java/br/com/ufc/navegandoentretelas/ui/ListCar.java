package br.com.ufc.navegandoentretelas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ufc.navegandoentretelas.R;
import br.com.ufc.navegandoentretelas.carroAdapter;
import br.com.ufc.navegandoentretelas.carroInteface;
import br.com.ufc.navegandoentretelas.dao.CarroDAO;
import br.com.ufc.navegandoentretelas.model.Carro;

public class ListCar extends AppCompatActivity implements carroInteface {
    private final CarroDAO dao = new CarroDAO();
    static private EditText id;
    private RecyclerView recyclerView;
    private carroAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_car);
        adapter = new carroAdapter(ListCar.this, dao.list(),this);
        adapter.notifyDataSetChanged();
        configRecycler();
        ConfigurandoBtnAdd();
    }



    private void ConfigurandoBtnAdd() {
        Button btnAdd = findViewById(R.id.Adicionar);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormularioCar();
            }
        });
    }

    private void configRecycler() {
            recyclerView = findViewById(R.id.recycler);
            adapter = new carroAdapter(ListCar.this, dao.list(),this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListCar.this,RecyclerView.VERTICAL,false);
            adapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

    }
    private void abrirFormularioCar() {
        Intent intent = new Intent(ListCar.this, FormularioCar.class);
        startActivity(intent);
    }

    @Override
    public void apClick(Carro carro) {

    }

    @Override
    public void LgClick(Carro carro) {
        dao.remover(carro);
        adapter.notifyDataSetChanged();
        Toast.makeText(ListCar.this,"Você apagou o carro do: " + carro.getModelo(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void btnEdit(int i, Carro carro) {
        Intent intent = new Intent(ListCar.this, EdtCar.class);
        intent.putExtra("id",i);
        intent.putExtra("car",carro);
        startActivity(intent);
    }
}
