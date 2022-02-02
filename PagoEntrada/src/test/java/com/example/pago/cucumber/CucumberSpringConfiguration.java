package com.example.pago.cucumber;

import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.pago.PagoEntradaApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = PagoEntradaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = PagoEntradaApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {

}
