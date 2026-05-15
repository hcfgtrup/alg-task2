package test;

import model.Order;
import org.junit.jupiter.api.Test;
import validator.OrderValidator;
import static org.junit.jupiter.api.Assertions.*;

class OrderValidatorTest {

    @Test
    void validOrderShouldPass() {
        Order order = new Order("Test order", false);
        assertTrue(OrderValidator.validate(order));
    }

    @Test
    void nullOrderShouldFail() {
        assertFalse(OrderValidator.validate(null));
    }

    @Test
    void orderWithNullIdShouldFail() throws Exception {
        Order order = new Order("test", false);
        java.lang.reflect.Field idField = Order.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(order, null);
        assertFalse(OrderValidator.validate(order));
    }

    @Test
    void orderWithEmptyDescriptionShouldFail() throws Exception {
        Order order = new Order("", false);
        java.lang.reflect.Field descField = Order.class.getDeclaredField("description");
        descField.setAccessible(true);
        descField.set(order, "");
        assertFalse(OrderValidator.validate(order));
    }
}