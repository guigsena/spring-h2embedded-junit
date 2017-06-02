# spring-h2embedded-junit
Exemplo de CRUD em java com banco de dados H2 embarcado e teste unitário com Junit.

A aplicação possui a entidade produto que pode conter uma lista de imagens. as eguintes funcionalidades foram implementadas:
- Recuperar todos os Produtos cadastrados excluindo os relacionamentos;
- Recuperar todos os Produtos incluindo um relacionamento específico (Produto ou Imagem);
- Recuperar um produto com um id específico sem seus relacionamentos;
- Recuperar um produto com um id específico e seus relacionamentos;
- Recupera a coleção de produtos filhos por um id de produto específico;
- Recupera a coleção de Imagens para um id de produto específico;
