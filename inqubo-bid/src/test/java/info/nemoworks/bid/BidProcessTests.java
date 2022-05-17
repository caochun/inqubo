package info.nemoworks.bid;

import info.nemoworks.bid.model.Bid;
import info.nemoworks.bid.process.BidProcess;
import info.nemoworks.inqubo.Command;
import info.nemoworks.inqubo.Task;
import org.apache.commons.scxml2.model.ModelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BidProcessTests {

    Bid bid;

    @BeforeEach
    public void setup() {
        bid = new Bid();
    }

    @Test
    public void newBid_thenStartProcess() throws ModelException {

        BidProcess bidProcess = new BidProcess(bid);

        Task t = bidProcess.getPendingTask();

        t.complete((Command) t.getExpectedCommands().get(0));

        t = bidProcess.getPendingTask();

        t.complete((Command) t.getExpectedCommands().get(0));

        t = bidProcess.getPendingTask();

        t.complete((Command) t.getExpectedCommands().get(1));

        t = bidProcess.getPendingTask();

        t.complete((Command) t.getExpectedCommands().get(0));

        t = bidProcess.getPendingTask();

        t.complete((Command) t.getExpectedCommands().get(0));

        t = bidProcess.getPendingTask();

        t.complete((Command) t.getExpectedCommands().get(1));

    }

}
