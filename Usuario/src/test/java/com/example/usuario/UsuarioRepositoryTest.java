package com.example.usuario;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.usuario.model.Usuario;
import com.example.usuario.repository.UsuarioRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Test
	void shouldReturnTheListWhenFindAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		assertThat(usuarios).hasSize(usuarios.size());
	}

	@Test
	void shouldSaveTheUserSavedCorrectly() {
		Usuario usuario = new Usuario();
		
		usuario.setNombre("nombre");
		usuario.setApellido("apellido");
		usuario.setMail("mail@gmail.com");
		usuario.setPassword("password");
		usuario.setFechaAlta(LocalDate.now(ZoneId.of("Europe/Madrid")));
		usuarioRepository.save(usuario);
		assertThat(usuarioRepository.findAll())
			.extracting(Usuario::getNombre)
			.contains("nombre");
	}
	
	
	

}


