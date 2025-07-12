<h1 align="center"> Gerenciador de Gastos API </h1>

# Resumo do projeto
O serviço é um organizador de livros, ele utiliza banco H2 como database para salvar Livros, Categorias e Autores. Como usuário, você poderá salvar Livros e vincular a eles seu autor e categoria, também é possível ver o todos os livros, e retornar eles por autores ou categorias a qual fazem parte. Ao utilizar a aplicação, você poderá checar as rotas com a documentação gerada pelo Springdoc. (Mais informações na área de utilização).

# Endpoints

<img width="1193" height="842" alt="image" src="https://github.com/user-attachments/assets/5e05f7fa-eadf-4e21-921e-36c097cbfbd0" />


## Entenda os Endpoints
- ``/api/autores: Endpoint de tipo Post onde é feita a criação de um autor``
- ``/api/autores: Endpoint de tipo GET onde é feita o retorno de todos os autores de forma paginada``
- ``/api/autores/{id}: Endpoint de tipo GET onde é feita o retorno de um autor especifico com base no seu id``
- ``/api/autores/{id}/livros: Endpoint de tipo GET onde é feita o retorno de todos os livros de um autor especifico``
- ``/api/autores/{id}: Endpoint de tipo PUT onde é feita a atualização de um autor com base no seu id``
- ``/api/autores/{id}: Endpoint de tipo DELETE onde é feita a exclusão de um autor``
- ``/api/categorias: Endpoint de tipo POST onde é feita a criação de uma categoria``
- ``/api/categorias: Endpoint de tipo GET onde é feito o retorno de todas as categorias de forma paginada``
- ``/api/categorias/{id}/livros: Endpoint de tipo GET onde é feito o retorno de todos os livros de uma certa categoria``
- ``/api/livros: Endpoint de tipo Post onde é feita a criação de um livro``
- ``/api/livros/importar: Endpoint de tipo Post onde é feita a importação de um livro da amazon``
- ``/api/livros: Endpoint de tipo GET onde é feita o retorno de todos os livros de forma paginada``
- ``/api/livros/{id}: Endpoint de tipo GET onde é feita o retorno de um livro especifico com base no seu id``
- ``/api/livros/search: Endpoint de tipo GET onde é feita o retorno de um livro especifico com base no seu titulo``
- ``/api/livros/{id}: Endpoint de tipo PUT onde é feita a atualização de um livro com base no seu id``
- ``/api/livros/{id}: Endpoint de tipo DELETE onde é feita a exclusão de um livro``


## ✔️ Bibliotecas e tecnologias utilizadas

- ``Java 17``
- ``Spring 3.5.3``
- ``Maven``
- ``H2 DataBase``
- ``HtmlUnit``
- ``SpringDoc``
- ``DevTools``
- ``Validation``

# Features 
1. Operação de Crud de autores.
2. Operação de Crud do categorias.
3. Operação de Crud de livros.
4. Vincular um Livro ao autor e categoria.
5. Retornar livro por autor ou categoria.
6. Importar livros da amazon.

# Instalação
1. Faça a clonagem do projeto
2. Abra o projeto na sua IDE e faça a instalação das dependências através do Maven
3. Acesse http://localhost:8080/swagger-ui.html para ver a documentação

