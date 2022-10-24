package br.com.ufc.navegandoentretelas.model;

import java.io.Serializable;

public class Carro implements Serializable{
    private String id;
    private String modelo;
    private String cor;

    public Carro(String id, String modelo, String cor) {
        this.id = id;
        this.modelo = modelo;
        this.cor = cor;
    }

    public Carro() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "Carro " + "modelo = " + modelo + " ,cor = " + cor;
    }
}
