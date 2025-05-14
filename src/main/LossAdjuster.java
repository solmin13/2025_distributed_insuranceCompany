
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.random.RandomGenerator;

/**
 * @author �ڼֹ�
 * @version 1.0
 * @created 11-5-2025 ���� 11:25:09
 */
// public class LossAdjuster extends Employee {
	public class LossAdjuster{
		private EventList EventList;
	public LossAdjuster(){
		this.EventList = new EventListImpl();
	}
//	public void finalize() throws Throwable {
//		super.finalize();
//	}

	public boolean evaluateCompensation(String eventID){
		// Need to look up customer information, class diagram needs to be modified, can't be implemented in current state
		return false;
	}


	public void payCompensation() {
		List<Compensation> compensations = EventList.searchCompensation("all", "");
		if (compensations.isEmpty()) {
			System.out.println("지급할 보상금이 없습니다.");
			return;
		}
		System.out.println("지급할 보상금을 선택하세요:");
		for (int i = 0; i < compensations.size(); i++) {
			System.out.println((i + 1) + "|| " + compensations.get(i).getCompensationID()+", paidState: "+compensations.get(i).getState()+", claimsPaid: "+compensations.get(i).getClaimsPaid());
		}
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		if (choice > 0 && choice <= compensations.size()) {
			Compensation selectedCompensation = compensations.get(choice - 1);
			System.out.println("선택한 보상 정보:");
			System.out.println(selectedCompensation);
			System.out.println("보상을 지급하시겠습니까? (y/n) 취소하려면 c를 입력해주세요.");
			String confirm = scanner.nextLine();
			if (confirm.equalsIgnoreCase("n")) {
				System.out.println("보상 지급이 거부되었습니다.");
				selectedCompensation.receiptCompensation(false);
				EventList.update(selectedCompensation);
				return;
			} else if (confirm.equalsIgnoreCase("y")) {
				System.out.println("보상 지급이 완료되었습니다: " + selectedCompensation.getCompensationID());
				selectedCompensation.receiptCompensation(true);
				EventList.update(selectedCompensation);
			}	else if(confirm.equalsIgnoreCase("c")) {
				System.out.println("보상 지급이 취소되었습니다.");
			}
			else {
				System.out.println("잘못된 선택입니다.");
			}
		}
	}


	//임시, 더미데이터
	public void genrateDummy(int count){

		for(int i=0;i<count;i++) {
			String customerID = "CustomerN"+i;
			String EventID = "EventN"+i;
			String EvaluationID = "EvaluationN"+i;
			String CompensationID = "CompensationN"+i;
			Event e = new Event.Builder(EventID,customerID).build();
			Evaluation ev = new Evaluation.Builder(EvaluationID,e.getEventID(), customerID).build();
			Compensation c = new Compensation.Builder(CompensationID,ev.getEvaluationID(), customerID).claimsPaid((int)(Math.random()*1000000+10))
					.build();

			ev.setM_Compensation(c);
			e.setM_Evaluation(ev);
			EventList.insert(e);
		}
	}

	public static void main(String[] args) {
		LossAdjuster la = new LossAdjuster();
		la.genrateDummy(10);
		la.payCompensation();
	}
}//end LossAdjuster