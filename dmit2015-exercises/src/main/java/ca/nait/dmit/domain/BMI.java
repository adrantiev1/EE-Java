package ca.nait.dmit.domain;

public class BMI {
	private int weight;
	private int hieght;
	
	public BMI() {
		super();
	}
	
	public BMI(int weight, int hieght) {
		super();
		this.weight = weight;
		this.hieght = hieght;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHieght() {
		return hieght;
	}

	public void setHieght(int hieght) {
		this.hieght = hieght;
	}
	
	public double bmi() {
		double bmi = (703 * weight)/ Math.pow(hieght, 2);
		return bmi;
	}
	public String bmiCategory() {
		double daBmi = bmi();
		String currentCategory ="Invalid option";
		
		if(daBmi < 18.5) {
			currentCategory = "underweight";
		}
		else if (daBmi < 24.9) {
			currentCategory = "normal";
		}
		else if (daBmi <29.9) {
			currentCategory = "overwhight";
		}
		else {
			currentCategory = "obese";
		}
		
		return currentCategory;
	}

}
