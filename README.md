# spring-h2embedded-junit
Exemplo de aplicação em java (jdk 1.8, spring boot) com banco de dados H2 embarcado e teste unitário com Junit.


A aplicação possui a entidade produto que pode conter uma lista de imagens. as eguintes funcionalidades foram implementadas:
- Recuperar todos os Produtos cadastrados excluindo os relacionamentos;
- Recuperar todos os Produtos incluindo um relacionamento específico (Produto ou Imagem);
- Recuperar um produto com um id específico sem seus relacionamentos;
- Recuperar um produto com um id específico e seus relacionamentos;
- Recupera a coleção de produtos filhos por um id de produto específico;
- Recupera a coleção de Imagens para um id de produto específico;

# Como utilizar
Executar o arquivo APP.java e a aplcação ja será stardata. O banco embarcado utilizado foi o H2. Para executar os métodos basta executar o seguintes links passando a porta local do servidor utilizado bem como o id do produto para as respectivas funcionalidades que necessitam.
- http://localhost:8080/todosProdutosSemRelacionamento
- http://localhost:8080/todosProdutosSemRelacionamentoComFiltro?idProduto=
- http://localhost:8080/todosProdutosComRelacionamento
- http://localhost:8080/todosProdutosComRelacionamentoComFiltro?idProduto=
- http://localhost:8080/todosProdutosFilhosPorProduto?idProduto=
- http://localhost:8080/todasImagensPorProduto?idProduto=
