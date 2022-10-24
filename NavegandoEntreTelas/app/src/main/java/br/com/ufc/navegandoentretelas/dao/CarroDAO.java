package br.com.ufc.navegandoentretelas.dao;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import br.com.ufc.navegandoentretelas.model.Carro;

public class CarroDAO {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final static List<Carro> carros = new ArrayList<>();

    public void salva(Carro carro) {
        db.collection("carros")
                .add(carro)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                        carro.setId(documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        carros.add(carro);
    }
    public void remover(Carro carro) {
        db.collection("carros").document(carro.getId()).delete();
    }

    public void edit(Carro carro){
        db.collection("carros").document(carro.getId()).set(carro);
    }

    public List<Carro> list() {
        db.collection("carros")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            carros.clear();
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                String id = (String) doc.getId();
                                String modelo = (String) doc.getData().get("modelo");
                                String cor = (String) doc.getData().get("cor");
                                carros.add(new Carro(id,modelo, cor));
                            }
                        } else {

                        }
                    }
                });
        return carros;
    }


}
