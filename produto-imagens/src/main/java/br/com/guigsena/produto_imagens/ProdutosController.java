package br.com.guigsena.produto_imagens;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Guilherme Sena
 *
 */
@RestController
public class ProdutosController {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    String hello() {
        return "<b>Para executar os testes copie e cole os seguintes links digitando o id do produto desejado (caso seja necessário):</b>"
        		+ "<p>1 - http://localhost:8080/todosProdutosSemRelacionamento</p>"
        		+ "<p>2 - http://localhost:8080/todosProdutosSemRelacionamentoComFiltro?idProduto=</p>"
        		+ "<p>3 - http://localhost:8080/todosProdutosComRelacionamento</p>"
        		+ "<p>4 - http://localhost:8080/todosProdutosComRelacionamentoComFiltro?idProduto=</p>"
        		+ "<p>5 - http://localhost:8080/todosProdutosFilhosPorProduto?idProduto=</p>"
        		+ "<p>6 - http://localhost:8080/todasImagensPorProduto?idProduto=</p>";
    }

    @RequestMapping("/todosProdutosSemRelacionamento")
    String todosProdtudosSemRelacionamento() {
    	MapSqlParameterSource param = null;
    	List<Produto> produtos  = recuperarTodosProdutos(param);
        return produtos == null ?  "Nenhum produto encontrado" : "Recuperar o(s) Produto(s) excluindo os relacionamentos => ok";
    }
        
    @RequestMapping("/todosProdutosSemRelacionamentoComFiltro")
    String todosProdutosSemRelacionamentoComFiltro(@RequestParam Integer idProduto) {
    	MapSqlParameterSource param = null;
    	if(idProduto != null){
    		param = new MapSqlParameterSource().addValue("idProduto", idProduto);
    	}
    	List<Produto> produtos  = recuperarTodosProdutos(param);
        return produtos == null ?  "Nenhum produto encontrado" : "Recuperar o(s) Produto(s) excluindo os relacionamentos => ok";
    }
    
    @RequestMapping("/todosProdutosComRelacionamento")
    String todosProdtudosComRelacionamento() {
    	MapSqlParameterSource param = null;    	
    	List<Produto> produtos  = recuperarProdutosDependencias(param);
        return produtos == null ?  "Nenhum produto encontrado" : "Recuperar o(s) Produto(s) incluindo um relacionamento específico (Produto ou Imagem) => ok";
    }
    
    @RequestMapping("/todosProdutosComRelacionamentoComFiltro")
    String todosProdutosComRelacionamentoComFiltro(@RequestParam Integer idProduto) {
    	MapSqlParameterSource param = null;
    	if(idProduto != null){
    		param = new MapSqlParameterSource().addValue("idProduto", idProduto);
    	}
    	List<Produto> produtos  = recuperarProdutosDependencias(param);
        return produtos == null ?  "Nenhum produto encontrado" : "Recuperar o(s) Produto(s) incluindo um relacionamento específico (Produto ou Imagem) => ok";
    }
    
    @RequestMapping("/todosProdutosFilhosPorProduto")
    String todosProdutosFilhosPorProduto(@RequestParam Integer idProduto) {
    	MapSqlParameterSource param = null;
    	if(idProduto != null){
    		param = new MapSqlParameterSource().addValue("idProduto", idProduto);
    	}
    	List<Produto> produtos  = recuperarTodosProdutosFilhosPorProduto(param);
        return produtos == null ?  "Nenhum produto encontrado" : "Recupera a coleção de produtos filhos por um id de produto específico => ok";
    	
    }   

    @RequestMapping("/todasImagensPorProduto")
    String todasImagensPorProduto(@RequestParam Integer idProduto) {
    	MapSqlParameterSource param = null;
    	if(idProduto != null){
    		param = new MapSqlParameterSource().addValue("idProduto", idProduto);
    	}
    	List<Imagem> imagens  = recuperarTodosImagensPorProduto(param);
        return imagens == null ?  "Nenhuma imagem encontrado" : "Recupera a coleção de Imagens para um id de produto específico => ok";
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Produto> recuperarTodosProdutos(MapSqlParameterSource param){
    	List<Produto> produtos = null;
    	String sql = "Select id, nome, descricao from produto ";
    	try {
    		if(param != null){
    			sql += " WHERE id = :idProduto";
    		}
    		produtos  = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper(Produto.class));
		} catch (Exception e) {
			System.out.println("erro ao converter produto");
		}    	
    	
    	return produtos;
    }
    
