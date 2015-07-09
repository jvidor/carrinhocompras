/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvidor.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joubertvidor
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrinhoprodutos.findAll", query = "SELECT c FROM Carrinhoprodutos c"),
    @NamedQuery(name = "Carrinhoprodutos.findByIdcarrinho", query = "SELECT c FROM Carrinhoprodutos c WHERE c.carrinhoprodutosPK.idcarrinho = :idcarrinho"),
    @NamedQuery(name = "Carrinhoprodutos.findByIdproduto", query = "SELECT c FROM Carrinhoprodutos c WHERE c.carrinhoprodutosPK.idproduto = :idproduto"),
    @NamedQuery(name = "Carrinhoprodutos.findByIdcliente", query = "SELECT c FROM Carrinhoprodutos c WHERE c.carrinhoprodutosPK.idcliente = :idcliente"),
    @NamedQuery(name = "Carrinhoprodutos.findByIdquantidade", query = "SELECT c FROM Carrinhoprodutos c WHERE c.quantidade = :quantidade")})
public class Carrinhoprodutos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CarrinhoprodutosPK carrinhoprodutosPK;
    @Basic(optional = false)
    @NotNull
    private int quantidade;
    @JoinColumns({
        @JoinColumn(name = "IDCARRINHO", referencedColumnName = "IDCARRINHO", insertable = false, updatable = false),
        @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Carrinho carrinho;
    @JoinColumn(name = "IDPRODUTO", referencedColumnName = "IDPRODUTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produtos produtos;

    public Carrinhoprodutos() {
    }

    public Carrinhoprodutos(CarrinhoprodutosPK carrinhoprodutosPK) {
        this.carrinhoprodutosPK = carrinhoprodutosPK;
    }

    public Carrinhoprodutos(CarrinhoprodutosPK carrinhoprodutosPK, int idquantidade) {
        this.carrinhoprodutosPK = carrinhoprodutosPK;
        this.quantidade = idquantidade;
    }

    public Carrinhoprodutos(int idcarrinho, int idproduto, int idcliente) {
        this.carrinhoprodutosPK = new CarrinhoprodutosPK(idcarrinho, idproduto, idcliente);
    }

    public CarrinhoprodutosPK getCarrinhoprodutosPK() {
        return carrinhoprodutosPK;
    }

    public void setCarrinhoprodutosPK(CarrinhoprodutosPK carrinhoprodutosPK) {
        this.carrinhoprodutosPK = carrinhoprodutosPK;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carrinhoprodutosPK != null ? carrinhoprodutosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrinhoprodutos)) {
            return false;
        }
        Carrinhoprodutos other = (Carrinhoprodutos) object;
        if ((this.carrinhoprodutosPK == null && other.carrinhoprodutosPK != null) || (this.carrinhoprodutosPK != null && !this.carrinhoprodutosPK.equals(other.carrinhoprodutosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jvidor.model.Carrinhoprodutos[ carrinhoprodutosPK=" + carrinhoprodutosPK + " ]";
    }
    
}
