package Discipline.CineHub.dto;

import Discipline.CineHub.entity.AuthorityEntity;
import Discipline.CineHub.entity.UserEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserResponseDto {
    private String username;
    private String address;
    private String provider;
    private String email;
    private String phonenumber;
    private String role;
    private List<AuthorityInfo> authorities = new ArrayList<>();
    private boolean enabled;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;

    @Data
    public static class AuthorityInfo {
        private String authority;

        public AuthorityInfo(String authority) {
            this.authority = authority;
        }
    }

    public UserResponseDto(UserEntity entity) {
        this.username = entity.getUsername();
        this.address = entity.getAddress();
        this.provider = entity.getProvider();
        this.email = entity.getEmail();
        this.phonenumber = entity.getPhonenumber();
        this.role = entity.getRole();
        Set<AuthorityEntity> authorityEntities = entity.getAuthorities().stream()
                .map(grantedAuthority -> new AuthorityEntity(grantedAuthority.getAuthority()))
                .collect(Collectors.toSet());
        setAuthorities(authorityEntities);
        this.enabled = entity.isEnabled();
        this.accountNonLocked = entity.isAccountNonLocked();
        this.accountNonExpired = entity.isAccountNonExpired();
        this.credentialsNonExpired = entity.isCredentialsNonExpired();
    }

    public void setAuthorities(Set<AuthorityEntity> authorityEntities) {
        this.authorities.clear();
        for (AuthorityEntity authorityEntity : authorityEntities) {
            if (authorityEntity.getAuthorityName() != null && !authorityEntity.getAuthorityName().isEmpty()) {
                this.authorities.add(new AuthorityInfo(authorityEntity.getAuthorityName()));
            }
        }
    }
}