#language: pt
#Author: Marilia
#Version: 1.0
#Encoding: UTF-8

@Gorest @regressivo
Funcionalidade: Criar e editar contas de usuários
  Eu como Administrador do sistema, quero cadastrar, editar e excluir usuários do sistema

  @post
  Cenario: Cadastrar novo usuário API Gorest
    Dado que tenho os dados preenchidos corretamente
    E que possuo gorest token valido
    Quando envio um request de cadastro de usuário com dados validos
    Entao o usuario deve ser criado corretamente
    E o status code do request deve ser 201

  @get
    #TODO o teste abaixo não está funcionando ainda, mas a parte do relatório está
  Cenario: Buscar um usuário existente na API Gorest
    Dado que possuo gorest token valido
    E existe um usuario cadastrado na api
    Quando buscar esse usuario
    Entao os dados dos usuarios devem ser retornados
    E o status code do request deve ser 200