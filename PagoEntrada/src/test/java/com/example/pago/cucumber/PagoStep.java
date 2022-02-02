package com.example.pago.cucumber;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.example.pago.model.MensajePago;
import com.example.pago.model.Tarjeta;
import com.example.pago.service.PagoService;
import com.example.pago.utils.RandomTarjeta;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class PagoStep{

	@Autowired
    private TestRestTemplate testRestTemplate;
	
	@Autowired
	private PagoService pagoService;
	
	private int aforo = 50;
	private int entradasVendidas = 10;
	private MensajePago mensaje;
	private float precio = 0.01F;
	private Tarjeta tarjeta;

	@Given("un aforo total menor o igual que el numero de entradas vendidas")
	public void unAforoTotalMenorOIgualQueElNumeroDeEntradasVendidas() {
		aforo = 50;
		entradasVendidas = 50;
		precio = 0.01F;
	}

	@When("se llama a la pasarela de pago")
	public void seLlamaALaPasarelaDePago(){
		mensaje = testRestTemplate
				.getForObject("http://localhost:9000/pago/aforoTotal/" + aforo + "/entradasVendidas/" + entradasVendidas + "/precio/" + precio, MensajePago.class);
	}

	@Then("se obtiene un mensaje {string}")
	public void seObtieneUnMensaje(String mensajeEsperado){
		assertEquals(mensajeEsperado, mensaje.getMensaje());
	}

	@And("un codigo {int}")
	public void unCodigo(int codigoEsperado){
		assertEquals(codigoEsperado, mensaje.getCodigo().value());
	}

	@Given("una tarjeta caducada")
	public void unaTarjetaCaducada(){
		tarjeta = RandomTarjeta.creaTarjetaCaducada();
	}

	@When("se llama al servicio generaMensajeDePago")
	public void seLlamaAlServicioGeneraMensajeDePago() {
		mensaje = pagoService.generaMensajeDePago(aforo, entradasVendidas, precio, tarjeta);
	}

	@Given("una tarjeta con un saldo inferior al precio de la entrada")
	public void unaTarjetaConUnSaldoInferiorAlPrecioDeLaEntrada(){
		tarjeta = RandomTarjeta.creaTarjetaSinSaldo();
		precio = 10F;
	}

	@Given("una tarjeta con datos incorrectos")
	public void unaTarjetaConDatosIncorrectos(){
		tarjeta = RandomTarjeta.creaTarjetaErronea();
	}

	@Given("una tarjeta con todos los datos validos")
	public void unaTarjetaConTodosLosDatosValidos(){
		tarjeta = RandomTarjeta.creaTarjetaCorrecta();
	}
	
}
