package ie.cct.Farm.Managment.Animals;

public class Cow extends Animal {

//	creating a constructor to pass the encapsulated Attributes from animals as parameter.
	public Cow(String type, Double weight, Integer id) {
		this.setType(type);
		this.setWeight(weight);
		this.setId(id);
	}

//	creating a toString to Override it.
	@Override
	public String toString() {
		return "      ID: " + getId() +"       ANIMAL: " + getType() + "        WEIGHT:  " + getWeight() + " Kg."
				+ "\n\n\n______________________________________________________________________________________________________________\n";
	}
}
