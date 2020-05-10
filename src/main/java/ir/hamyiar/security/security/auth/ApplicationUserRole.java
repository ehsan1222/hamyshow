package ir.hamyiar.security.security.auth;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static ir.hamyiar.security.security.auth.ApplicationPermissions.*;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(USER_READ, USER_WRITE, COMMENT_WRITE, MOVIE_DOWNLOAD)),
    PREMIUM(Sets.newHashSet(USER_READ, USER_WRITE, COMMENT_WRITE, MOVIE_DOWNLOAD, MOVIE_SUGGEST)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, COMMENT_WRITE, MOVIE_DOWNLOAD, MOVIE_SUGGEST, COMMENT_CHANGE,
            MOVIE_UPLOAD, ADMIN_ADD));


    private final Set<ApplicationPermissions> permissions;

    ApplicationUserRole(Set<ApplicationPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<? extends GrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> userPermissions = permissions.stream()
                .map(applicationPermissions -> new SimpleGrantedAuthority(applicationPermissions.name()))
                .collect(Collectors.toSet());

        userPermissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return userPermissions;
    }


}
