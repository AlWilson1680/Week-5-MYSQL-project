package entity;

public class Cards {

	private int cardsId;
	private String name;
	private String type;
	private String description;
	private String stats;

	public Cards(int cardsId, String name, String type, String description, String stats) {
		this.setCardsId(cardsId);
		this.setName(name);
		this.setType(type);
		this.setDescription(description);
		this.setStats(stats);
	}

	public int getCardsId() {
		return cardsId;
	}

	public void setCardsId(int cardsId) {
		this.cardsId = cardsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

}
