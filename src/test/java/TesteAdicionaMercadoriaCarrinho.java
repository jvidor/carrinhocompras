/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jvidor.model.Carrinho;
import com.jvidor.model.CarrinhoController;
import com.jvidor.model.Carrinhoprodutos;
import com.jvidor.model.Produtos;
import javax.faces.model.SelectItem;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author joubertvidor
 */
public class TesteAdicionaMercadoriaCarrinho {
    
    private Carrinho carrinho;
    private Carrinhoprodutos item1, item2;
    private final String valorEsperado = "CAPACETE";

    
    public TesteAdicionaMercadoriaCarrinho() {
    }
        
    @Before
    public void MockSetUp() {
        
        //instanciar objeto carrinho
        carrinho = new Carrinho();
        
        //atribuir valor cliente no objeto carrinho
        carrinho.getClientes().setIdcliente(1);
        
        Produtos produto1 = new Produtos();
        produto1.setNomeproduto("MOTOCICLETA");
        produto1.setPreco(10000.00F);
        
        item1 = new Carrinhoprodutos();
        item1.setQuantidade(1);
        item1.setProdutos(produto1);

        Produtos produto2 = new Produtos();
        produto2.setNomeproduto("CAPACETE");
        produto2.setPreco(200.00F);
        
        item2 = new Carrinhoprodutos();
        item2.setQuantidade(2);
        item2.setProdutos(produto2);
        
        carrinho.getCarrinhoprodutosCollection().add(item2);
        
    }
    
    @Test
    public void adicionaMercadoriaCarrinho() {
        
        CarrinhoController carrinhoCRUD = new CarrinhoController();

        carrinho.getCarrinhoprodutosCollection().add(item1);
        carrinhoCRUD.create(carrinho);

        carrinho.getCarrinhoprodutosCollection().add(item1);
        carrinhoCRUD.update(carrinho);

        SelectItem[] resultado = carrinhoCRUD.getItemsAvailableSelectOne(2);

        assertEquals("Teste bem sucedido!",valorEsperado,resultado.toString());
        fail("Resultado não esperado");

    }
    
}
