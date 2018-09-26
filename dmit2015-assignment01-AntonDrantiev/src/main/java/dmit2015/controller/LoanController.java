package dmit2015.controller;


import org.omnifaces.util.Messages;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import javax.annotation.PostConstruct;



import dmit2015.model.Loan;
import dmit2015.model.LoanSchedule;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped

public class LoanController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Loan  currentLoan = new Loan();
	
	private BarChartModel barModel;
	

	
	
	
	
	public Loan getCurrentLoan() {
		return currentLoan;
	}

	public void setCurrentLoan(Loan currentLoan) {
		this.currentLoan = currentLoan;
	}

	

	


	public void calculate() {
		Messages.addGlobalInfo("Your monthly mortgage payment is $ {0}", currentLoan.getMonthlyPayment());
		
		createBarModels();
		
	}
	
	
	
	@PostConstruct
	public void init() {
		createBarModels();
		
	}
	public BarChartModel getBarModel() {
		return barModel;
	}
	private void createBarModels() {
        createBarModel();
       
    }
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries mortgageChart = new ChartSeries();
        int amortizationPeriod = currentLoan.getAmortizationPeriod();
        
        String topLabel = MessageFormat.format("${0}.00 at {1}% for {2} years.", 
        		currentLoan.getMortgageAmount(),
        		currentLoan.getAnnualInterestRate(), 
        		currentLoan.getAmortizationPeriod());
        mortgageChart.setLabel(topLabel);
        
        int yearTotal = -1;
        LoanSchedule[] newLoanSchedule = currentLoan.getLoanScheduleArray();
        
        for (int i=0; i<amortizationPeriod;i++)
        {       	       	
        	yearTotal = yearTotal + 12;
        	double remainingBalance = newLoanSchedule[yearTotal].getRemainingBalance();
        	 mortgageChart.set(i+1, remainingBalance);
        }
        model.addSeries(mortgageChart);
        
        
        return model;
    }
     
	private void createBarModel() {
		barModel = initBarModel();
	         
		barModel.setTitle("Amortization Schedule");
		barModel.setLegendPosition("ne");
	         
	       Axis xAxis = barModel.getAxis(AxisType.X);
	       xAxis.setLabel("Amortization in Years");
	       xAxis.setMin(0);
	       xAxis.setMax(currentLoan.getAmortizationPeriod());
	         
	       Axis yAxis = barModel.getAxis(AxisType.Y);
	       yAxis.setLabel("Mortgage Amount");
	       yAxis.setMin(0);
	       yAxis.setMax(currentLoan.getMortgageAmount());
	}
	

}
