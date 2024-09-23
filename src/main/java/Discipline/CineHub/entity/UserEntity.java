package Discipline.CineHub.entity;

import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.entity.expert.ExpertComment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"Actor"})
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @JsonIgnore
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50)
    private String username;

    @JsonIgnore
    @Column
    private String password;

    @Column(name = "address", length = 100)
    private String address;

    @Column
    private String provider;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column
    private String phonenumber;

    @Column
    private String role;

    @JsonManagedReference
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    ExpertBoard expertBoard;

    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACTOR_ID")
    @JsonBackReference
    @JsonIgnore
    @ToString.Exclude //circular referencing 이슈 방지
    private Actor actor;

    // ManyToMany 유지 시 지연 로딩, 로그인 시 LazyInitializationException 발생
    // FetchType.EAGER로 즉시 로딩으로 전환
    // DB I/O 부담 늘어서 성능 저하 일어나기 때문에 대용량 트래픽 발생 시에는 좀 고민해봐야할듯?
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<AuthorityEntity> authorities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnore
    @ToString.Exclude //circular referencing 이슈 방지
    List<ExpertComment> expertComment;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> roles = new ArrayList<>();
        for (AuthorityEntity authority : authorities) {
            if (authority.getAuthorityName() != null && !authority.getAuthorityName().isEmpty()) {
                roles.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
            }
        }
        return roles;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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


}
