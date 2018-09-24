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
		super();
		// TODO Auto-generated constructor stub
	}
	


	public Loan(double mortgageAmount, double annualInterestRate, int amortizationPeriod) {
		super();
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
		
		double amortizationPeriodCalc = -12 * Double.valueOf(amortizationPeriod);
		double annualInterestRateCalc = Math.pow(((annualInterestRate / 200)+1), (1/6) );
		 
		
		double monthlyPayment = mortgageAmount * (((annualInterestRateCalc)-1) / (1 - (Math.pow(annualInterestRateCalc, amortizationPeriodCalc)))) ;
		
		
	
		return Math.round(monthlyPayment * 100.0) / 100.0 ;
	}
	
	public LoanSchedule[] getLoanScheduleArray() {
		
		LoanSchedule newLoanSchedule = new LoanSchedule();
		
		double monthlyPercentageRate = (Math.pow(((annualInterestRate / 200)+1), (1/6) ) - 1 );
		
//		rounding remaining balance to 2 decimal places
		double remainingBalance = Math.round(newLoanSchedule.getRemainingBalance() * 100.0) /100.0;
		double interestPaid = monthlyPercentageRate  * remainingBalance;
		double principalPaid = getMonthlyPayment() - interestPaid;
//		rounding to 2 decimal places
		interestPaid = Math.round(interestPaid *100.0) / 100.0;
		principalPaid = Math.round(principalPaid * 100.0) / 100.0;
		
		LoanSchedule newLoanScheduleArray[] = new LoanSchedule[4];
		newLoanScheduleArray[0].getPaymentNumber();
		newLoanScheduleArray[1].setInterestPaid(interestPaid);
		newLoanScheduleArray[2].setPrincipalPaid(principalPaid);
		newLoanScheduleArray[3].setRemainingBalance(remainingBalance);
		
		
		
		
		
		return newLoanScheduleArray;
	}

}
