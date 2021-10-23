package advent_of_code.main.a2020;

public class ShipWayPoint {
	Integer latitudeShip;
	Integer longitudeShip;
	Integer latitudeWP;
	Integer longitudeWP;



	public ShipWayPoint(Integer latitudeShip, Integer longitudeShip, Integer latitudeWP, Integer longitudeWP) {
		super();
		this.latitudeShip = latitudeShip;
		this.longitudeShip = longitudeShip;
		this.latitudeWP = latitudeWP;
		this.longitudeWP = longitudeWP;
	}

	public Integer getLatitudeShip() {
		return latitudeShip;
	}

	public void setLatitudeShip(Integer latitudeShip) {
		this.latitudeShip = latitudeShip;
	}

	public Integer getLongitudeShip() {
		return longitudeShip;
	}

	public void setLongitudeShip(Integer longitudeShip) {
		this.longitudeShip = longitudeShip;
	}

	public Integer getLatitudeWP() {
		return latitudeWP;
	}

	public void setLatitudeWP(Integer latitudeWP) {
		this.latitudeWP = latitudeWP;
	}

	public Integer getLongitudeWP() {
		return longitudeWP;
	}

	public void setLongitudeWP(Integer longitudeWP) {
		this.longitudeWP = longitudeWP;
	}

	public void deplacer(Deplacement d) {
		if (d.getDirection().equals("E")) {
			this.latitudeWP += d.getQuantite();
		} else if (d.getDirection().equals("W")) {
			this.latitudeWP -= d.getQuantite();
		} else if (d.getDirection().equals("N")) {
			this.longitudeWP += d.getQuantite();
		} else if (d.getDirection().equals("S")) {
			this.longitudeWP -= d.getQuantite();
		} else if (d.getDirection().equals("L")) {
			this.turnLeftWP(d.quantite);
		} else if (d.getDirection().equals("R")) {
			this.turnRightWP(d.quantite);
		} else if (d.getDirection().equals("F")) {
			this.deplacerF(d.quantite);
		}

	}

	private void deplacerF(Integer quantite) {
			this.setLatitudeShip(latitudeShip+quantite*(latitudeWP));
			this.setLongitudeShip(longitudeShip+quantite*(longitudeWP));	
	}

	private void turnRightWP(Integer quantite) {
		if(quantite == 90) {
			this.turnQuarterRight();
		} else if(quantite == 180) {
			int latTmpWP=this.latitudeWP;
			int lonTmpWP=this.longitudeWP;
			this.setLatitudeWP(-latTmpWP);
			this.setLongitudeWP(- lonTmpWP);
		} else if(quantite == 270) {
			this.turnQuarterLeft();
		}

	}

	private void turnLeftWP(Integer quantite) {
		if(quantite == 90) {
			this.turnQuarterLeft();
		} else if(quantite == 180) {
			int latTmpWP=this.latitudeWP;
			int lonTmpWP=this.longitudeWP;
			this.setLatitudeWP(-latTmpWP);
			this.setLongitudeWP(- lonTmpWP);
		} else if(quantite == 270) {
			this.turnQuarterRight();
		}
		
	}
		private void turnQuarterLeft() {
				int latTmpWP=this.latitudeWP;
				int lonTmpWP=this.longitudeWP;
				this.setLatitudeWP(-lonTmpWP);
				this.setLongitudeWP(latTmpWP);
			

		}

		private void turnQuarterRight() {
			int latTmpWP=this.latitudeWP;
			int lonTmpWP=this.longitudeWP;
			this.setLatitudeWP(lonTmpWP);
			this.setLongitudeWP(- latTmpWP);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ship : latitude :"+this.latitudeShip+" ,longitude :"+this.longitudeShip+" WP :" +this.latitudeWP+" ,longitude :"+this.longitudeWP;
	}
}
