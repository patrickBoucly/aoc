package advent_of_code.main.a2020;

public class Ship {
	Integer latitude;
	Integer longitude;
	String cap;

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public Ship(Integer latitude, Integer longitude, String cap) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.cap = cap;
	}

	public void deplacer(Deplacement d) {
		if (d.getDirection().equals("E")) {
			this.latitude += d.getQuantite();
		} else if (d.getDirection().equals("W")) {
			this.latitude -= d.getQuantite();
		} else if (d.getDirection().equals("N")) {
			this.longitude += d.getQuantite();
		} else if (d.getDirection().equals("S")) {
			this.longitude -= d.getQuantite();
		} else if (d.getDirection().equals("L")) {
			this.turnLeft(d.quantite);
		} else if (d.getDirection().equals("R")) {
			this.turnRight(d.quantite);
		} else if (d.getDirection().equals("F")) {
			this.deplacer(new Deplacement(this.getCap(), d.quantite));
		}

	}

	private void turnRight(Integer quantite) {
		if(quantite == 90) {
			this.turnQuarterRight();
		} else if(quantite == 180) {
			this.turnQuarterRight();
			this.turnQuarterRight();
		} else if(quantite == 270) {
			this.turnQuarterRight();
			this.turnQuarterRight();
			this.turnQuarterRight();
		}

	}

	private void turnLeft(Integer quantite) {
		if(quantite == 90) {
			this.turnQuarterLeft();
		} else if(quantite == 180) {
			this.turnQuarterLeft();
			this.turnQuarterLeft();
		} else if(quantite == 270) {
			this.turnQuarterLeft();
			this.turnQuarterLeft();
			this.turnQuarterLeft();
		}
		
	}
		private void turnQuarterRight() {
			if(this.cap.equals("E")) {
				this.setCap("S");
			} else if(this.cap.equals("S")) {
				this.setCap("W");
			} else if(this.cap.equals("W")) {
				this.setCap("N");
			} else if(this.cap.equals("N")) {
				this.setCap("E");
			} 

		}

		private void turnQuarterLeft() {
			if(this.cap.equals("E")) {
				this.setCap("N");
			} else if(this.cap.equals("S")) {
				this.setCap("E");
			} else if(this.cap.equals("W")) {
				this.setCap("S");
			} else if(this.cap.equals("N")) {
				this.setCap("W");
			} 
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "cap :"+this.cap+", latitude :"+this.latitude+" ,longitude :"+this.longitude ;
	}
}
