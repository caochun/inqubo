package info.nemoworks.bid.process;

import info.nemoworks.bid.model.Bid;
import info.nemoworks.bid.service.command.EditContentCommand;
import info.nemoworks.bid.service.query.BidQuery;
import info.nemoworks.bid.service.query.EditingQuery;
import info.nemoworks.inqubo.AbstractProcess;
import info.nemoworks.inqubo.Task;
import org.apache.commons.scxml2.model.ModelException;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidProcess extends AbstractProcess {

    private static final String SCXML_MODEL = "scxml/bidchart.xml";

    public BidProcess(URL scxmlDocument) throws ModelException {
        super(scxmlDocument);
    }

    private Bid bid;

    @Override
    public List<Task> getTasks(String state) {
        switch (state) {
            case "created":
                return List.of(Task.builder().subject("A").object(bid).command(null).query(new EditingQuery(bid)).build());
            case "editing":
                ;
            case "reviewing":
                ;
            case "tracking":
                ;
            case "closed":
                ;
        }
        return null;
    }

    private Map<String, Task> tasks = new HashMap<>();

}
