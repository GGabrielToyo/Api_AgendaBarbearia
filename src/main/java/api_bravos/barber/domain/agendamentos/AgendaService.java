package api_bravos.barber.domain.agendamentos;

import api_bravos.barber.domain.ValidacaoException;
import api_bravos.barber.domain.agendamentos.validacoes.agendamento.ValidadorAgendamento;
import api_bravos.barber.domain.barbeiro.BarbeiroRepository;
import api_bravos.barber.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private AgendamentoRepository repositoryAgendamento;

    @Autowired
    private BarbeiroRepository repositoryBarbeiro;

    @Autowired
    private UsuarioRepository repositoryUsuario;

    @Autowired
    private List<ValidadorAgendamento> validadores;

    public DadosDetalhamentoAgendamento agendar(DadosAgendamento dados) {
        if(!repositoryUsuario.existsById(dados.idUsuario())){
            throw new ValidacaoException("Id do usuario informado não encontrado na base de dados!");
        }
        if(!repositoryBarbeiro.existsById(dados.idBarbeiro())){
            throw new ValidacaoException("Id do barbeiro não encontrado na base de dados!");
        }

        validadores.forEach(v -> v.validar(dados));

        var usuario = repositoryUsuario.getReferenceById(dados.idUsuario());

        var barbeiro = repositoryBarbeiro.getReferenceById(dados.idBarbeiro());

        var agendamento = new Agendamento(null, barbeiro, usuario, dados.data(), null);

        repositoryAgendamento.save(agendamento);

        return new DadosDetalhamentoAgendamento(agendamento);
    }

}