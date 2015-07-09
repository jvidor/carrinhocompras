/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvidor.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joubertvidor
 */
@Embeddable
public class CarrinhoprodutosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    private int idcarrinho;
    @Basic(optional = false)
    @NotNull
    private int idproduto;
    @Basic(optional = false)
    @NotNull
    private int idcliente;

    public CarrinhoprodutosPK() {
    }

    public CarrinhoprodutosPK(int idcarrinho, int idproduto, int idcliente) {
        this.idcarrinho = idcarrinho;
        this.idproduto = idproduto;
        this.idcliente = idcliente;
    }

    public int getIdcarrinho() {
        return idcarrinho;
    }

    public void setIdcarrinho(int idcarrinho) {
        this.idcarrinho = idcarrinho;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcarrinho;
        hash += (int) idproduto;
        hash += (int) idcliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarrinhoprodutosPK)) {
            return false;
        }
        CarrinhoprodutosPK other = (CarrinhoprodutosPK) object;
        if (this.idcarrinho != other.idcarrinho) {
            return false;
        }
        if (this.idproduto != other.idproduto) {
            return false;
        }
        if (this.idcliente != other.idcliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jvidor.model.CarrinhoprodutosPK[ idcarrinho=" + idcarrinho + ", idproduto=" + idproduto + ", idcliente=" + idcliente + " ]";
    }
    
}
