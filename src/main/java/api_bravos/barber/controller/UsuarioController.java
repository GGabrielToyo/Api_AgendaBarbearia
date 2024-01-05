package api_bravos.barber.controller;

import api_bravos.barber.domain.usuario.*;
import api_bravos.barber.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(dados);
        repository.save(usuario);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        System.out.println(dados);
       return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listarAtivos(Pageable paginacao) {
        var lista = repository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoUsuario::new);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listarTodos(Pageable paginacao) {
        var lista = repository.findAll(paginacao).map(DadosDetalhamentoUsuario::new);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/perfil")
    public ResponseEntity detalhar(HttpServletRequest request) {
        var login = usuarioService.getLoginUsuario(request.getHeader("Authorization"));
        var usuario = repository.findUsuarioByLogin(login);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PatchMapping
    @Transactional
    public ResponseEntity editar (@RequestBody @Valid DadosEditarUsuario dados) {
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        usuario.excluir();

        return ResponseEntity.noContent().build();
    }

}
