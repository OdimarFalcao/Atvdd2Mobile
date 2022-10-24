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

public class EdtCar extends AppCompatActivity {
    private EditText modelo,cor;
    private Button btnAt,btnCacelar;
    private CarroDAO dao = new CarroDAO();
    private carroAdapter adapter = new carroAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edt_car);
        modelo = findViewById(R.id.modelo);
        cor = findViewById(R.id.cor);
        int posicao = getIntent().getIntExtra("id",0);
        Carro car = (Carro) getIntent().getSerializableExtra("car");
        String modelo1 = car.getModelo();
        String cor1 = car.getCor();
        modelo.setText(modelo1);
        cor.setText(cor1);


        configurandoBtnAtl(posicao, car, modelo1, cor1);

        btnCacelar = findViewById(R.id.cancelar);
        btnCacelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EdtCar.this,ListCar.class);
                startActivity(intent);
            }
        });

    }

    private void configurandoBtnAtl(int posicao, Carro car, String modelo1, String cor1) {
        btnAt = findViewById(R.id.atualizar);
        btnAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(EdtCar.this,ListCar.class);
                String modelof = modelo.getText().toString();
                String corf = cor.getText().toString();
                ValidandoCampos(intent,modelof,corf);
            }
            private void ValidandoCampos(Intent intent, String modelof, String corf){
                if (modelo1.equals("") && cor1.equals("")){
                    Toast.makeText(EdtCar.this,"Por favor, preencher todos os campos",Toast.LENGTH_SHORT).show();
                }else if (modelo1.equals("")|| cor1.equals("")){
                    Toast.makeText(EdtCar.this,"Por favor, preencher todos os campos",Toast.LENGTH_SHORT).show();
                }else{
                    car.setModelo(modelof);
                    car.setCor(corf);
                    dao.edit(car);
                    Toast.makeText(EdtCar.this,"Você editou o apartamento: "+ modelof,Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();
                    startActivity(intent);
                }

            }
        });
    }
}