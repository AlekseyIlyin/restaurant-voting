package ru.ilin.restvote.urils;

import ru.ilin.restvote.model.Role;
import ru.ilin.restvote.model.User;
import ru.ilin.restvote.to.UserTo;

public class UserUtil {
    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getPassword(), Role.USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setId(userTo.getId());
        user.setName(userTo.getName());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
