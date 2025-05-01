# Sistema de Pedidos de Loja - UCS 360

Projeto desenvolvido para a disciplina **Programação Orientada a Objetos** na **Universidade de Caxias do Sul** (UCS) - 2025/2.

## 📚 Sobre o Projeto

Este sistema tem como objetivo simular uma aplicação de pedidos para uma loja, utilizando os conceitos de Programação Orientada a Objetos (POO) em Java. O projeto é dividido em duas partes:

- **Parte 1**: Implementação de cadastros e gestão de estoque utilizando vetores.
- **Parte 2**: Realização de pedidos, consulta de dados e armazenamento em arquivos utilizando listas e tratamento de exceções.

A interface do sistema é baseada em menus de texto (modo caractere), conforme exemplo fornecido em aula.

## ✨ Funcionalidades

### Parte 1 (Administrador)

- Sistema de Login (diferenciação de acesso por tipo de usuário)
- Cadastro de Fornecedores (Inclusão, Alteração, Exclusão e Consulta)
- Cadastro de Produtos vinculados a Fornecedores (Inclusão, Alteração, Exclusão e Consulta)
- Gestão de Estoque de Produtos

### Parte 2 (Cliente e Administração)

- Consulta de produtos por código ou descrição
- Carrinho de Compras e Realização de Pedidos
- Validação de estoque disponível no momento da compra
- Cálculo automático de valores de pedidos com ICMS de 17%
- Consulta de Pedidos:
  - Por número de pedido
  - Por intervalo de datas
- Gestão de status dos pedidos (Enviado, Cancelado)
- Armazenamento e recuperação de dados via arquivos
- Tratamento de Exceções (ex: estoque insuficiente)

## 🔧 Tecnologias Utilizadas

- Java (versão recomendada: 21)
- Eclipse IDE
- Programação Orientada a Objetos
- Manipulação de arquivos (.txt)
- Estruturas de Dados: Vetores e Listas

## 📁 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/Eduardo-Bauer/projeto_POO.git
   ```
2. Importe o projeto no **Eclipse IDE**.
3. Compile e execute a partir da classe principal.
4. Siga as instruções exibidas nos menus para navegação no sistema.

> ⚠️ Atenção: O projeto deve ser importado como um **Projeto Java** no Eclipse. Certifique-se que todas as dependências e pastas estejam corretamente estruturadas.

## 👥 Integrantes do Grupo

- Eduardo Felipe Bauer
- Gabriel Ribeiro Bossa
- Johann Manfro

## 📅 Datas de Entrega

- **Parte 1**: 22/05/2025
- **Parte 2**: 03/07/2025

## 📄 Requisitos de Avaliação

- Implementação correta das funcionalidades descritas
- Estruturação adequada em classes (respeitando POO)
- Qualidade e clareza do código
- Cumprimento dos requisitos mínimos e obrigatórios

## 📜 Licença

Projeto acadêmico desenvolvido exclusivamente para fins educacionais.
