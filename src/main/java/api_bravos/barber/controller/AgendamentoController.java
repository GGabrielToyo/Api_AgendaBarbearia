package api_bravos.barber.controller;

import api_bravos.barber.domain.agendamentos.*;
import api_bravos.barber.domain.barbeiro.Barbeiro;
import api_bravos.barber.domain.barbeiro.DadosDetalhamentoBarbeiro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendaService agenda;

    @Autowired
    private AgendamentoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamento dados){
        var dto = agenda.agendar(dados);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoAgendamento>> listar(Pageable paginacao) {
        var lista = repository.findAll(paginacao).map(DadosDetalhamentoAgendamento::new);
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{data}")
    public ResponseEntity<List<DadosDetalhamentoAgendamento>> listarHorariosNaData(@PathVariable String data){

        return ResponseEntity.ok(agenda.listarHorariosIndisponiveisPorBarbeiro(data));
    }


    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoAgendamento dados){
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
