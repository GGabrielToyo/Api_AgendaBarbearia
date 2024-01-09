package api_bravos.barber.controller;

import api_bravos.barber.domain.agendamentos.*;
import api_bravos.barber.domain.barbeiro.DadosDetalhamentoBarbeiro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    /*
    TODO Data chegando do front e sendo tratada para formato correto.
        ->Fazer a query no banco buscando pela data para saber os horários
          indisponíveis
    */
    @GetMapping("/{data}")
    public void listarHorariosNaData(@PathVariable String data){
        String dataSemFracaoSegundo = data.substring(0, data.length() - 5);
        LocalDateTime localDateTime = LocalDateTime.parse(dataSemFracaoSegundo, DateTimeFormatter.ISO_DATE_TIME);

        System.out.println(localDateTime);
    }


    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoAgendamento dados){
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
