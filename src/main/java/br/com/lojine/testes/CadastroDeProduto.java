package br.com.lojine.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.lojine.dao.CategoriaDao;
import br.com.lojine.dao.ProdutoDao;
import br.com.lojine.modelo.Categoria;
import br.com.lojine.modelo.Produto;
import br.com.lojine.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("DOCINES");
		todos.forEach(produto -> System.out.println(produto.getNome()));
	
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("boline de choconache");
		System.out.println("Preco do Produto: " + precoDoProduto);
	}

	private static void cadastrarProduto() {
		Categoria docines = new Categoria("DOCINES");
		Produto docine = new Produto("boline de choconache", "bolo de chocolate com ganache", new BigDecimal("5"), docines );
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(docines);
		produtoDao.cadastrar(docine);
		
		em.getTransaction().commit();
		em.close();
	}

}
