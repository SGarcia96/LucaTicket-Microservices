package com.example.demo.controller;

import java.net.URI;

import org.slf4j.Logger;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController<UsuarioService, UsuarioAdapter> {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioAdapter usuarioAdapter;
	
	@GetMapping("/{id}")
	public UsuarioResponse getUsuario(@PathVariable Long id) {
		log.info("--- usuario por id " + id);
		final Usuario usuario = usuarioService.findId(id).orElseThrow();
		return UsuarioAdapter.of(usuario);
	}

	@Operation(summary = "Buscar estudiantes por ID", description = "Dado un ID, devuelve un objeto Usuario", tags= {"usuario"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario localizado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "400", description = "No válido (NO implementado) ", content = @Content),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado (NO implementado)", content = @Content) })
	@GetMapping("/{id}")
	public Usuario readUsuario(
			@Parameter(description = "ID del usuario a localizar", required=true) 
			@PathVariable Long id) {
		System.out.println("-------- readUsuario ");
		return serv.findById(id).orElseThrow(UsuarioNotFoundException::new);
	}

	// @RequestBody Student student significa que un usuario será el cuerpo de la
	// respuesta
	// Devuelve en la cabecera la URL
	@PostMapping
	public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
		Usuario result = this.serv.save(usuario);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
				.toUri();
}
}
