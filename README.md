# Sistema de Agendamento de Tarefas

Este é um projeto Java de um sistema simples de agendamento de tarefas. Ele permite que você crie, visualize, atualize e exclua tarefas em um banco de dados MySQL e foi criado para auxiliar a compreensão sobre Testes de Integração nas aulas de Teste de Software.


## Pré-requisitos

Antes de começar, certifique-se de que você tenha o seguinte instalado em seu ambiente de desenvolvimento:

- Java Development Kit (JDK)
- Apache Maven (opcional, se você planeja usar o Maven para gerenciar dependências)
- MySQL Server
- Git (para clonar o repositório)

## Configuração do Banco de Dados

Para configurar o banco de dados, siga estas etapas:

1. Crie um banco de dados MySQL chamado `todolist_test`.

2. No seu cliente MySQL, abra um novo arquivo de consulta e cole o seguinte script SQL:

```sql
-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS todolist_test;

-- Usar o banco de dados
USE todolist_test;

-- Tabela para armazenar as tarefas
CREATE TABLE IF NOT EXISTS tarefas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    finalizada BOOLEAN NOT NULL
);

-- Exemplo de inserção de dados iniciais (opcional)
INSERT INTO tarefas (descricao, finalizada) VALUES
    ('Estudar Java', false),
    ('Fazer compras', true),
    ('Preparar apresentação', false);

3. No arquivo `DatabaseConnection.java`, atualize as informações de conexão com o banco de dados, como o URL, o nome de usuário e a senha de acordo com a configuração do seu MySQL.

## Executando o Projeto

1. Clone este repositório para o seu ambiente de desenvolvimento:

   ```bash
   git clone https://github.com/seu-nome-de-usuario/AgendamentoDeTarefas.git
