package api_bravos.barber.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);

    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
           select u.ativo from Usuario u where u.id = :idUsuario
           """)
    boolean findAtivoById(Long idUsuario);
}
