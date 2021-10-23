package advent_of_code.main.a2020;

import java.util.ArrayList;
import java.util.List;

public class Food {
	List<String> ingredients = new ArrayList<>();
	List<String> allergenes = new ArrayList<>();
    Integer id;
	

	

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getAllergenes() {
		return allergenes;
	}

	public void setAllergenes(List<String> allergenes) {
		this.allergenes = allergenes;
	}

	

	public Food(int id ,List<String> ingredients, List<String> allergenes) {
		super();
		this.ingredients = ingredients;
		this.allergenes = allergenes;
		this.id = id;
	}

	public Food() {
		super();
	}
}
