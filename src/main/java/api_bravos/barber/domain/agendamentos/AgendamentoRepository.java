package api_bravos.barber.domain.agendamentos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByBarbeiroIdAndDataAndMotivoCancelamentoIsNull(Long idBarbeiro, LocalDateTime data);

    boolean existsByUsuarioIdAndDataAndMotivoCancelamentoIsNull(Long idUsuario, LocalDateTime data);


    @Query("SELECT a FROM Agendamento a WHERE DATE(a.data) = :data")
    List<DadosDetalhamentoAgendamento> findAllByData(@Param("data") LocalDate data);

}
