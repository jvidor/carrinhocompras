/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvidor.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author joubertvidor
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrinho.findAll", query = "SELECT c FROM Carrinho c"),
    @NamedQuery(name = "Carrinho.findByIdcarrinho", query = "SELECT c FROM Carrinho c WHERE c.carrinhoPK.idcarrinho = :idcarrinho"),
    @NamedQuery(name = "Carrinho.findByIdcliente", query = "SELECT c FROM Carrinho c WHERE c.carrinhoPK.idcliente = :idcliente"),
    @NamedQuery(name = "Carrinho.findByDatetime", query = "SELECT c FROM Carrinho c WHERE c.datetime = :datetime"),
    @NamedQuery(name = "Carrinho.findByPagamento", query = "SELECT c FROM Carrinho c WHERE c.pagamento = :pagamento")})
public class Carrinho implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CarrinhoPK carrinhoPK;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    private String pagamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrinho")
    private Collection<Carrinhoprodutos> carrinhoprodutosCollection;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clientes clientes;

    public Carrinho() {
    }

    public Carrinho(CarrinhoPK carrinhoPK) {
        this.carrinhoPK = carrinhoPK;
    }

    public Carrinho(CarrinhoPK carrinhoPK, String pagamento) {
        this.carrinhoPK = carrinhoPK;
        this.pagamento = pagamento;
    }

    public Carrinho(int idcarrinho, int idcliente) {
        this.carrinhoPK = new CarrinhoPK(idcarrinho, idcliente);
    }

    public CarrinhoPK getCarrinhoPK() {
        return carrinhoPK;
    }

    public void setCarrinhoPK(CarrinhoPK carrinhoPK) {
        this.carrinhoPK = carrinhoPK;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    @XmlTransient
    public Collection<Carrinhoprodutos> getCarrinhoprodutosCollection() {
        return carrinhoprodutosCollection;
    }

    public void setCarrinhoprodutosCollection(Collection<Carrinhoprodutos> carrinhoprodutosCollection) {
        this.carrinhoprodutosCollection = carrinhoprodutosCollection;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carrinhoPK != null ? carrinhoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrinho)) {
            return false;
        }
        Carrinho other = (Carrinho) object;
        if ((this.carrinhoPK == null && other.carrinhoPK != null) || (this.carrinhoPK != null && !this.carrinhoPK.equals(other.carrinhoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jvidor.model.Carrinho[ carrinhoPK=" + carrinhoPK + " ]";
    }
    
}
