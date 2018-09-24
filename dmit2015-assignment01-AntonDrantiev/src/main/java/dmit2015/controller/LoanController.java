package dmit2015.controller;

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
	private LoanSchedule[] loanScheduleTable; 
	private BarChartModel loanChart;
	
	public Loan getCurrentLoan() {
		return currentLoan;
	}

	public void setCurrentLoan(Loan currentLoan) {
		this.currentLoan = currentLoan;
	}


	public LoanSchedule[] getLoanScheduleTable() {
		return loanScheduleTable;
	}

	public void setLoanScheduleTable(LoanSchedule[] loanScheduleTable) {
		this.loanScheduleTable = loanScheduleTable;
	}
	
	

	public BarChartModel getLoanChart() {
		return loanChart;
	}

	public void setLoanChart(BarChartModel loanChart) {
		this.loanChart = loanChart;
	}

	public void calculate() {
		
	}
	

}
