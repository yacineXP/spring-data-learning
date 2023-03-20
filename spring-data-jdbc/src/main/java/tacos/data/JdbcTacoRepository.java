package tacos.data;

import java.util.Date;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import tacos.Taco;

@Slf4j
@Repository
public class JdbcTacoRepository implements TacoRepository {

	private JdbcTemplate jdbc;
	private SimpleJdbcInsert tacoInserter;
	private ObjectMapper objectMapper;

	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.tacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco").usingGeneratedKeyColumns("id");
		this.objectMapper = new ObjectMapper();

	}

	@Override
	public Taco save(Taco taco) {
		taco.setCreatedAt(new Date());
		Long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		for (String ingredient : taco.getIngredients()) {
			linkTacoToIngredient(ingredient, tacoId);
		}
		return taco;
	}

	private long saveTacoInfo(Taco taco) {
		Map<String, Object> dbEntry = objectMapper.convertValue(taco, Map.class);
		dbEntry.put("createdAt", taco.getCreatedAt()); // To be stored as String

		long tacoId = tacoInserter.executeAndReturnKey(dbEntry).longValue();

		return tacoId;
	}

	private void linkTacoToIngredient(String ingredientId, long tacoId) {
		jdbc.update("insert into Taco_Ingredients (taco, ingredient) " + "values (?, ?)", tacoId, ingredientId);
	}

}
