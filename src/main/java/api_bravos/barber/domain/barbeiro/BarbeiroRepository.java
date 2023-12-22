package api_bravos.barber.domain.barbeiro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    Page<Barbeiro> findAllByAtivoTrue(Pageable paginacao);

    @Query("select b.ativo from Barbeiro b where b.id = :idBarbeiro")
    Boolean findAtivoById(Long idBarbeiro);
}
