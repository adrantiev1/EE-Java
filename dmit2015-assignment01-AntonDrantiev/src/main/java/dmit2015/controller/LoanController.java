package dmit2015.controller;

import org.omnifaces.util.Messages;
import org.primefaces.model.chart.BarChartModel;


import dmit2015.model.Loan;
import dmit2015.model.LoanSchedule;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped

public class LoanController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Loan  currentLoan = new Loan();
	
	private BarChartModel loanChart;
	
	
	
	
	public Loan getCurrentLoan() {
		return currentLoan;
	}

	public void setCurrentLoan(Loan currentLoan) {
		this.currentLoan = currentLoan;
	}

	

	public BarChartModel getLoanChart() {
		return loanChart;
	}

	public void setLoanChart(BarChartModel loanChart) {
		this.loanChart = loanChart;
	}
	
//	method

	public void calculate() {
		Messages.addGlobalInfo("Your monthly mortgage payment is ${0}", monthlyMortgage);
		
		
	}
	

}
