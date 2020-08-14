package mandolin.exception;

/**
 * Created by caresys on 7/6/2020.
 */
public class EventNotFoundException extends RuntimeException{
	public EventNotFoundException(){
		super("Event not found");
	}
}
