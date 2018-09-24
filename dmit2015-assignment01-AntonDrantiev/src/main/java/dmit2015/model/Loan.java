package dmit2015.model;




public class Loan {
	public double mortgageAmount;
	public double annualInterestRate;
	public int amortizationPeriod;
	
	@Override
	public String toString() {
		return "Loan [mortgageAmount=" + mortgageAmount + ", annualInterestRate=" + annualInterestRate
				+ ", amortizationPeriod=" + amortizationPeriod + "]";
	}
	
	public Loan() {
		this.mortgageAmount = 0;
		this.annualInterestRate = 0;
		this.amortizationPeriod = 0;
	}
	


	public Loan(double mortgageAmount, double annualInterestRate, int amortizationPeriod) {
		
		this.mortgageAmount = mortgageAmount;
		this.annualInterestRate = annualInterestRate;
		this.amortizationPeriod = amortizationPeriod;
	}

	//getters and setters
	public double getMortgageAmount() {
		return mortgageAmount;
	}

	public void setMortgageAmount(double mortgageAmount) {
		this.mortgageAmount = mortgageAmount;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public int getAmortizationPeriod() {
		return amortizationPeriod;
	}

	public void setAmortizationPeriod(int amortizationPeriod) {
		this.amortizationPeriod = amortizationPeriod;
	}
	
	
	public double getMonthlyPayment() 
	{	
		
		double amortizationPeriodCalc = -12.0 * (Double.valueOf(amortizationPeriod));
		double annualInterestRateCalc = Math.pow(((annualInterestRate / 200.0)+1.0), (1.0/6.0) );
		 
		
		double monthlyPayment = mortgageAmount * (((annualInterestRateCalc)-1.0) / (1.0 - (Math.pow(annualInterestRateCalc, amortizationPeriodCalc)))) ;
		
		
	
		return Math.round(monthlyPayment * 100.0) / 100.0 ;
	}
	
	public LoanSchedule[] getLoanScheduleArray() {
		
		int totalPayments = amortizationPeriod *12;
		LoanSchedule newLoanScheduleArray[] = new LoanSchedule[totalPayments];
		double remainingBalance = mortgageAmount;
//		Using formulas from figure 2 calculations
		double monthlyPercentageRate = (Math.pow(((annualInterestRate / 200.0)+1.0), (1.0/6.0) ) - 1.0 );

		

		for (int paymentNumber = 1; paymentNumber <= totalPayments ; paymentNumber++) {
			double interestPaid;
			
			if (paymentNumber < totalPayments) {
				double monthlyPayment = getMonthlyPayment();
				LoanSchedule currentLoanSchedule = new LoanSchedule();
				
				interestPaid = Math.round((monthlyPercentageRate  * remainingBalance)* 100.0 ) /100.0;
				double principalPaid = Math.round((monthlyPayment - interestPaid) * 100.0 ) / 100.0;
				remainingBalance = Math.round((remainingBalance-principalPaid)*100.0)/100.0;
				
				
				currentLoanSchedule.setPaymentNumber(paymentNumber);
				currentLoanSchedule.setInterestPaid(interestPaid);
				currentLoanSchedule.setPrincipalPaid(principalPaid);
				currentLoanSchedule.setRemainingBalance(remainingBalance);
				int index = paymentNumber -1;
				newLoanScheduleArray[index] = currentLoanSchedule;
				
			}else {
				interestPaid = Math.round((monthlyPercentageRate  * remainingBalance)* 100.0 ) /100.0;
				double principalPaid = remainingBalance;
				remainingBalance = 0.0;
				newLoanScheduleArray[paymentNumber-1] = new LoanSchedule(paymentNumber, interestPaid, principalPaid, remainingBalance);
				
			}
			
			
			
		}

	
		
		
		
		
		return newLoanScheduleArray;
	}

}
