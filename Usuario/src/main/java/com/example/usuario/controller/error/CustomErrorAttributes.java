package com.example.usuario.controller.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.example.usuario.controller.UsuarioController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

//Se usará para alterar el formato propio de JSOn para los errores
//Por ejemplo, aqui se accede cuando se pone mal la URI
// Ejemplos
//     PUT de valor no existente
//     GET de valor no existente
//El acceso es automatico

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	// Formato fecha
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	// Para Spring Boot > 2.3
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
		logger.info("------ getErrorAttributes(): " + options);
		// TODO Auto-generated method stub
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
		logger.info("------ getErrorAttributes(): " + options);

		// format & update timestamp
		Object timestamp = errorAttributes.get("timestamp");
		if (timestamp == null) {
			errorAttributes.put("timestamp", dateFormat.format(new Date()));
		} else {
			errorAttributes.put("timestamp", dateFormat.format((Date) timestamp));
		}

		// Eliminamos la traza para simplificar la salida
		errorAttributes.remove("trace");

		// Insertamos nueva clave
		errorAttributes.put("jdk", System.getProperty("java.version"));

		return errorAttributes;
	}

}