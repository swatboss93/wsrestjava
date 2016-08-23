/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas.inf.pdm.ws;

import br.pucpcaldas.inf.pdm.bd.Pizza;
import br.pucpcaldas.inf.pdm.bd.dao.ConexaoLocal;
import br.pucpcaldas.inf.pdm.bd.dao.PizzaDAO;
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
@Path("pizza")
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
    public List<Pizza> getPizzas() {
        PizzaDAO pizzaDAO = new PizzaDAO(new ConexaoLocal());
        List<Pizza> pizzas = new ArrayList<>();
        try {
            pizzas = pizzaDAO.getPizzas();
        } catch (SQLException ex) {
            System.out.println("Get pizzas: " + ex.getMessage());
        }
        return pizzas;
    }
    
    @Path("{id: [0-9]+}")
    @GET
    @Produces("application/json")
    public Pizza getPizza(@PathParam("id") int id) {
        PizzaDAO pizzaDAO = new PizzaDAO(new ConexaoLocal());
        Pizza pizza = new Pizza();
        try {
            pizza = pizzaDAO.getPizza(id);
        } catch (SQLException ex) {
            System.out.println("Get pizza: " + ex.getMessage());
        }
        return pizza;
    }

    @PUT
    @Consumes("application/json")
    public void updatePizza(Pizza pizza) {
        System.out.println("Entrou");
        PizzaDAO pizzaDAO = new PizzaDAO(new ConexaoLocal());
        try {
            System.out.println("Vai fazer update");
            System.out.println("grupo: " + pizza.getGrupo().name());
            pizzaDAO.updatePizza(pizza);
            System.out.println("updated");
        } catch (SQLException ex) {
            System.out.println("Update pizza: " + ex.getMessage());
        }
    }
    
    @POST
    @Consumes("application/json")
    public void insertPizza(Pizza pizza) {
        PizzaDAO pizzaDAO = new PizzaDAO(new ConexaoLocal());
        try {
            pizzaDAO.insertPizza(pizza);
        } catch (SQLException ex) {
            System.out.println("Insert pizza: " + ex.getMessage());
        }
    }

    @DELETE
    @Path("{id: [0-9]+}")
    @Produces("application/json")
    public void deletePizza(@PathParam("id") int id) {
        PizzaDAO pizzaDAO = new PizzaDAO(new ConexaoLocal());
        try {
            pizzaDAO.deletePizza(id);          
        } catch (SQLException ex) {
            System.out.println("Delete pizza: " + ex.getMessage());
        }
    }
}