    /**
     * @author Guilherme Sena
     * @param param
     * @return lista de produtos com ou sem filtro por produto
     */
    private List<Produto> recuperarProdutosDependencias(MapSqlParameterSource param){
    	List<Produto> produtos = null;
    	String sql = "Select p.id as idProduto, p.nome, p.descricao, p.idProdutoPai, i.id as idImagem, i.tipo from produto p left join imagem i on p.id = i.idProduto ";
    	try {
    		if(param != null){
    			sql += " WHERE p.id = :idProduto";
    		}
    		sql += " ORDER BY p.id";
    		
    		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, param);
    		Produto ultimoProduto = new Produto(); 
    		//PERCORRER COLUNAS
        	for (Map<String, Object> row : rows) {
        		Produto prod = new Produto();
        		prod.setId((Integer)(row.get("IDPRODUTO")));
        		if(ultimoProduto.getId() == prod.getId()){
        			prod = ultimoProduto;
        		}else{
        			prod.setNome((String)row.get("NOME"));
            		prod.setDescricao((String)row.get("DESCRICAO"));	
        		}
        		
        		Imagem img = new Imagem();
        		img.setId((Integer)(row.get("idImagem")));
        		img.setTipo((String)(row.get("tipo")));
        		
        		if(prod.getLstImagens() == null){
        			prod.setLstImagens(new ArrayList<Imagem>());
        		}
        		prod.getLstImagens().add(img);
        		
        		if((Integer)(row.get("IDPRODUTOPAI")) != null){
        			Produto produtoPai = new Produto();
        			produtoPai.setId((Integer)(row.get("IDPRODUTOPAI")));
        			produtoPai = produtos.get(produtos.indexOf(produtoPai));
        			if(produtoPai.getLstProdutosFilhos() == null){
        				produtoPai.setLstProdutosFilhos(new ArrayList<Produto>());
            		}
        			if(!produtoPai.getLstProdutosFilhos().contains(prod)){
        				produtoPai.getLstProdutosFilhos().add(prod);
        			}
        		}else{        		
	        		if(produtos == null){
	        			produtos = new ArrayList<Produto>();
	        		}
	        		if(!produtos.contains(prod)){
	        			produtos.add(prod);
	        		}
        		}
        		ultimoProduto = prod;
        	}	
		} catch (Exception e) {
			System.out.println("erro ao converter produto");
		}
    	
    	return produtos;
    }
    
    /**
     * @author Guilherme Sena
     * @param param
     * @return lista de produtos filhos 
     */
    private List<Produto> recuperarTodosProdutosFilhosPorProduto(MapSqlParameterSource param){
    	List<Produto> produtos = null;
    	String sql = "Select p.id as idProduto, p.nome, p.descricao, p.idProdutoPai, i.id as idImagem, i.tipo from produto p left join imagem i on p.id = i.idProduto ";
    	try {
    		if(param != null){
    			sql += " WHERE p.idProdutoPai = :idProduto";
    		}
    		sql += " ORDER BY p.id";
    		
    		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, param);
    		Produto ultimoProduto = new Produto(); 
    		//PERCORRER COLUNAS
        	for (Map<String, Object> row : rows) {
        		Produto prod = new Produto();
        		prod.setId((Integer)(row.get("IDPRODUTO")));
        		if(ultimoProduto.getId() == prod.getId()){
        			prod = ultimoProduto;
        		}else{
        			prod.setNome((String)row.get("NOME"));
            		prod.setDescricao((String)row.get("DESCRICAO"));	
        		}
        		
        		Imagem img = new Imagem();
        		img.setId((Integer)(row.get("idImagem")));
        		img.setTipo((String)(row.get("tipo")));
        		
        		if(prod.getLstImagens() == null){
        			prod.setLstImagens(new ArrayList<Imagem>());
        		}
        		prod.getLstImagens().add(img);
        		
    			Produto produtoPai = new Produto();
    			produtoPai.setId((Integer)(row.get("IDPRODUTOPAI")));
        			
    			prod.setProdutoPai(produtoPai);
        		
    			if(produtos == null){
        			produtos = new ArrayList<Produto>();
        		}
        		if(!produtos.contains(prod)){
        			produtos.add(prod);
        		}
        		ultimoProduto = prod;
        	}	
		} catch (Exception e) {
			System.out.println("erro ao converter produto");
		}
    	return produtos;
    }
    
    /**
     * @author Guilherme Sena
     * @param param
     * @return lista de imagem por produto
     */
    private List<Imagem> recuperarTodosImagensPorProduto(MapSqlParameterSource param){
    	List<Imagem> imagens = null;
    	String sql = "Select i.id, i.tipo, i.idProduto from imagem i ";
    	try {
    		if(param != null){
    			sql += " WHERE i.idProduto = :idProduto";
    		}
    		
    		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, param);
    		//PERCORRER COLUNAS
        	for (Map<String, Object> row : rows) {
        		Imagem img = new Imagem();
        		img.setId((Integer)(row.get("id")));
        		img.setTipo((String)(row.get("tipo")));
        		
        		Produto prod = new Produto();
        		prod.setId((Integer)(row.get("IDPRODUTO")));
        		img.setProduto(prod);
        		
        		if(imagens == null){
        			imagens = new ArrayList<Imagem>();
        		}
        		imagens.add(img);
        	}	
		} catch (Exception e) {
			System.out.println("erro ao converter imagem");
		}
    	return imagens;
    }
}
