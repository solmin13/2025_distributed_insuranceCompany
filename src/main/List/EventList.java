package main.List;
import java.util.ArrayList;
import main.Compensation;
import main.Evaluation;
import main.Event;

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
	public ArrayList<Event> searchEvaluation(String key, String value);
	public ArrayList<Event> searchCompensation(String key, String value);

	//update
	public boolean update(Event event);
	public boolean update(Evaluation evaluation);
	public boolean update(Compensation compensation);

}