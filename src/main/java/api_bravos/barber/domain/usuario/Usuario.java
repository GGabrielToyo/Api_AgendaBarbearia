package api_bravos.barber.domain.usuario;

import api_bravos.barber.infra.security.TokenService;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String login;
    private String senha;
    private boolean ativo;
    private LocalDateTime nascimento;

    public Usuario(DadosCadastroUsuario dados){
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.login = dados.login();
        this.nascimento = dados.nascimento();
        this.senha = encriptografarSenha(dados.senha());
        this.ativo = true;
    }

    private String encriptografarSenha(String senha) {
        String gensalt = BCrypt.gensalt();
        String senhaEncriptografada = BCrypt.hashpw(senha, gensalt);

        return  senhaEncriptografada;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void atualizarInformacoes(DadosEditarUsuario dados) {
        if(dados.nome() != null && dados.nome() != this.nome) {
            this.nome = dados.nome();
        }
        if(dados.nascimento() != null && dados.nascimento() != this.nascimento) {
            this.nascimento = dados.nascimento();
        }
        if(dados.telefone() != null && dados.telefone() != this.telefone) {
            this.telefone = dados.telefone();
        }
        if(dados.login() != null && dados.login() != this.login) {
            this.login = dados.login();
        }
        if(dados.senha() != null && !this.senha.equals(encriptografarSenha(dados.senha()))) {
            this.senha = encriptografarSenha(dados.senha());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
