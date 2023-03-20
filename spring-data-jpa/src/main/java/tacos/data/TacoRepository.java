package tacos.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tacos.TacoOrder;
import tacos.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {



}
