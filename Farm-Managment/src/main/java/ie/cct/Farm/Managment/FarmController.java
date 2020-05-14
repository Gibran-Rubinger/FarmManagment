package ie.cct.Farm.Managment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

	private Double averageKgAllFarm = 0.00;
	private Double averageKgAllFarm$ = 0.00;
	private Double averageKgAllCow = 0.00;
	private Double averageKgCow$ = 0.00;
	private Double averageKgAllPig = 0.00;
	private Double averageKgPig$ = 0.00;
	private Double averageKgAllChicken = 0.00;
	private Double averageKgChicken$ = 0.00;

	private Double fullPrice = 0.00;
	private Double prospFullPrice = 0.00;
	private Double cows$ = 0.00;
	private Double prospFullCows$ = 0.00;
	private Double pigs$ = 0.00;
	private Double prospFullPigs$ = 0.00;
	private Double chickens$ = 0.00;
	private Double prospFullChickens$ = 0.00;

//	declaring a List to storage the animals.
	List<Animal> animals;
	Set<Animal> warehouse;
	List<Animal> cows;
	Set<Animal> cowsForSale;
	List<Animal> pigs;
	Set<Animal> pigsForSale;
	List<Animal> chickens;
	Set<Animal> chickensForSale;

	public FarmController() {
		animals = new ArrayList<Animal>();
		warehouse = new HashSet<Animal>();
		cows = new ArrayList<Animal>();
		cowsForSale = new HashSet<Animal>();
		pigs = new ArrayList<Animal>();
		pigsForSale = new HashSet<Animal>();
		chickens = new ArrayList<Animal>();
		chickensForSale = new HashSet<Animal>();
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
	public SuccessResponse AddAnimal(@RequestBody Animal animal) {

		if (animal.getType().equalsIgnoreCase(cow)) {
			animals.add(new Cow(animal.getType(), animal.getWeight(), animal.getId()));
			cows.add(new Cow(animal.getType(), animal.getWeight(), animal.getId()));
			animalPost = cow;

			return new SuccessResponse("This " + animalPost + " was successfully added to the system.");
		} else if (animal.getType().equalsIgnoreCase(pig)) {
			animals.add(new Pig(animal.getType(), animal.getWeight(), animal.getId()));
			pigs.add(new Pig(animal.getType(), animal.getWeight(), animal.getId()));
			animalPost = pig;

			return new SuccessResponse("This " + animalPost + " was successfully added to the system.");
		} else if (animal.getType().equalsIgnoreCase(chicken)) {
			animals.add(new Chicken(animal.getType(), animal.getWeight(), animal.getId()));
			chickens.add(new Chicken(animal.getType(), animal.getWeight(), animal.getId()));
			animalPost = chicken;

			return new SuccessResponse("This " + animalPost + " was successfully added to the system.");
		} else {
			return new SuccessResponse("Sorry, at moment " + animal.getType() + " is not inplemented in system yet.");
		}
	}

	// method to verify if the animal has enough weight to be sold.
	@GetMapping("animals-for-sale")
	public SuccessResponse GoodToSell() {

		for (Animal animal : animals) {
			if (animal.getType().equalsIgnoreCase(cow) && animal.getWeight() >= 300.00) {
				warehouse.add(animal);
				cowsForSale.add(animal);

			} else if (animal.getType().equalsIgnoreCase(pig) && animal.getWeight() >= 100.00) {
				warehouse.add(animal);
				pigsForSale.add(animal);

			} else if (animal.getType().equalsIgnoreCase(chicken) && animal.getWeight() >= 0.50) {
				warehouse.add(animal);
				chickensForSale.add(animal);
			}
		}

		Integer cowsFor$ = cowsForSale.size();
		Integer pigsFor$ = pigsForSale.size();
		Integer chickensFor$ = chickensForSale.size();
		return new SuccessResponse("                        ANIMALS FOR SALE:   At moment we have  " + warehouse.size()
				+ "  animals for sale.   COWS - " + cowsFor$ + "   PIGS - " + pigsFor$ + "   CHICKENS - "
				+ chickensFor$);

	}

	@GetMapping("average-weight")
	public SuccessResponse AverageWeight() {

		if (animals.size() == 0) {
			throw new RuntimeException(" No animals found in the system");
		} else {
			for (Animal animal : animals) {
				averageKgAllFarm = animal.getWeight();
			}
			averageKgAllFarm = averageKgAllFarm / animals.size();
//_____________________________________________________________________________
			for (Animal animal : warehouse) {
				averageKgAllFarm$ = animal.getWeight();
			}
			averageKgAllFarm$ = averageKgAllFarm$ / warehouse.size();
//_____________________________________________________________________________
			for (Animal animal : cows) {
				averageKgAllCow = animal.getWeight();
			}
			averageKgAllCow = averageKgAllCow / cows.size();
			// ______________________________________________________________________
			for (Animal animal : cowsForSale) {
				averageKgCow$ = animal.getWeight();
			}
			averageKgCow$ = averageKgCow$ / cowsForSale.size();
//_____________________________________________________________________________
			for (Animal animal : pigs) {
				averageKgAllPig = animal.getWeight();
			}
			averageKgAllPig = averageKgAllPig / pigs.size();
			// ______________________________________________________________________
			for (Animal animal : pigsForSale) {
				averageKgPig$ = animal.getWeight();
			}
			averageKgPig$ = averageKgPig$ / pigsForSale.size();
//_____________________________________________________________________________
			for (Animal animal : chickens) {
				averageKgAllChicken = animal.getWeight();
			}
			averageKgAllChicken = averageKgAllChicken / chickens.size();
			// ______________________________________________________________________
			for (Animal animal : chickensForSale) {
				averageKgChicken$ = animal.getWeight();
			}
			averageKgChicken$ = averageKgChicken$ / chickensForSale.size();
//_____________________________________________________________________________

			return new SuccessResponse("   AVERAGE WEIGHT:    - All Animals:  " + averageKgAllFarm + " Kg.   COWS:  "
					+ averageKgAllCow + " Kg.   PIGS:  " + averageKgAllPig + " Kg.   CHICKENS:  " + averageKgAllChicken
					+ " Kg."
					+ "                                                                                                         - ALL ANIMALS FOR SALE:  "
					+ averageKgAllFarm$ + " Kg.   COWS:  " + averageKgCow$ + " Kg.   PIGS:  " + averageKgPig$
					+ "   CHICKENS:  " + averageKgChicken$ + " Kg.");
		}
	}

	@GetMapping("current-price")
	public SuccessResponse CurrentPrice() {

		if (animals.size() == 0) {
			throw new RuntimeException(" No animals found in the system");
		} else {
			for (Animal animal : animals) {
				fullPrice = animal.getPrice();
			}
			// _____________________________________________________________________________
			for (Animal animal : warehouse) {
				prospFullPrice = animal.getPrice();
			}
			// _____________________________________________________________________________
			for (Animal animal : cows) {
				averageKgAllCow = animal.getWeight();
			}
			averageKgAllCow = averageKgAllCow / cows.size();
			// ______________________________________________________________________
			for (Animal animal : cowsForSale) {
				averageKgCow$ = animal.getWeight();
			}
			averageKgCow$ = averageKgCow$ / cowsForSale.size();
			// _____________________________________________________________________________
			for (Animal animal : pigs) {
				averageKgAllPig = animal.getWeight();
			}
			averageKgAllPig = averageKgAllPig / pigs.size();
			// ______________________________________________________________________
			for (Animal animal : pigsForSale) {
				averageKgPig$ = animal.getWeight();
			}
			averageKgPig$ = averageKgPig$ / pigsForSale.size();
			// _____________________________________________________________________________
			for (Animal animal : chickens) {
				averageKgAllChicken = animal.getWeight();
			}
			averageKgAllChicken = averageKgAllChicken / chickens.size();
			// ______________________________________________________________________
			for (Animal animal : chickensForSale) {
				averageKgChicken$ = animal.getWeight();
			}
			averageKgChicken$ = averageKgChicken$ / chickensForSale.size();
			// _____________________________________________________________________________

			return new SuccessResponse("   AVERAGE WEIGHT:    - All Animals:  " + averageKgAllFarm + "   COWS:  "
					+ averageKgAllCow + "   PIGS:  " + averageKgAllPig + "   CHICKENS:  " + averageKgAllChicken
					+ "                                                                                            - ALL ANIMALS FOR SALE:  "
					+ averageKgAllFarm$ + "   COWS:  " + averageKgCow$ + "   PIGS:  " + averageKgPig$ + "   CHICKENS:  "
					+ averageKgChicken$);
		}

	}

	@GetMapping("all-animals")
	public SuccessResponse AllAnimals() {
		System.out.println(
				"\n\n______________________________________________________________________________________________________________\n\n");
		System.out.println("\n\n\n\n\n\n\n                                           STARTING SESSION  ");
		System.out.println(
				"\n\n______________________________________________________________________________________________________________\n\n");

		for (Animal animal : animals) {
			System.out.println(animal);
		}
		System.out.println(
				"\n\n\n\n\n\n\n\n______________________________________________________________________________________________________________\n");
		System.out
				.println("                                         AT MOMENT:   " + animals.size() + "  ANIMALS ADDED");
		System.out.println("\n                            COWS:   " + cows.size() + "               PIGS:  "
				+ pigs.size() + "               CHICKENS:  " + chickens.size());
		System.out.println(
				"\n______________________________________________________________________________________________________________\n\n");

		return new SuccessResponse("we have  " + animals.size() + "  animals register in the system.");
	}

}
