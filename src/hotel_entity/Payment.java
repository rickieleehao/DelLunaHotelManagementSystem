package hotel_entity;

public class Payment { //yy
	private double deposit, totalPrice;
	private PaymentMethod paymentMethod;
	private int cardNumber;
	
	public Payment(double rate, PaymentMethod paymentMethod) {
		this.deposit = 0.15 * rate;
		this.paymentMethod = paymentMethod;
		this.cardNumber = 0; //cannot null unless changed to type Integer 
	}
	
	public Payment(double rate, PaymentMethod paymentMethod, int cardNumber) {
		this.deposit = 0.15 * rate;
		this.paymentMethod = paymentMethod;
		this.cardNumber = cardNumber;
	}
	
	public double getDeposit() {
		return deposit;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	
	public void setTotalprice(double totalprice) {]
		this.totalPrice = totalprice;
	}
	
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public void setcardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
}
