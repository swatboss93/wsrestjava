/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swatboss93.bd;

/**
 *
 * @author swatboss93
 */
public enum Grupo {
    A(0), B(1);
    
    private final int grupo;
    Grupo(int valorPrioridade){
        this.grupo=valorPrioridade;
    }
    public int getValorGrupo(){
        return this.grupo;
    }
}
