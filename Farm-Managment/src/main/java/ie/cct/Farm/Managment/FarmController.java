package ie.cct.Farm.Managment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

	public FarmController() {
		animals = new ArrayList<Animal>();
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

		if (animal.getType().equalsIgnoreCase(cow)) {
			animals.add(new Cow(animal.getType(), animal.getWeight()));
			animalPost = cow;
			return new SuccessResponse("This " + animalPost + " was successfully added to the system.");
		} else if (animal.getType().equalsIgnoreCase(pig)) {
			animals.add(new Pig(animal.getType(), animal.getWeight()));
			animalPost = pig;
			return new SuccessResponse("This " + animalPost + " was successfully added to the system.");
		} else if (animal.getType().equalsIgnoreCase(chicken)) {
			animals.add(new Chicken(animal.getType(), animal.getWeight()));
			animalPost = chicken;
			return new SuccessResponse("This " + animalPost + " was successfully added to the system.");
		} else {
			return new SuccessResponse("Sorry, at moment " + animal.getType() + " is not inplemented in system yet.");
		}

	}
}
