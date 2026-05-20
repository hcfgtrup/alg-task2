import model.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void orderConstructorShouldSetFieldsCorrectly() {
        Order order = new Order("Test description", true);

        assertNotNull(order.getId());
        assertEquals("Test description", order.getDescription());
        assertTrue(order.isUrgent());
        assertEquals("URGENT", order.getType());
        assertTrue(order.getCreatedAt() > 0);
    }

    @Test
    void orderWithIdConstructorShouldWork() {
        String customId = "custom-123";
        Order order = new Order(customId, "Description", false);

        assertEquals(customId, order.getId());
        assertEquals("Description", order.getDescription());
        assertFalse(order.isUrgent());
        assertEquals("ORDINARY", order.getType());
    }

    @Test
    void testToString() {
        Order order = new Order("Test", true);
        String str = order.toString();

        assertNotNull(str);
        assertTrue(str.contains("Order{"));
        assertTrue(str.contains("type=URGENT"));
    }

    @Test
    void testEqualsAndHashCode() {
        Order order1 = new Order("desc1", false);
        Order order2 = new Order(order1.getId(), "desc2", false);

        assertEquals(order1, order2);
        assertEquals(order1.hashCode(), order2.hashCode());

        Order order3 = new Order("desc3", false);
        assertNotEquals(order1, order3);
    }
}