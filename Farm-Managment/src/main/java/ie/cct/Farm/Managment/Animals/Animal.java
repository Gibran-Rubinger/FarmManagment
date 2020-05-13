package ie.cct.Farm.Managment.Animals;

public class Animal {

//	defining the attributes as private.
	protected String type = "";
	protected Double weight = 0.00;
	protected boolean weightTarget = false;

//	Built a constructor to pass the attributes above as a parameter.
	public Animal(String species, Double weight, boolean weightTarget) {
		this.type = species;
		this.weight = weight;
		this.weightTarget = weightTarget;
	}

// adding a regular constructor.
	public Animal() {

	}

//	Generating the Getters and Setters.
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public boolean isWeightTarget() {
		return weightTarget;
	}

	public void setWeightTarget(boolean weightTarget) {
		this.weightTarget = weightTarget;
	}

//	creating a toString to Override it.
	@Override
	public String toString() {
		return "      Animal: " + type + "        Weight:  " + weight + " Kg."
				+ "\n\n\n______________________________________________________________________________________________________________\n";
	}

// hashCode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

}
