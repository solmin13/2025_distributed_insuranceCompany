import java.util.ArrayList;
import java.util.List;

/**
 * @author �ڼֹ�
 * @version 1.0
 * @created 11-5-2025 ���� 11:25:09
 */
public interface EventList {


	public boolean delete(String eventID);
	public boolean insert(Event event);

	//search
	public ArrayList<Event> searchEvent(String key, String value);
	public ArrayList<Evaluation> searchEvaluation(String key, String value);
	public ArrayList<Compensation> searchCompensation(String key, String value);

	//update
	public boolean update(Event event);
	public boolean update(Evaluation evaluation);
	public boolean update(Compensation compensation);

}