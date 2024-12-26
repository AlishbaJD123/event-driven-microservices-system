package event.driven.microservices.system.event_driven_microservices_system.application;

import event.driven.microservices.system.event_driven_microservices_system.domain.Order;
import event.driven.microservices.system.event_driven_microservices_system.domain.OrderRepository;
import event.driven.microservices.system.event_driven_microservices_system.domain.OrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServicePort {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void placeOrder(Order order) {
        order.setStatus("PENDING");
        orderRepository.saveOrder(order);
        kafkaTemplate.send("order-placed", "Order ID: " + order.getId());
    }
}