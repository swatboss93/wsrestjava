/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas.inf.pdm.bd.dao;

import java.sql.Connection;

/**
 *
 * @author swatboss93
 */
public interface Conexao {
    public Connection getConexao();
}
