package ru.ilin.restvote.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.ilin.restvote.AuthorizedUser;
import ru.ilin.restvote.to.UserTo;

import static java.util.Objects.requireNonNull;

@Component
public class SecurityUtil {
    private SecurityUtil() {
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        return requireNonNull(safeGet(), "No authorized user found");
    }

    public static int authUserId() {
        UserTo userTo = get().getUserTo();
        return userTo == null ? 0 : userTo.id();
    }
}
