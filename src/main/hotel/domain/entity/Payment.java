package hotel.domain.entity;

public class Payment {

	private PaymentMethod paymentMethod;
	private double totalPrice;
	private double deposit;
	private String cardNumber;

	public Payment(String paymentMethod, double totalPrice, String cardNumber) {
		if (paymentMethod.equals("CreditCard")) {
			this.paymentMethod = PaymentMethod.CreditCard;
		} else if (paymentMethod.equals("Cash")) {
			this.paymentMethod = PaymentMethod.Cash;
		}
		this.totalPrice = totalPrice;
		this.cardNumber = cardNumber;
		this.deposit = 100; // by default
	}

	public Payment() {
		this.deposit = 100; // by default
		this.cardNumber = "";
	}

	public double getDeposit() {
		return this.deposit;
	}

	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setcardNumber(String cardNumber) {
		if (cardNumber.matches("[a-zA-Z ]+")) {
			throw new IllegalArgumentException("Card number must consist number only!");
		} else if (cardNumber.length() != 12) {
			throw new IllegalArgumentException("Card number must have 12 digits!");
		} else {
			this.cardNumber = cardNumber;
		}
	}

	public void setTotalPrice(double totalPrice) {
		if (totalPrice >= 0) {
			this.totalPrice = totalPrice;
		} else {
			throw new IllegalArgumentException();
		}
	}
}
