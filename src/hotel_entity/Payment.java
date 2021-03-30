package hotel_entity;

public class Payment { //yy
	private double totalPrice, deposit;
	private PaymentMethod paymentMethod;
	private int cardNumber;
	
	public Payment() {
		
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public double getDeposit() {
		return deposit;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public void setcardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
