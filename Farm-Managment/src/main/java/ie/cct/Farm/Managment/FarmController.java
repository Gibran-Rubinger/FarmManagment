package ie.cct.Farm.Managment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ie.cct.Farm.Managment.Actions.AverageWeight;
import ie.cct.Farm.Managment.Actions.SuccessResponse;
import ie.cct.Farm.Managment.Animals.Animal;
import ie.cct.Farm.Managment.Animals.Chicken;
import ie.cct.Farm.Managment.Animals.Cow;
import ie.cct.Farm.Managment.Animals.Pig;

@RestController
public class FarmController {

	private String cow = "Cow";
	private String pig = "Pig";
	private String chicken = "Chicken";
	private String animalPost = "";

//	declaring a List to storage the animals.
	List<Animal> animals;
	List<Animal> warehouse;
	List<Animal> cows;
	List<Animal> cowsForSale;
	List<Animal> pigs;
	List<Animal> pigsForSale;
	List<Animal> chickens;
	List<Animal> chickensForSale;
	AverageWeight kg = new AverageWeight(0.00);

	public FarmController() {
		animals = new ArrayList<Animal>();
		warehouse = new ArrayList<Animal>();
		cows = new ArrayList<Animal>();
		cowsForSale = new ArrayList<Animal>();
		pigs = new ArrayList<Animal>();
		pigsForSale = new ArrayList<Animal>();
		chickens = new ArrayList<Animal>();
		chickensForSale = new ArrayList<Animal>();
	}

	/*
	 * Here in this method some important steps are happening. To allow the http
	 * interface to access this method in other words, here I building a query
	 * parameter:
	 * 
	 * POST: to allow a new path to the endpoint, I am using it to built the new
	 * URL: http://localhost:8080/add-item
	 * 
	 * Send and Request: Json is use at the body of the request to allow us using
	 * javascrip across the endpoints.
	 */
	@PostMapping("add-animal")
	public SuccessResponse addAnimal(@RequestBody Animal animal) {
		Integer cowIdMaker = 0;
		Integer pigIdMaker = 0;
		Integer chickenIdMaker = 0;
		if (animal.getType().equalsIgnoreCase(cow)) {
			animals.add(new Cow(animal.getType(), animal.getWeight(), animal.setId(cowIdMaker)));
			cows.add(new Cow(animal.getType(), animal.getWeight(), animal.setId(cowIdMaker)));
			animalPost = cow;
			cowIdMaker++;
			return new SuccessResponse("This " + animalPost + " was successfully added to the system.");
		} else if (animal.getType().equalsIgnoreCase(pig)) {
			animals.add(new Pig(animal.getType(), animal.getWeight(), animal.setId(pigIdMaker)));
			pigs.add(new Pig(animal.getType(), animal.getWeight(), animal.setId(pigIdMaker)));
			animalPost = pig;
			pigIdMaker++;
			return new SuccessResponse("This " + animalPost + " was successfully added to the system.");
		} else if (animal.getType().equalsIgnoreCase(chicken)) {
			animals.add(new Chicken(animal.getType(), animal.getWeight(), animal.setId(chickenIdMaker)));
			chickens.add(new Chicken(animal.getType(), animal.getWeight(), animal.setId(chickenIdMaker)));

			animalPost = chicken;
			chickenIdMaker++;
			return new SuccessResponse("This " + animalPost + " was successfully added to the system.");
		} else {
			return new SuccessResponse("Sorry, at moment " + animal.getType() + " is not inplemented in system yet.");
		}
	}

//	@GetMapping("average-weight")
//	public AverageWeight averageWeight() {
//		if (animals.size() == 0) {
//			throw new RuntimeException(" No animals found in the system");
//		}
//		kg = 0.00;
//		for (Animal animal : animals) {
//			kg = animal.getWeight();
//		}
//		kg = kg / animals.size();
//		retunr kg;
//
//	}
	@GetMapping("all-animals")
	public SuccessResponse AllAnimals() {
		return new SuccessResponse("we have  " + animals.size() + "  animals register in the system.");
	}

	// method to verify if the animal has enough weight to be sold.
	@GetMapping("animals-for-sale")
	public SuccessResponse GoodToSell() {

		for (Animal animal : animals) {
			if (animal.getType().equalsIgnoreCase(cow) && animal.getWeight() >= 300.00) {
				for (Animal animalRegistred : warehouse) {
					if (animal.getId() == animalRegistred.getId()) {
						break;
					} else {
						warehouse.add(animal);
						cowsForSale.add(animal);
					}
				}
			} else if (animal.getType().equalsIgnoreCase(pig) && animal.getWeight() >= 100.00) {
				for (Animal animalRegistred : warehouse) {
					if (animal.getId() == animalRegistred.getId()) {
						break;
					} else {
						warehouse.add(animal);
						pigsForSale.add(animal);
					}
				}
			} else if (animal.getType().equalsIgnoreCase(chicken) && animal.getWeight() >= 0.50) {
				for (Animal animalRegistred : warehouse) {
					if (animal.getId() == animalRegistred.getId()) {
						break;
					} else {
						warehouse.add(animal);
						chickensForSale.add(animal);
					}
				}

			}
		}

		Integer cowsFor$ = cowsForSale.size();
		Integer pigsFor$ = pigsForSale.size();
		Integer chickensFor$ = chickensForSale.size();
		return new SuccessResponse("                        ANIMALS FOR SALE:   At moment we have  " + warehouse.size()
				+ "  animals for sale.   COWS - " + cowsFor$ + "   PIGS - " + pigsFor$ + "   CHICKENS - "
				+ chickensFor$);

	}
}
