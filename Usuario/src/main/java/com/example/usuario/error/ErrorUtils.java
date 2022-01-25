package com.example.usuario.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindingResult;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Dado el resultado de una validación lo convierte a un JSn tipo String para que pueda ir en el mensaje de vuelta
//y pueda ser facilmente leido
public class ErrorUtils {
	
    public static String formatMessage( BindingResult result){
    	//recoge todos los errores generados
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        
        ErrorMessage errorMessage = new ErrorMessage("01",errors);
        
        //Lo convierto este objeto a JSOnString
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}