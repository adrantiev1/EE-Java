package dmit2015.model;


public class LoanSchedule {
	private int paymentNumber;
	private double interestPaid;
	private double principalPaid;
	private double remainingBalance;
	
	public LoanSchedule() {
		super();
		this.paymentNumber = 1;
		this.principalPaid = 0;
		this.remainingBalance = 0;
		this.interestPaid = 0;
		
	}
	
	public LoanSchedule(int paymentNumber, double interestPaid, double principalPaid, double remainingBalance) {
		super();
		this.paymentNumber = paymentNumber;
		this.interestPaid = interestPaid;
		this.principalPaid = principalPaid;
		this.remainingBalance = remainingBalance;
	}


	@Override
	public String toString() {
		return "LoanSchedule [paymentNumber=" + paymentNumber + ", interestPaid=" + interestPaid + ", principalPaid="
				+ principalPaid + ", remainingBalance=" + remainingBalance + "]";
	}

	public int getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(int paymentNumber) {
		if(paymentNumber >= 1) {
			this.paymentNumber = paymentNumber;
		}
	}
		
	
	public double getInterestPaid() {
		return interestPaid;
	}
	public void setInterestPaid(double interestPaid) {
		
		if(interestPaid >= 0) {
			
			this.interestPaid = interestPaid;
		}
	}
	
	
	public double getPrincipalPaid() {
		return principalPaid;
	}
	public void setPrincipalPaid(double principalPaid) {
		if(principalPaid >= 0) {
			this.principalPaid = principalPaid;
		}
	}
	
	
	public double getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(double remainingBalance) {
		if(remainingBalance >= 0) {
			this.remainingBalance = remainingBalance;
		}
	}
	
	/*Methods*/
	
	
	
	

}
