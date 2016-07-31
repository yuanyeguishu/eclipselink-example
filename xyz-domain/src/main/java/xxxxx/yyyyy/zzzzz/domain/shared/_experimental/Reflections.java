package xxxxx.yyyyy.zzzzz.domain.shared._experimental;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class Reflections {

    public <T> T get(Field field, Object object) {
        boolean accessible = false;
        try {
            //if (Modifier.isPrivate(field.getModifiers())) {
            accessible = field.isAccessible();
            if (!accessible) {
                AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                    field.setAccessible(true);
                    return null;
                });
            }
            return (T) field.get(object);
        } catch (ReflectiveOperationException | SecurityException cause) {
            throw new RuntimeException(cause);
        } finally {
            if (!accessible) {
                field.setAccessible(false);
            }
        }
    }
}
