package ie.cct.Farm.Managment.Animals;

import java.rmi.server.UID;

public class Animal {

//	defining the attributes as private.
	private String type = "";
	private Double weight = 0.00;
	private Double price = 0.00;
	private Double pricePerKg = 0.00;

	UID id = new UID();
//	Built a constructor to pass the attributes above as a parameter.
	public Animal(String species, Double weight, UID id, Double price, Double pricePerKg) {
		this.type = species;
		this.weight = weight;
		this.id = id;
		this.price = price;
		this.pricePerKg = pricePerKg;

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

	public UID getId() {
		return id;
	}

	public void setId(UID id) {
		this.id = id;
	}
	

public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPricePerKg() {
		return pricePerKg;
	}

	public void setPricePerKg(Double pricePerKg) {
		this.pricePerKg = pricePerKg;
	}

	//	creating a toString to Override it.
	@Override
	public String toString() {
		return "      ID: " + id +"       ANIMAL: " + type + "        WEIGHT:  " + weight + " Kg.        Price:  " + price + "         Price per Kg:  " + pricePerKg 
				+ "\n\n\n___________________________________________________________________________________________________________________________________________\n";
	}
	// hashCode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((pricePerKg == null) ? 0 : pricePerKg.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (pricePerKg == null) {
			if (other.pricePerKg != null)
				return false;
		} else if (!pricePerKg.equals(other.pricePerKg))
			return false;
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
