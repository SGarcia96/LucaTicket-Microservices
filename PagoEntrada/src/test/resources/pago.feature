#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Comprobar si el pago es posible
  Yo como usuario
  quiero comprobar si el pago es posible
  para comprar una entrada
  
  Scenario: El aforo del evento esta completo
    Given un aforo total menor o igual que el numero de entradas vendidas
    When se llama a la pasarela de pago 
    Then se obtiene un mensaje "aforo completo"
    And un codigo 451
  
  Scenario: La tarjeta esta caducada
    Given una tarjeta caducada
    When se llama al servicio generaMensajeDePago
    Then se obtiene un mensaje "tarjeta caducada"
    And un codigo 406 
    
  Scenario: La tarjeta no tiene saldo suficiente
    Given una tarjeta con un saldo inferior al precio de la entrada
    When se llama al servicio generaMensajeDePago
    Then se obtiene un mensaje "dispone de un saldo insuficiente"
    And un codigo 417
    
  Scenario: Datos incorrectos
    Given una tarjeta con datos incorrectos
    When se llama al servicio generaMensajeDePago
    Then se obtiene un mensaje "los datos de la tarjeta son erroneos"
    And un codigo 406
    
  Scenario: Pago correcto
    Given una tarjeta con todos los datos validos
    When se llama al servicio generaMensajeDePago
    Then se obtiene un mensaje "Pago efectuado con éxito"
    And un codigo 200    