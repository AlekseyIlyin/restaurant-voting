package ru.ilin.restvote.urils;

import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    public static int authUserId() {
        return 100000;
    }
}
