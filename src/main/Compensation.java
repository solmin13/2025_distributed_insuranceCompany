package main;
import java.util.Date;
import java.util.Objects;

public class Compensation {

	// Example Fields
	private final String compensationID;
	private final String evaluationID; // Link to an Evaluation
	private final String customerID;   // Link to a Customer
	private ProcessState paidState;
	private boolean resultOfPaid;
	private int claimsPaid;
	// Builder Pattern

	private Compensation(Builder builder) {
		this.compensationID = builder.compensationID;
		this.evaluationID = builder.evaluationID;
		this.customerID = builder.customerID;
		this.claimsPaid = builder.claimsPaid;
		this.paidState = ProcessState.Awaiting;
		this.resultOfPaid = false;
	}

	// Getters
	public String getCompensationID() {
		return compensationID;
	}

	public String getEvaluationID() {
		return evaluationID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public ProcessState getState() {
		return paidState;
	}

	public int getClaimsPaid() {
		return claimsPaid;
	}
	public boolean getResultOfPaid() {
		return resultOfPaid;
	}
	public ProcessState getPaidState(){
		return paidState;
	}

	// setter?
	public void receiptCompensation(boolean isReceipt){
		if(isReceipt) {
			this.paidState = ProcessState.Completed;
		}
		else {
			this.paidState = ProcessState.Rejected;
		}
	}

	public void setResultOfPaid(boolean resultOfPaid) {
		this.resultOfPaid = resultOfPaid;
	}

	public void setClaimsPaid(int paid){
		this.claimsPaid = paid;
  }

	public static class Builder {
		private final String evaluationID;
		private final String customerID;
		private final String compensationID;
		private ProcessState paidState;
		private int claimsPaid;


		public Builder(String compensationID,String evaluationID, String customerID) {
			this.evaluationID = evaluationID;
			this.customerID = customerID;
			this.compensationID = compensationID;
			this.paidState = ProcessState.Awaiting;
			this.claimsPaid = 0;

		}

		public Builder paidState(ProcessState paidState) {
			this.paidState = paidState;
			return this;
		}

		public Builder claimsPaid(int claimsPaid) {
			this.claimsPaid = claimsPaid;
			return this;
		}


		public Compensation build() {
			return new Compensation(this);
		}
	}


	@Override
	public String toString() {
		return "Compensation{" +
				"compensationID='" + compensationID + '\'' +
				", evaluationID='" + evaluationID + '\'' +
				", customerID='" + customerID + '\'' +
				", paidState=" + paidState +
				", claimsPaid=" + claimsPaid +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Compensation that = (Compensation) o;
		return claimsPaid == that.claimsPaid && Objects.equals(compensationID, that.compensationID) && Objects.equals(evaluationID, that.evaluationID) && Objects.equals(customerID, that.customerID) && paidState == that.paidState;
	}

}