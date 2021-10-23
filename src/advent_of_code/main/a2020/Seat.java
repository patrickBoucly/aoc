package advent_of_code.main.a2020;

public class Seat {
	int SeatID;
	String code;

	public Seat(String code) {
		super();
		
		this.code = code;
		SeatID = getSeatIDFromCode(code);
	}

	public int getSeatID() {
		return SeatID;
	}

	public void setSeatID(int seatID) {
		SeatID = seatID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSeatIDFromCode(String code) {
		int borneInfRangee = 0;
		int borneSupRangee = 127;
		int borneInfColonne = 0;
		int borneSupColonne = 7;
		if (this.asCodeGoodFormat()) {
			
			
			for (int i = 0; i < 7; i++) {
				if (code.substring(i, i + 1).equals("F")) {
					borneSupRangee=borneSupRangee-(borneSupRangee-borneInfRangee+1)/2;
				} else {
					borneInfRangee=borneInfRangee+(borneSupRangee-borneInfRangee+1)/2;
				}
			}

			for (int i = 7; i < 10; i++) {
				if (code.substring(i, i + 1).equals("L")) {
					borneSupColonne=borneSupColonne-(borneSupColonne-borneInfColonne+1)/2;
				} else {
					borneInfColonne=borneInfColonne+(borneSupColonne-borneInfColonne+1)/2;
				}
			}

		} else {
			System.out.println("mauvais code");
		}
System.out.println("IC "+borneInfColonne);
System.out.println("SC "+borneSupColonne);
System.out.println("IR "+borneInfRangee);
System.out.println("SR "+borneSupRangee);
		return (borneInfColonne+8*borneInfRangee);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + SeatID;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Seat other = (Seat) obj;
		if (SeatID != other.SeatID)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	private boolean asCodeGoodFormat() {
		// TODO Auto-generated method stub
		return true;
	}
}
