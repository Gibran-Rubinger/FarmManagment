package ie.cct.Farm.Managment.Animals;

public class Pig extends Animal {

//	creating a constructor to pass the encapsulated Attributes from animals as parameter.
	public Pig (String type, Double weight) {
		this.type = type;
		this.weight = weight;
	}
	
//	creating a toString to Override it.
	@Override
	public String toString() {
		return "Animal: " + type + "     Weight:  " + weight + " Kg.";
	}

}
