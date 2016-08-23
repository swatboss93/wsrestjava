/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas.inf.pdm.bd.dao;

import br.pucpcaldas.inf.pdm.bd.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author swatboss93
 */
public class UserDAO {
    private Conexao con;

    public UserDAO(Conexao con) {
        this.con = con;
    }
    
    public int CriaBanco() throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("CREATE DATABASE IF NOT EXISTS peixe;");

        return ps.executeUpdate();
    }
    
    public int CriaTabelaUser() throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("CREATE TABLE IF NOT EXISTS User(id INTEGER NOT NULL AUTO_INCREMENT, name VARCHAR(30) NOT NULL, email VARCHAR(30) NOT NULL, password VARCHAR(30) NOT NULL, CONSTRAINT pk_user PRIMARY KEY (id));");

        return ps.executeUpdate();
    }

    public User getUser(int id) throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM User WHERE id=?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        User user = new User();

        while (rs.next()) {
            user = loadObjects(rs);
        }
        return user;
    }

    public List<User> getUser() throws SQLException {
        List<User> lista = new ArrayList<>();

        PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM User");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            User user = new User();
            user = loadObjects(rs);

            lista.add(user);
        }

        return lista;
    }

    public int insertUser(User user) throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("INSERT INTO User(name, email, password) VALUES (?,?,?)");

        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());

        return ps.executeUpdate();
    }

    public int updateUser(User user) throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("UPDATE User SET name=?, email=?, password=? WHERE id=?");

        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setInt(4, user.getId());

        return ps.executeUpdate();
    }

    public int deleteUser(int id) throws SQLException {
        PreparedStatement ps = con.getConexao().prepareStatement("DELETE FROM User WHERE id=?");

        ps.setInt(1, id);

        return ps.executeUpdate();
    }

    private User loadObjects(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
