package ie.cct.Farm.Managment.Animals;

public class Chicken extends Animal {

//	creating a constructor to pass the encapsulated Attributes from animals as parameter.
	public Chicken (String type, Double weight) {
		this.setType(type);
		this.setWeight(weight);
	}

//	creating a toString to Override it.
	@Override
	public String toString() {
		return "      Animal: " + getType() + "        Weight:  " + getWeight() + " Kg."
				+ "\n\n\n______________________________________________________________________________________________________________\n";
	}

}
