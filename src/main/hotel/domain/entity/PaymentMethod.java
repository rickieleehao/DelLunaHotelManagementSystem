package hotel.domain.entity;

public enum PaymentMethod {
	Cash,
	CreditCard;
	
	public PaymentMethod selectPaymentMethod(int option){			
		PaymentMethod paymentMethod;	
		if(option==1) {
			paymentMethod=PaymentMethod.Cash;
		}else if(option==2) {
			paymentMethod=PaymentMethod.CreditCard;
		}else {
			throw new IllegalArgumentException("Invalid Selection!");
		}
		return paymentMethod;
	}
	
	public void printPaymentMethodOption() {
		System.out.println("1. Cash");
		System.out.println("2. CreditCard");
		System.out.print("Pick a option ----> ");
	}
}
