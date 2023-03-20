package tacos.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.Order;
import tacos.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {
	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;

	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		this.orderInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order").usingGeneratedKeyColumns("id");
		this.orderTacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order_Tacos");
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public Order save(Order order) {
		// Assembling Order Object
		order.setPlacedAt(new Date());
		long orderId = saveOrderDetails(order);
		order.setId(orderId);

		List<Taco> tacos = order.getTacos();
		for (Taco taco : tacos) {
			linkOrderToTaco(taco, orderId);
		}

		return order;
	}

	public long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		Map<String, Object> dbEntry = objectMapper.convertValue(order, Map.class);
		System.out.println(order);
		dbEntry.put("placedAt", order.getPlacedAt());

		long orderId = orderInserter.executeAndReturnKey(dbEntry).longValue();

		return orderId; 
	}

	public void linkOrderToTaco(Taco taco, long orderId) {
		Map<String, Object> dbEntry = new HashMap<>();
		System.out.println(orderId);
		dbEntry.put("tacoOrder", orderId);
		dbEntry.put("taco", taco.getId());
		orderTacoInserter.execute(dbEntry);
	}

}
