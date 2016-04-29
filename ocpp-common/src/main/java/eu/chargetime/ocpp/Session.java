package eu.chargetime.ocpp;

/**
 * Created by Thomas Volden on 29-Apr-16.
 */
public class Session {

    private Communicator communicator;
    private Queue queue;

    public Session(Communicator communicator, Queue queue) {
        this.communicator = communicator;
        this.queue = queue;
    }

}
