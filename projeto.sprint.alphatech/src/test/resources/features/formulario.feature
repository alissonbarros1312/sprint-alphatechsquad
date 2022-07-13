#Author: squadalphatech.atlassian.net

@CompraAlltags
Feature: Validar enviar email encontre seu imovel
  Como usuario quero enviar um email para buscar um imovel
  Background:
		Given que eu esteja na "https://visaoimoveisindaiatuba.com.br/encontre-meu-imovel/#undefined"

	@CompraInvalid
	Scenario: Validar enviar email com dados invalidos
    And preencher os dados invalidos
    When eu clicar enviar
    Then validar mensagem de erro
    
  @CompraVazio
	Scenario: Validar enviar email com campos vazios
    When eu clicar enviar
    Then validar mensagem de campo vazio