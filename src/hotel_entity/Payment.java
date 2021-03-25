package hotel_entity;

public class Payment {
	private double totalPrice, deposit;
	private PaymentMethod paymentMethod;
	private int cardNumber;
	
	public double getDeposit() {
		return 0;
	}
	public PaymentMethod getPaymentMethod() {
		return null;
	}
	public int getCardNumber() {
		return 0;
	}
}
