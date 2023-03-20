package tacos.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

	List<TacoOrder> findByDeliveryZip(String deliveryZip);

	List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

	List<TacoOrder> findByDeliveryCityOrderByDeliveryCity(String city);

//	@Query("from Taco_Order o where o.deliveryName='Test'")
//	List<Order> readOrdersDeliveredInSeattle();

}
