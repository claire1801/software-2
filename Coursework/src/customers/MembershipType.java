package customers;

public enum MembershipType {
	STUDENT(0.1), MEMBER(0.15), EMPLOYEE(0.25);

	private final double discountPercent;

	private MembershipType(double discountPercent) {
			this.discountPercent = discountPercent;
	}
	/**
	 * get the percent discount for membership type
	 * @return double 
	 */

	public double getDiscount() {return discountPercent;}

}