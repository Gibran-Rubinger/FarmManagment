package ie.cct.Farm.Managment.Animals;

import java.rmi.server.UID;

public class Cow extends Animal {

//	creating a constructor to pass the encapsulated Attributes from animals as parameter.
	public Cow(String type, Double weight, UID id) {
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
