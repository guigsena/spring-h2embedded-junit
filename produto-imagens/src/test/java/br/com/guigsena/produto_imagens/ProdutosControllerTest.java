package br.com.guigsena.produto_imagens;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:h2:mem:produto-imagens;DB_CLOSE_ON_EXIT=FALSE"})
public class ProdutosControllerTest {
    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void todosProdtudosSemRelacionamento() throws Exception {
    	given().get("/todosProdtudosSemRelacionamento")
    	.then()
    	.body(is("Testado todosProdtudosSemRelacionamento"));
    }
    
    @Test
    public void todosProdutosSemRelacionamentoComFiltro() throws Exception {
    	int idProd = 1;
    	given().param("idProduto", idProd)
		.get("/todosProdutosSemRelacionamentoComFiltro")
		.then()
		.body(is("Testado todosProdutosSemRelacionamentoComFiltro"))
		.body("idProduto", is(idProd));
    }
    
    @Test
    public void todosProdtudosComRelacionamento() throws Exception {
    	given().get("/todosProdtudosComRelacionamento")
    		.then()
    		.body(is("Testado todosProdtudosComRelacionamento"));
    }
    
    @Test
    public void todosProdutosComRelacionamentoComFiltro() throws Exception {
    	int idProd = 2;
    	given().param("idProduto", idProd)
    		.get("/todosProdutosComRelacionamentoComFiltro")
    		.then()
    		.body(is("Testado todosProdutosComRelacionamentoComFiltro"))
    		.body("idProduto", is(idProd));
    }
    
    
    @Test
    public void todosProdutosFilhosPorProduto() throws Exception {
    	int idProd = 2;
    	given().param("idProduto", idProd)
		.get("/todosProdutosFilhosPorProduto")
		.then()
		.body(is("Testado todosProdutosFilhosPorProduto"))
		.body("idProduto", is(idProd));
    }

    @Test
    public void todasImagensPorProduto() throws Exception {
    	int idProd = 3;
    	given().param("idProduto", idProd)
		.get("/todasImagensPorProduto")
		.then()
		.body(is("Testado todasImagensPorProduto"))
		.body("idProduto", is(idProd));    
    }
}