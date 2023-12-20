package api_bravos.barber.controller;

import api_bravos.barber.domain.barbeiro.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/barbeiro")
public class BarbeiroController {

    @Autowired
    BarbeiroRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroBarbeiro dados, UriComponentsBuilder uriBuilder) {
        var barbeiro = new Barbeiro(dados);
        repository.save(barbeiro);
        var uri = uriBuilder.path("/barbeiro/{id}").buildAndExpand(barbeiro.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoBarbeiro(barbeiro));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoBarbeiro>> listar(Pageable paginacao) {
        var lista = repository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoBarbeiro::new);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var barbeiro = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoBarbeiro(barbeiro));
    }

    @PutMapping
    @Transactional
    public ResponseEntity editar (@RequestBody @Valid DadosEditarBarbeiro dados) {
        var barbeiro = repository.getReferenceById(dados.id());
        barbeiro.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoBarbeiro(barbeiro));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var barbeiro = repository.getReferenceById(id);
        barbeiro.excluir();

        return ResponseEntity.noContent().build();
    }

}
