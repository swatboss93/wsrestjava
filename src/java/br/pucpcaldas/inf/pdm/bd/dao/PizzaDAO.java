/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas.inf.pdm.bd.dao;

import br.pucpcaldas.inf.pdm.bd.Grupo;
import br.pucpcaldas.inf.pdm.bd.Pizza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author swatboss93
 */
public class PizzaDAO {

    private Conexao con;

    public PizzaDAO(Conexao con) {
        this.con = con;
    }
    
    public int CriaBanco() throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("CREATE DATABASE IF NOT EXISTS pdm;");

        return ps.executeUpdate();
    }
    
    public int CriaTabelaPizza() throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("CREATE TABLE IF NOT EXISTS Pizza(id INTEGER NOT NULL AUTO_INCREMENT, nome VARCHAR(30) NOT NULL, preco decimal(6,2) NOT NULL, grupo INTEGER NOT NULL, CONSTRAINT pk_pizza PRIMARY KEY (id));");

        return ps.executeUpdate();
    }

    public Pizza getPizza(int id) throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM Pizza WHERE id=?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        Pizza pizza = new Pizza();

        while (rs.next()) {
            pizza = loadObjects(rs);
        }
        return pizza;
    }

    public List<Pizza> getPizzas() throws SQLException {
        List<Pizza> lista = new ArrayList<>();

        PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM Pizza");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Pizza pizza = new Pizza();
            pizza = loadObjects(rs);

            lista.add(pizza);
        }

        return lista;
    }

    public int insertPizza(Pizza pizza) throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("INSERT INTO Pizza(nome, preco, grupo) VALUES (?,?,?)");

        ps.setString(1, pizza.getNome());
        ps.setBigDecimal(2, pizza.getPreco());
        ps.setInt(3, pizza.getGrupo().getValorGrupo());

        return ps.executeUpdate();
    }

    public int updatePizza(Pizza pizza) throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("UPDATE Pizza SET nome=?, preco=?, grupo=? WHERE id=?");

        System.out.println("id: " + pizza.getId() + "\ngrupo: " + pizza.getGrupo().getValorGrupo());
        ps.setString(1, pizza.getNome());
        ps.setBigDecimal(2, pizza.getPreco());
        ps.setInt(3, pizza.getGrupo().getValorGrupo());
        ps.setInt(4, pizza.getId());

        return ps.executeUpdate();
    }

    public int deletePizza(int id) throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("DELETE FROM Pizza WHERE id=?");

        ps.setInt(1, id);

        return ps.executeUpdate();
    }

    private Pizza loadObjects(ResultSet rs) throws SQLException {
        Pizza pizza = new Pizza();

        pizza.setId(rs.getInt("id"));
        pizza.setNome(rs.getString("nome"));
        pizza.setPreco(rs.getBigDecimal("preco"));
        if (rs.getInt("grupo") == Grupo.A.getValorGrupo()) {
            pizza.setGrupo(Grupo.A);
        } else if (rs.getInt("grupo") == Grupo.B.getValorGrupo()) {
            pizza.setGrupo(Grupo.B);
        }
        return pizza;
    }
}
