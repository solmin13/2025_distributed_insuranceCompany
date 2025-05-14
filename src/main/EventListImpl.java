import java.util.ArrayList;

public class EventListImpl implements EventList {

	public ArrayList<Event> Events;

	public EventListImpl(){
		Events = new ArrayList<>();
	}

	/**
	 * 
	 * @param eventID
	 */
	public boolean delete(String eventID){
		for (Event event : Events) {
			if (String.valueOf(event.getEventID()).equals(eventID)) {
				Events.remove(event);
				return true;
			}
		}
		return false;
	}

	public boolean delete(Event tragetEvent){
		for (Event event : Events) {
			if (event.equals(tragetEvent)) {
				Events.remove(event);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean insert(Event event) {
		if(event==null)return false;
		return Events.add(event);
	}


//Search
	@Override
	public ArrayList<Event> searchEvent(String key, String value) {
		ArrayList<Event> result = new ArrayList<>();
		for(Event event : this.Events){
			switch (key.toLowerCase()){
				case "keyword":
					if(event.toString().toLowerCase().contains(value.toLowerCase())) result.add(event);
					break;
				case "state":
					if(event.getState() == ProcessState.fromString(value)) result.add(event);
					break;
				case "all":
					result.add(event);
					break;
				default:
					System.out.println("지원하지 않는 검색 필드입니다: " + key);
					return result;
			}
		}
		return result;
	}

	@Override
	public ArrayList<Evaluation> searchEvaluation(String key, String value) {
		ArrayList<Evaluation> result = new ArrayList<>();
		for(Event event : this.Events){
			Evaluation evaluation = event.getM_Evaluation();
			if(evaluation==null) continue;
			switch (key.toLowerCase()){
				case "keyword":
					if(evaluation.toString().toLowerCase().contains(value.toLowerCase())) result.add(evaluation);
					break;
				case "state":
					if(evaluation.getState() == ProcessState.fromString(value)) result.add(evaluation);
					break;
				case "all":
					result.add(evaluation);
					break;
				default:
					System.out.println("지원하지 않는 검색 필드입니다: " + key);
					return result;
			}
		}
		return result;
	}

	@Override
	public ArrayList<Compensation> searchCompensation(String key, String value) {
		ArrayList<Compensation> result = new ArrayList<>();
		for(Event event : this.Events){
			Compensation compensation = event.getM_Evaluation().getM_Compensation();
			if(compensation==null) continue;
			switch (key.toLowerCase()){
				case "keyword":
					if(compensation.toString().toLowerCase().contains(value.toLowerCase())) result.add(compensation);
					break;
				case "state":
					if(compensation.getState() == ProcessState.fromString(value)) result.add(compensation);
					break;
				case "all":
					result.add(compensation);
					break;
				default:
					System.out.println("지원하지 않는 검색 필드입니다: " + key);
					return result;
			}
		}
		return result;
	}


//Update
	@Override
	public boolean update(Event updatedEvent) {
		for (int i = 0; i < Events.size(); i++) {
			Event existingEvents = Events.get(i);
			if (existingEvents.getEventID().equals(updatedEvent.getEventID())) {
				this.Events.set(i, updatedEvent);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(Evaluation updatedEvaluation) {
		// 수정된 코드
    // 리스트에서 Event 객체를 가져옵니다.
    for (Event eventInList : Events) {
      Evaluation existingEvaluation = eventInList.getM_Evaluation();
      if (existingEvaluation.getEvaluationID().equals(updatedEvaluation.getEvaluationID())) {
        eventInList.setM_Evaluation(updatedEvaluation);
        return true;
      }
    }
		return false;
	}

	@Override
	public boolean update(Compensation compensation) {
    for (Event event : Events) {
      Compensation existingCompensation = event.getM_Evaluation().getM_Compensation();
      if (existingCompensation.getCompensationID().equals(compensation.getCompensationID())) {
        event.getM_Evaluation().setM_Compensation(compensation);
        return true;
      }
    }
		return false;
//update compensation
	}
}//end EventListImpl