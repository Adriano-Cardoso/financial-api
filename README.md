# Projeto Financial-API em Java

Este é um projeto Java para uma aplicação FinTech desenvolvida com Spring Boot. O objetivo é fornecer gestão financeira pessoal ou empresarial, incluindo funcionalidades como cadastro de usuários, gestão de contas, transações financeiras, orçamento, integração com Kafka, Dockerização da aplicação e integração com APIs externas gratuitas.

## Funcionalidades

- Cadastro de Usuários
- Gestão de Contas
- Transações Financeiras
- Orçamento
- Implementação Kafka
- Docker e Docker Compose
- Integração com APIs Externas Gratuitas

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.0.0
- Spring Security
- Spring Data JPA
- Kafka
- Docker
- PostgreSQL
- Lombok
- RESTful APIs

## Arquitetura e Design

O projeto segue princípios da arquitetura hexagonal e utiliza o padrão de microserviços para melhor escalabilidade e manutenibilidade. As camadas são separadas de acordo com o princípio de responsabilidade única (Single Responsibility Principle - SRP), garantindo um código modular e de fácil extensão.



## Configuração e Execução

### Pré-requisitos
- Java 17 SDK instalado
- Docker instalado e configurado

### Passos para Executar

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/projeto-fintech.git
   cd projeto-fintech

2. Build da aplicação:

   ```bash
   mvn clean package

3. Suba os containers Docker:
    
   ```bash
   docker-compose up

4. Acesse a API em: http://localhost:8080



### Considerações Finais

- **Detalhes Técnicos**: Inclua detalhes sobre as tecnologias utilizadas, versões, padrões de arquitetura adotados e qualquer decisão técnica relevante.
- **Instruções de Configuração**: Forneça instruções claras para configurar e executar o projeto localmente, utilizando ferramentas como Maven e Docker.
- **Estrutura de Diretórios**: Mostre a estrutura organizacional do projeto para facilitar a navegação e compreensão.
- **Contribuição e Licença**: Encoraje a contribuição e especifique a licença sob a qual o projeto está disponível.

Este `README.md` serve como uma base sólida para apresentar seu projeto FinTech de maneira profissional e atrativa para potenciais colaboradores ou empregadores. Personalize-o conforme necessário para refletir os detalhes específicos e o escopo do seu projeto.
