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
public class CarrinhoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    private int idcarrinho;
    @Basic(optional = false)
    @NotNull
    private int idcliente;

    public CarrinhoPK() {
    }

    public CarrinhoPK(int idcarrinho, int idcliente) {
        this.idcarrinho = idcarrinho;
        this.idcliente = idcliente;
    }

    public int getIdcarrinho() {
        return idcarrinho;
    }

    public void setIdcarrinho(int idcarrinho) {
        this.idcarrinho = idcarrinho;
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
        hash += (int) idcliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarrinhoPK)) {
            return false;
        }
        CarrinhoPK other = (CarrinhoPK) object;
        if (this.idcarrinho != other.idcarrinho) {
            return false;
        }
        if (this.idcliente != other.idcliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jvidor.model.CarrinhoPK[ idcarrinho=" + idcarrinho + ", idcliente=" + idcliente + " ]";
    }
    
}
