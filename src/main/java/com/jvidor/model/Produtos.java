/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvidor.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Produtos.findAll", query = "SELECT p FROM Produtos p"),
    @NamedQuery(name = "Produtos.findByIdproduto", query = "SELECT p FROM Produtos p WHERE p.idproduto = :idproduto"),
    @NamedQuery(name = "Produtos.findByNomeproduto", query = "SELECT p FROM Produtos p WHERE p.nomeproduto = :nomeproduto"),
    @NamedQuery(name = "Produtos.findByImagemproduto", query = "SELECT p FROM Produtos p WHERE p.imagemproduto = :imagemproduto"),
    @NamedQuery(name = "Produtos.findByPreco", query = "SELECT p FROM Produtos p WHERE p.preco = :preco")})
public class Produtos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idproduto;
    @Size(max = 10)
    private String nomeproduto;
    @Size(max = 10)
    private String imagemproduto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Float preco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtos")
    private Collection<Carrinhoprodutos> carrinhoprodutosCollection;

    public Produtos() {
    }

    public Produtos(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public Integer getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public String getNomeproduto() {
        return nomeproduto;
    }

    public void setNomeproduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
    }

    public String getImagemproduto() {
        return imagemproduto;
    }

    public void setImagemproduto(String imagemproduto) {
        this.imagemproduto = imagemproduto;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    @XmlTransient
    public Collection<Carrinhoprodutos> getCarrinhoprodutosCollection() {
        return carrinhoprodutosCollection;
    }

    public void setCarrinhoprodutosCollection(Collection<Carrinhoprodutos> carrinhoprodutosCollection) {
        this.carrinhoprodutosCollection = carrinhoprodutosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduto != null ? idproduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.idproduto == null && other.idproduto != null) || (this.idproduto != null && !this.idproduto.equals(other.idproduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jvidor.model.Produtos[ idproduto=" + idproduto + " ]";
    }
    
}
