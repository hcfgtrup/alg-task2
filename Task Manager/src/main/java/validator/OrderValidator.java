package validator;

import annotations.NotNull;
import annotations.OrderType;
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
            field.setAccessible(true);
            try {
                Object value = field.get(order);

                if (field.isAnnotationPresent(NotNull.class)) {
                    if (value == null) {
                        System.err.println("Validation failed: " + field.getName() + " is null");
                        return false;
                    }
                }

                if (field.isAnnotationPresent(Validate.class)) {
                    Validate validate = field.getAnnotation(Validate.class);
                    if (validate.required()) {
                        if (value == null) {
                            System.err.println("Validation failed: " + field.getName() + " is null");
                            return false;
                        }
                        if (value instanceof String && ((String) value).trim().isEmpty()) {
                            System.err.println("Validation failed: " + field.getName() + " is empty");
                            return false;
                        }
                    }
                }

                if (field.isAnnotationPresent(OrderType.class) && "type".equals(field.getName())) {
                    String actualType = (String) value;

                    if (actualType == null || (!actualType.equals("URGENT") && !actualType.equals("ORDINARY"))) {
                        System.err.println("Validation failed: type must be URGENT or ORDINARY, but was: " + actualType);
                        return false;
                    }
                }

            } catch (IllegalAccessException e) {
                return false;
            }
        }
        return true;
    }
}