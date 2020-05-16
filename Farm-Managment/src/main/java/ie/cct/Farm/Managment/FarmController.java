package ie.cct.Farm.Managment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.cct.Farm.Managment.Actions.NotFoundException;
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
	private Double pigs$ = 0.00;
	private Double chickens$ = 0.00;

	private Double cows$P = 0.00;
	private Double pigs$P = 0.00;
	private Double chickens$P = 0.00;

	private Double cows$cP = 0.00;
	private Double pigs$cP = 0.00;
	private Double chickens$cP = 0.00;

	private Double percent = 0.00;
	private Double totalP = 0.00;
	private Double totalcP = 0.00;

	private Double global = 0.00;
	private Double globalC = 0.00;

	private Double customPriceCow = 0.00;
	private Double customPricePig = 0.00;
	private Double customPriceChicken = 0.00;
//	declaring a List to storage the animals.
	List<Animal> animals;
	List<Animal> warehouse;
	Set<Animal> cows;
	Set<Animal> cowsForSale;
	Set<Animal> pigs;
	Set<Animal> pigsForSale;
	Set<Animal> chickens;
	Set<Animal> chickensForSale;

	public FarmController() {
		animals = new ArrayList<Animal>();
		warehouse = new ArrayList<Animal>();
		cows = new HashSet<Animal>();
		cowsForSale = new HashSet<Animal>();
		pigs = new HashSet<Animal>();
		pigsForSale = new HashSet<Animal>();
		chickens = new HashSet<Animal>();
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
		return new SuccessResponse("           ANIMALS FOR SALE:   At moment we have  " + warehouse.size()
				+ "  animals for sale.   COWS - " + cowsFor$ + "   PIGS - " + pigsFor$ + "   CHICKENS - "
				+ chickensFor$);

	}

	@GetMapping("average-weight")
	public SuccessResponse AverageWeight() {

		if (animals.size() == 0 && warehouse.size() ==0) {
			throw new NotFoundException(" No animals found in the system");
		} else {
			for (Animal animal : animals) {
				averageKgAllFarm += animal.getWeight();
			}
			averageKgAllFarm = averageKgAllFarm / animals.size();
//_____________________________________________________________________________
			for (Animal animal : warehouse) {
				averageKgAllFarm$ += animal.getWeight();
			}
			averageKgAllFarm$ = averageKgAllFarm$ / warehouse.size();
//_____________________________________________________________________________
			for (Animal animal : cows) {
				averageKgAllCow += animal.getWeight();
			}
			averageKgAllCow = averageKgAllCow / cows.size();
			// ______________________________________________________________________
			for (Animal animal : cowsForSale) {
				averageKgCow$ += animal.getWeight();
			}
			averageKgCow$ = averageKgCow$ / cowsForSale.size();
//_____________________________________________________________________________
			for (Animal animal : pigs) {
				averageKgAllPig += animal.getWeight();
			}
			averageKgAllPig = averageKgAllPig / pigs.size();
			// ______________________________________________________________________
			for (Animal animal : pigsForSale) {
				averageKgPig$ += animal.getWeight();
			}
			averageKgPig$ = averageKgPig$ / pigsForSale.size();
//_____________________________________________________________________________
			for (Animal animal : chickens) {
				averageKgAllChicken += animal.getWeight();
			}
			averageKgAllChicken = averageKgAllChicken / chickens.size();
			// ______________________________________________________________________
			for (Animal animal : chickensForSale) {
				averageKgChicken$ += animal.getWeight();
			}
			averageKgChicken$ = averageKgChicken$ / chickensForSale.size();
//_____________________________________________________________________________

			return new SuccessResponse("   AVERAGE WEIGHT:    - All Animals:  " + averageKgAllFarm + " Kg.   COWS:  "
					+ averageKgAllCow + " Kg.   PIGS:  " + averageKgAllPig + " Kg.   CHICKENS:  " + averageKgAllChicken
					+ " Kg.      ALL ANIMALS FOR SALE:  " + averageKgAllFarm$ + " Kg.   COWS:  " + averageKgCow$
					+ " Kg.   PIGS:  " + averageKgPig$ + "   CHICKENS:  " + averageKgChicken$ + " Kg.");
		}
	}

	@GetMapping("current-price")
	public SuccessResponse CurrentPrice() {
//___________________________________________________________________________
//		Prospect price when the all cow have got the over 299Kg per animal.
		if (animals.size() == 0) {
			throw new NotFoundException(" No animals found in the system");
		} else if (cows.size() == 0) {
			cows$P = 0.00;
		} else {
			cows$P = 500.00;

			for (Animal animal : cows) {
				animal.setPrice(cows$P);
			}
			cows$P = cows$P * cows.size();
		}

		// ______________________________________________________________________
//		price for all cow over 299Kg per animal.
		if (cowsForSale.size() == 0) {
			cows$ = 0.00;
		} else {
			cows$ = 500.00;
			for (Animal animal : cowsForSale) {
				animal.setPrice(cows$);
			}
			cows$ = cows$ * cowsForSale.size();
		}

		// _____________________________________________________________________________
//		Prospect price when the all pigs have got the over 99.99Kg per animal.

		if (pigs.size() == 0) {
			pigs$P = 0.00;
		} else {
			pigs$P = 250.00;
			for (Animal animal : pigs) {
				animal.setPrice(pigs$P);
			}
			pigs$P = pigs$P * pigs.size();
		}

		// ______________________________________________________________________
//		price for all pigs over 99.99Kg per animal.
		if (pigsForSale.size() == 0) {
			pigs$ = 0.00;
		} else {
			pigs$ = 250.00;
			for (Animal animal : pigsForSale) {
				animal.setPrice(pigs$);
			}
			pigs$ = pigs$ * pigsForSale.size();
		}

		// _____________________________________________________________________________
//		Prospect price when the all chickens have got the over 0.499Kg per animal.
		if (chickens.size() == 0) {
			chickens$P = 0.00;
		} else {
			chickens$P = 5.00;
			for (Animal animal : chickens) {
				animal.setPrice(chickens$P);
			}
			chickens$P = chickens$P * chickens.size();
		}

		// ______________________________________________________________________
//		price for all chickens over 0.499Kg per animal.

		if (chickensForSale.size() == 0) {
			chickens$ = 0.00;
		} else {
			chickens$ = 5.00;
			for (Animal animal : chickensForSale) {
				animal.setPrice(chickens$);
			}
			chickens$ = chickens$ * chickensForSale.size();
		}

		// _____________________________________________________________________________
//      generate the full farm price able to sell right now   and full farm prospect price.
		prospFullPrice = chickens$P + pigs$P + cows$P;
		fullPrice = chickens$ + pigs$ + cows$;
		totalP = prospFullPrice - fullPrice;
		percent = (totalP / fullPrice) * 100;
		global = fullPrice + totalP;

		// _____________________________________________________________________________
		return new SuccessResponse("   FULL PRICE:    - All Animals:  €" + fullPrice + "   COWS:  €" + cows$
				+ "   PIGS:  €" + pigs$ + "   CHICKENS:  €" + chickens$
				+ "                                                                                            PROSPECTIVE PRICE  - when the  other animals have got the correct weight    "
				+ "   -  TOTAL PROSPECTIVE SALE:  €" + totalP + " THIS IS " + percent
				+ " % MORE.              ON TOTAL: " + global + "      COWS:  €" + cows$P + "   PIGS:  €" + pigs$P
				+ "   CHICKENS:  €" + chickens$P);
	}

	@GetMapping("custom-price")
	public SuccessResponse CustomPrice(@RequestParam(required = true) Double cowPrice,
			@RequestParam(required = true) Double pigPrice, @RequestParam(required = true) Double chickenPrice) {
		// ___________________________________________________________________________

//		Prospect  custom price when the all cow have got the over 299Kg per animal.		
		if (animals.size() == 0) {
			throw new NotFoundException(" No animals found in the system");
		} else if (cows.size() == 0) {
			cows$cP = 0.00;
		} else {
			cows$cP = cowPrice;
			for (Animal animal : cows) {
				animal.setPrice(0.00);
			}
			for (Animal animal : cows) {
				animal.setPrice(cows$cP);
			}
			cows$cP = cows$cP * cows.size();
		}

		// ______________________________________________________________________
//		custom price for all cow over 299Kg per animal.
		if (cowsForSale.size() == 0) {
			cowPrice = 0.00;
		} else {
			for (Animal animal : cowsForSale) {
				animal.setPrice(0.00);
			}
			for (Animal animal : cowsForSale) {
				animal.setPrice(cowPrice);
			}
			cowPrice = cowPrice * cowsForSale.size();
		}

		// _____________________________________________________________________________
//		Prospect custom price when the all pigs have got the over 99.99Kg per animal.

		if (pigs.size() == 0) {
			pigPrice = 0.00;
		} else {
			pigs$cP = pigPrice;
			for (Animal animal : pigs) {
				animal.setPrice(0.00);
			}
			for (Animal animal : pigs) {
				animal.setPrice(pigs$cP);
			}
			pigs$cP = pigs$cP * pigs.size();
		}

		// ______________________________________________________________________
//		custom price for all pigs over 99.99Kg per animal.
		if (pigsForSale.size() == 0) {
			pigPrice = 0.00;
		} else {
			for (Animal animal : pigsForSale) {
				animal.setPrice(0.00);
			}
			for (Animal animal : pigsForSale) {
				animal.setPrice(pigPrice);
			}
			pigPrice = pigPrice * pigsForSale.size();
		}

		// _____________________________________________________________________________
//		Prospect custom price when the all chickens have got the over 0.499Kg per animal.
		if (chickens.size() == 0) {
			chickenPrice = 0.00;
		} else {
			chickens$cP = chickenPrice;
			for (Animal animal : chickens) {
				animal.setPrice(0.00);
			}
			for (Animal animal : chickens) {
				animal.setPrice(chickens$cP);
			}
			chickens$cP = chickens$cP * chickens.size();
		}

		// ______________________________________________________________________
//		custom price for all chickens over 0.499Kg per animal.

		if (chickensForSale.size() == 0) {
			chickenPrice = 0.00;
		} else {
			for (Animal animal : chickensForSale) {
				animal.setPrice(0.00);
			}
			for (Animal animal : chickensForSale) {
				animal.setPrice(chickenPrice);
			}
			chickenPrice = chickenPrice * chickensForSale.size();
		}

		// _____________________________________________________________________________
//      generate the full farm price able to sell right now   and full farm prospect price.
		prospFullPrice = chickens$cP + pigs$cP + cows$cP;
		fullPrice = cowPrice + pigPrice + chickenPrice;
		totalcP = prospFullPrice - fullPrice;
		percent = (totalcP / fullPrice) * 100;
		globalC = fullPrice + totalcP;

		customPriceCow = cowPrice;
		customPricePig = pigPrice;
		customPriceChicken = chickenPrice;

		// _____________________________________________________________________________
		return new SuccessResponse("   FULL PRICE:    - All Animals:  €" + fullPrice + "   COWS:  €" + cowPrice
				+ "   PIGS:  €" + pigPrice + "   CHICKENS:  €" + chickenPrice
				+ "                                                                                            PROSPECTIVE PRICE  - when the  other animals have got the correct weight    "
				+ "   -  TOTAL PROSPECTIVE SALE:  €" + totalcP + " THIS IS " + percent
				+ " % MORE.              ON TOTAL: " + globalC + "      COWS:  €" + cows$cP + "   PIGS:  €" + pigs$cP
				+ "   CHICKENS:  €" + chickens$cP);

	}

	@GetMapping("all-animals")
	public SuccessResponse AllAnimals() {
		System.out.println(
				"\n\n______________________________________________________________________________________________________________\n\n");
		System.out.println("\n\n\n\n\n                                             STARTING SESSION  ");
		System.out.println(
				"\n\n______________________________________________________________________________________________________________\n\n");
//____________________________________________________
//		setting the standard price
		if (animals.size() == 0) {
			chickens$ = 0.00;
			pigs$ = 0.00;
			cows$ = 0.00;
			customPriceCow = 0.00;
			customPriceChicken = 0.00;
			customPricePig = 0.00;
		} else {

			for (Animal animal : animals) {
				if (animal.getType().equalsIgnoreCase(cow)) {
					animal.setPrice(cows$);
				} else if (animal.getType().equalsIgnoreCase(pig)) {
					animal.setPrice(pigs$);
				} else if (animal.getType().equalsIgnoreCase(chicken)) {
					animal.setPrice(chickens$);
				}
			}
		}

//		_________________________________________________
		for (Animal animal : animals) {
			System.out.println(animal);
		}
		System.out.println(
				"\n\n\n\n\n\n\n\n______________________________________________________________________________________________________________\n");
		System.out
				.println("                                         AT MOMENT:   " + animals.size() + "  ANIMALS ADDED");
		System.out.println("\n                            COWS:   " + cows.size() + "               PIGS:  "
				+ pigs.size() + "               CHICKENS:  " + chickens.size());
		System.out.println("                                      POSSIBLE RENTABILITY ");
		System.out.println("\n                            COWS:   " + cowsForSale.size() + "               PIGS:  "
				+ pigsForSale.size() + "               CHICKENS:  " + chickensForSale.size());
		System.out.println("\n TOTAL STANDARD PRICE BY:        COWS:   €" + cows$ + "               PIGS:  €" + pigs$
				+ "               CHICKENS:  €" + chickens$);
		System.out.println("\n TOTAL CUSTOM PRICE BY:        COWS:   €" + customPriceCow + "               PIGS:  €"
				+ customPricePig + "               CHICKENS:  €" + customPriceChicken);
		System.out.println(
				"\n______________________________________________________________________________________________________________\n\n");

		return new SuccessResponse("we have  " + animals.size() + "  animals register in the system.");
	}

}
