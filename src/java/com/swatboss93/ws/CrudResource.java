/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swatboss93.ws;

import com.swatboss93.bd.User;
import com.swatboss93.bd.dao.ConexaoLocal;
import com.swatboss93.bd.dao.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author swatboss93
 */
@Path("users")
public class CrudResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CrudResource
     */
    public CrudResource() {
    }

    @GET
    @Produces("application/json")
    public List<User> getPizzas() {
        UserDAO userDAO = new UserDAO(new ConexaoLocal());
        List<User> users = new ArrayList<>();
        try {
            users = userDAO.getUsers();
        } catch (SQLException ex) {
            System.out.println("Get users: " + ex.getMessage());
        }
        return users;
    }
    
    @Path("{id: [0-9]+}")
    @GET
    @Produces("application/json")
    public User getUser(@PathParam("id") int id) {
        UserDAO userDAO = new UserDAO(new ConexaoLocal());
        User user = new User();
        try {
            user = userDAO.getUser(id);
        } catch (SQLException ex) {
            System.out.println("Get user: " + ex.getMessage());
        }
        return user;
    }

    @Path("{id: [0-9]+}")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public User updateUser(User user) {
        UserDAO userDAO = new UserDAO(new ConexaoLocal());
        try {
            userDAO.updateUser(user);
        } catch (SQLException ex) {
            System.out.println("Update user: " + ex.getMessage());
        }
        return user;
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public User insertUser(User user) {
        UserDAO userDAO = new UserDAO(new ConexaoLocal());
        try {
            userDAO.insertUser(user);
            user.setId(7);
        } catch (SQLException ex) {
            System.out.println("Insert user: " + ex.getMessage());
            user.setId(0);
        }
        return user;
    }

    @DELETE
    @Path("{id: [0-9]+}")
    @Produces("application/json")
    public void deleteUser(@PathParam("id") int id) {
        UserDAO userDAO = new UserDAO(new ConexaoLocal());
        try {
            userDAO.deleteUser(id);          
        } catch (SQLException ex) {
            System.out.println("Delete user: " + ex.getMessage());
        }
    }
    
    @Path("authenticate")
    @POST
    @Consumes("application/json")
    public User authenticateUser(User user) {
        System.out.println("AAAAE");
        UserDAO userDAO = new UserDAO(new ConexaoLocal());
        User userBD = new User();
        try {
            userBD = userDAO.authenticateUser(user);
        } catch (SQLException ex) {
            System.out.println("Authenticate user: " + ex.getMessage());
        }
        return userBD;
    }
}
