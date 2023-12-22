package api_bravos.barber.domain.agendamentos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByBarbeiroIdAndDataAndMotivoCancelamentoIsNull(Long idBarbeiro, LocalDateTime data);
}
