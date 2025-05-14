package main;
import java.util.Objects;

public class Evaluation {


	private final String evaluationID;
	private final String eventID;
	private final String customerID;
	private ProcessState resultOfEvaluation;
	private Compensation compensation;

	// Private constructor to be used by the Builder
	private Evaluation(Builder builder) {
		this.evaluationID = builder.evaluationID;
		this.eventID = builder.eventID;
		this.customerID = builder.customerID;
		this.compensation = builder.m_Compensation;
		this.resultOfEvaluation = ProcessState.Awaiting;
	}

	// Getters
	public String getEvaluationID() {
		return evaluationID;
	}

	public String getEventID() {
		return eventID;
	}

	public String getCustomerID() {return customerID;}

	public Compensation getCompensation() {return compensation;}

	public ProcessState getResultOfEvaluation() {
		return resultOfEvaluation;
	}

	// setter
	public void setCompensation(Compensation compensation) {
		this.compensation = compensation;
	}
//m_Compensation


	/**
	 * @param isReceipt 심사 통과하려면 False, 거부하려면 False
	 */
	public void receiptEvaluation(boolean isReceipt){
		if(isReceipt) {
			this.resultOfEvaluation = ProcessState.Completed;
		}
		else {
			this.resultOfEvaluation = ProcessState.Rejected;
		}
	}

	// toString method
	@Override
	public String toString() {
		return "Evaluation{" +
				"evaluationID='" + evaluationID + '\'' +
				", eventID='" + eventID + '\'' +
				", customerID='"+customerID+'\''+
				'}';
	}

	// Builder class
	public static class Builder {
		// Required fields
		private final String eventID;
		private final String customerID;
		private final String evaluationID;
		// Optional fields
		private Compensation m_Compensation;


		public Builder(String evaluationID,String eventID, String customerID) {
			this.eventID = eventID;
			this.customerID = customerID;
			this.evaluationID = evaluationID;
		}

		public Builder compensation(Compensation compensation) {
			this.m_Compensation = compensation;
			return this;
		}

		public Evaluation build() {
					return new Evaluation(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Evaluation that = (Evaluation) o;
		return Objects.equals(evaluationID, that.evaluationID) &&
						Objects.equals(eventID, that.eventID) &&
						Objects.equals(customerID, that.customerID) &&
						Objects.equals(resultOfEvaluation, that.resultOfEvaluation) &&
						Objects.equals(compensation, that.compensation);
	}

}