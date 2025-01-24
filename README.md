# Seras / Sistema de Gerenciamento de Empréstimos
**Este projeto conta com uma interface visual primitiva**

Este é um sistema CRUD desenvolvido em Java 17 com objetivos de experimentação e estudo, com o tema de um sistema de empréstimos, tem como objetivo gerenciar empréstimos, incluindo funcionalidades para diferentes tipos de usuários, como administradores e usuários não logados. O projeto utiliza o servidor Apache Tomcat e segue o padrão arquitetural MVC.

Foi criado com o hipótetico uso interno em uma empresa de empréstimos primitiva.

## Funcionalidades Principais

### Para Usuários Não Logados
- Cadastro de clientes.
- Cadastro de propostas de empréstimos associadas a determinados clientes.

### Para Administradores Logados
- **Propostas de Empréstimos:**
  - Aprovação ou rejeição de propostas, transformando-as em empréstimos.
  - Exclusão de propostas.
- **Empréstimos:**
  - Atualização das informações de empréstimos.
  - Exclusão de empréstimos.
- **Clientes e Usuários:**
  - Atualização das informações de clientes e usuários.
  - Exclusão de clientes e usuários.

### Outros Recursos
- **Cookies:**
  - O sistema utiliza cookies para gerenciar sessões de usuários logados, garantindo a persistência do login durante o período configurado.
- **Registro de Logs:**
  - Todos os acessos à aplicação são registrados em logs, incluindo:
    - Endereço IP do usuário.
    - Página acessada.
    - ID do usuário, caso esteja logado.

## Tecnologias Utilizadas
- **Java 17:** Linguagem principal do sistema.
- **Apache Tomcat:** Servidor de aplicações utilizado para hospedar o sistema.
- **MVC (Model-View-Controller):** Arquitetura padrão para organizar o código.
- **Banco de Dados Relacional:** Gerenciamento das entidades de usuários, clientes, propostas e empréstimos.

## Estrutura do Projeto
1. **Model:** Classes Java que representam as entidades principais:
   - Usuário
   - Cliente
   - Proposta de Empréstimo
   - Empréstimo

2. **Controller:** Servlets para gerenciar a lógica de negócios e as interações entre Model e View.

3. **View:** Páginas JSP para interação com o usuário.

## Configuração do Ambiente
1. Instale o [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
2. Configure o [Apache Tomcat](https://tomcat.apache.org/) (versão 9 ou superior).
3. Configure o banco de dados conforme o esquema fornecido `seras.sql`.
4. Importe o projeto em uma IDE de sua escolha (Eclipse ou IntelliJ IDEA).
5. Configure o servidor Tomcat na IDE e implante o projeto.

## Execução do Sistema
1. Inicie o servidor Apache Tomcat e o SGBD escolhido.
2. Acesse a aplicação pelo navegador em: `http://localhost:8080/seras`.
3. Utilize as funcionalidades conforme as permissões:
   - Usuário não logado: Acesso limitado.
   - Administrador logado: Acesso completo ao sistema.

## Estrutura de Permissões
- **Não logado:**
  - Cadastro de clientes.
  - Cadastro de propostas de empréstimos.
- **Administrador:**
  - Aprovação e rejeição de propostas.
  - Atualização e exclusão de informações (empréstimos, clientes, propostas, usuários).

## Licença
Este projeto é de uso educacional e foi desenvolvido para estudos e experimentações no desenvolvimento de aplicações corporativas.

## Contato
Caso tenha dúvidas ou sugestões, entre em contato pelo e-mail caiothurlerrr@gmail.com ou c.thurlerr@gmail.com.