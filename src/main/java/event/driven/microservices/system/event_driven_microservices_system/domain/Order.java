package event.driven.microservices.system.event_driven_microservices_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private UUID id;
    private String customerName;
    private String productName;
    private int quantity;
    private String status; // e.g., PENDING, COMPLETED

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public Order(UUID id, String customerName, String productName, int quantity, String status) {
        this.id = id;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.status = status;
    }

    public Order() {
    }

}