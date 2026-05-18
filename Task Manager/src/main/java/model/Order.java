package model;

import annotations.NotNull;
import annotations.OrderType;
import annotations.Validate;
import java.util.Objects;
import java.util.UUID;

public class Order {
    @NotNull
    private String id;

    @Validate(required = true)
    private String description;

    @OrderType("ORDINARY")
    private String type;

    private long createdAt;

    public Order(String description, boolean isUrgent) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.type = isUrgent ? "URGENT" : "ORDINARY";
        this.createdAt = System.currentTimeMillis();
    }

    public Order(String id, String description, boolean isUrgent) {
        this.id = id;
        this.description = description;
        this.type = isUrgent ? "URGENT" : "ORDINARY";
        this.createdAt = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public boolean isUrgent() {
        return "URGENT".equals(type);
    }

    @Override
    public String toString() {
        return String.format("Order{id='%s', desc='%s', type=%s}", id, description, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}