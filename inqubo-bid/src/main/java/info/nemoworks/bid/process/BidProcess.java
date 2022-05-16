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

public class BidProcess extends AbstractProcess<Bid> {

    private static final String SCXML_MODEL = "scxml/bidchart.xml";

    public BidProcess(URL scxmlDocument, Bid bid) throws ModelException {
        super(scxmlDocument, bid);
    }

    @Override
    public List<Task<? extends Bid>> getTasks(String state) {
        switch (state) {
            case "created":
                return List.of(Task.<Bid>builder().subject("A").object(getObj()).command(null).query(new EditingQuery(getObj())).build());
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


}
