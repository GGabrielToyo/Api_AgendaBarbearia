package api_bravos.barber.controller;

import api_bravos.barber.domain.agendamentos.AgendaService;
import api_bravos.barber.domain.agendamentos.DadosAgendamento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendaService agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamento dados){
        var dto = agenda.agendar(dados);

        return ResponseEntity.ok(dto);
    }

}
