package api_bravos.barber.domain.agendamentos.validacoes.agendamento;

import api_bravos.barber.domain.ValidacaoException;
import api_bravos.barber.domain.agendamentos.DadosAgendamento;
import api_bravos.barber.domain.barbeiro.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorBarbeiroAtivo")
public class ValidadorBarbeiroAtivo implements ValidadorAgendamento{

    @Autowired
    private BarbeiroRepository repositoryBarbeiro;

    public void validar(DadosAgendamento dados){
        if(dados.idBarbeiro() == null) {
            return;
        };

        var barbeiroAtivo = repositoryBarbeiro.findAtivoById(dados.idBarbeiro());

        if(!barbeiroAtivo){
            throw new ValidacaoException("Agendamento não pode ser feito com barbeiro inativo!");
        }

    }

}
