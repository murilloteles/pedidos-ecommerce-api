<h1 align="center">pedidos-ecommerce-api</h1>
<p align="center">Api Rest para gerenciar pedidos de venda em um ecommerce. Cada pedido é composto por um conjunto de itens, cada um com um preço e quantidade. O sistema  calcula o valor total do pedido e armazena essa informação no banco de dados.
</p>

# Funcionalidades do projeto

- `Listar`: Lista todos os pedidos já feitos.
- `Buscar`: Busca um pedido específico.
- `Salvar`: Salva um pedido no banco de dados.
- `Atualizar`: Atualiza um pedido específico já salvo.
- `Deletar`: Exclui um pedido específico já salvo.

# Tecnologias utilizadas

- `Java 8`
- `Spring Boot`
- `Apache Kafka`
- `PostgreSQL`
- `Flyway`
- `JUnit`

# Dependências do projeto

- `itens-pedidos-ecommerce-ms`: Utiliza-se a funcionalidade "Listar" desse micro serviço para buscar todos os itens disponíveis para Venda no e-commerce. Essa troca de mensagens é feita pelo mensageiro Apache Kafka, caso ainda não esteja publicada essa informação no tópico Kafka, faz uma requisição GET para o serviço. 
