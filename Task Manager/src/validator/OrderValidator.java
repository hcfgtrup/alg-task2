package validator;

import annotations.Validate;
import model.Order;
import java.lang.reflect.Field;

public class OrderValidator {

    public static boolean validate(Order order) {
        if (order == null) {
            return false;
        }

        Field[] fields = Order.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Validate.class)) {
                Validate validate = field.getAnnotation(Validate.class);
                if (validate.required()) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(order);
                        if (value == null) {
                            System.err.println("Validation failed: " + field.getName() + " is null");
                            return false;
                        }
                        if (value instanceof String && ((String) value).trim().isEmpty()) {
                            System.err.println("Validation failed: " + field.getName() + " is empty");
                            return false;
                        }
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}