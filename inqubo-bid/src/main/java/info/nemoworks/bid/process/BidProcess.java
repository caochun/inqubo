package info.nemoworks.bid.process;

import info.nemoworks.bid.model.Bid;
import info.nemoworks.bid.service.command.EditContentCommand;
import info.nemoworks.bid.service.query.*;
import info.nemoworks.inqubo.AbstractProcess;
import info.nemoworks.inqubo.Task;
import org.apache.commons.scxml2.model.ModelException;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidProcess extends AbstractProcess<Bid> {

    private static final String SCXML_MODEL = "scxml/bidchart.xml";

    public BidProcess(URL scxmlDocument, Bid bid) throws ModelException {
        super(scxmlDocument, bid);
    }

    @Override
    public List<Task<? extends Bid>> getTasks(String state) {
        switch (state) {
            case "created":
                return List.of(Task.<Bid>builder().subject("Creator").object(getObj()).command(null).query(new CreatingQuery(getObj())).build());
            case "editing":
                return List.of(Task.<Bid>builder().subject("Editor").object(getObj()).command(null).query(new EditingQuery(getObj())).build());
            case "reviewing":
                return List.of(Task.<Bid>builder().subject("Reviewer").object(getObj()).command(null).query(new ApprovingQuery(getObj())).build());
            case "tracking":
            case "closed":
                return List.of(Task.<Bid>builder().subject("Tracker").object(getObj()).command(null).query(new TracingQuery(getObj())).build());
        }
        return null;
    }


}
