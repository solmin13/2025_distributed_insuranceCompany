import java.util.Date;
import java.util.Objects;

public class Evaluation {


	private final String evaluationID;
	private final String eventID;
	private final String customerID;
	private ProcessState evaluationState;
	private Compensation m_Compensation;

	// Private constructor to be used by the Builder
	private Evaluation(Builder builder) {
		this.evaluationID = builder.evaluationID;
		this.eventID = builder.eventID;
		this.customerID = builder.customerID;
		this.m_Compensation = builder.m_Compensation;
		this.evaluationState = ProcessState.Awaiting;
	}

	// Getters
	public String getEvaluationID() {
		return evaluationID;
	}

	public String getEventID() {
		return eventID;
	}

	public Compensation getM_Compensation() {return m_Compensation;}

	public ProcessState getState() {
		return evaluationState;
	}

	// setter
	public void setM_Compensation(Compensation m_Compensation) {
		this.m_Compensation = m_Compensation;
	}
//m_Compensation

	public void receiptEvaluation(boolean isReceipt){
		if(isReceipt) {
			this.evaluationState = ProcessState.Completed;
		}
		else {
			this.evaluationState = ProcessState.Rejected;
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
						Objects.equals(evaluationState, that.evaluationState) &&
						Objects.equals(m_Compensation, that.m_Compensation);
	}

}