package hotel_entity;

public class Payment {
	
	private double totalPrice;
	private double deposit;
	private PaymentMethod paymentMethod;
	private int cardNumber;

	public Payment() {
		this.deposit = 100; // by default
	}

	public double getDeposit() {
		return this.deposit;
	}

	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}

	public int getCardNumber() {
		return this.cardNumber;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setcardNumber(int cardNumber) {
		if(cardNumber >= 0) {
			this.cardNumber = cardNumber;
		}
		else {
			throw new IllegalArgumentException("Invalid input");
		}
	}

	public void setTotalPrice(double totalPrice) {
		if(totalPrice >= 0) {
			this.totalPrice = totalPrice;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}
