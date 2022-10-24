package br.com.ufc.navegandoentretelas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ufc.navegandoentretelas.R;
import br.com.ufc.navegandoentretelas.carroAdapter;
import br.com.ufc.navegandoentretelas.dao.CarroDAO;
import br.com.ufc.navegandoentretelas.model.Carro;

public class FormularioCar extends AppCompatActivity {
    private EditText modelo,cor;
    private final CarroDAO dao = new CarroDAO();
    private carroAdapter adapter = new carroAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_car);

        iniciandoComponentes();
        configuraBotaoSalvar();
        configuraBtnCacelar();
    }

    private void iniciandoComponentes() {
        modelo = findViewById(R.id.act2Modelo);
        cor = findViewById(R.id.act2Cor);
    }
    private void configuraBtnCacelar(){
        Button btnCacelar = findViewById(R.id.act2Cancelar);
        btnCacelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormularioCar.this,ListCar.class);
                startActivity(intent);
            }
        });
    }
    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.act2Salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String modelo1 = modelo.getText().toString();
                String cor1 = cor.getText().toString();

                ValidandoCampos(modelo1,cor1);


            }
            private void ValidandoCampos(String modelo1, String cor1){
                if (modelo1.equals("") && cor1.equals("")){
                    Toast.makeText(FormularioCar.this,"Por favor, preencher todos os campos",Toast.LENGTH_SHORT).show();
                }else if (modelo1.equals("")||cor1.equals("")){
                    Toast.makeText(FormularioCar.this,"Por favor, preencher todos os campos",Toast.LENGTH_SHORT).show();
                }else{
                    Carro carroCriado = criaCarro();
                    salva(carroCriado);
                }

            }
        });
    }
    private void salva(Carro carro) {
        dao.salva(carro);
        adapter.notifyDataSetChanged();
        Intent intent = new Intent(FormularioCar.this, ListCar.class);
        startActivity(intent);
    }
    private Carro criaCarro() {
    String id ="";
    String modeloCar = modelo.getText().toString();
    String corCar = cor.getText().toString();
    return new Carro(id,modeloCar,corCar);

    }
}