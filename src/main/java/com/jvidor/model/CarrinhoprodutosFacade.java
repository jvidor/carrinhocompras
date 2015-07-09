/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvidor.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author joubertvidor
 */
@Stateless
public class CarrinhoprodutosFacade extends AbstractFacade<Carrinhoprodutos> {
    @PersistenceContext(unitName = "com.jvidor_carrinho_war_1.0")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarrinhoprodutosFacade() {
        super(Carrinhoprodutos.class);
    }
    
}
