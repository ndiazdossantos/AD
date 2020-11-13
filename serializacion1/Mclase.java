/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion1;

import java.io.Serializable;

/**
 *
 * @author oracle
 */
public class Mclase implements Serializable {
    
    private String nome;
  //  private int numero1;
    private transient int numero1;
    private double numero2;

    public Mclase(String nome, int numero1, double numero2) {
        this.nome = nome;
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    Mclase() {
    }

    @Override
    public String toString() {
        return "Mclase{" + "nome=" + nome + ", numero1=" + numero1 + ", numero2=" + numero2 + '}';
    }
    
    
    
}
