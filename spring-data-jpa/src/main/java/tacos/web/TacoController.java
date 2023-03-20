package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

//import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.TacoOrder;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/taco")
@SessionAttributes("order")
public class TacoController {

	private IngredientRepository ingredientRepo;
	private TacoRepository tacoRepo;

	@Autowired
	public TacoController(TacoRepository tacoRepo, IngredientRepository ingredientRepo) {
		this.tacoRepo = tacoRepo;
		this.ingredientRepo = ingredientRepo;
	}

	@ModelAttribute(name = "order")
	public TacoOrder order() {
		return new TacoOrder();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	// Managed By JdbcIngredientRepository
	public String showDesignForm(Model model) {

		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		
		return "tacoForm";
	}

	@PostMapping
	// Managed By JdbcTacoRepository
	public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder order) {
		
		if (errors.hasErrors()) {
			System.out.println(errors);
			return "tacoForm";
		}

		Taco saved = tacoRepo.save(taco);
		order.addTaco(taco);

		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());

	}

}