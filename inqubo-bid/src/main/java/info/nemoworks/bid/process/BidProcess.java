package info.nemoworks.bid.process;

import info.nemoworks.bid.model.Bid;
import info.nemoworks.bid.service.command.CreateCommand;
import info.nemoworks.bid.service.command.EditCommand;
import info.nemoworks.bid.service.command.ReviewCommand;
import info.nemoworks.bid.service.command.TrackCommand;
import info.nemoworks.bid.service.query.CreatingQuery;
import info.nemoworks.bid.service.query.EditingQuery;
import info.nemoworks.bid.service.query.ReviewingQuery;
import info.nemoworks.bid.service.query.TracingQuery;
import info.nemoworks.inqubo.AbstractProcess;
import info.nemoworks.inqubo.Task;
import org.apache.commons.scxml2.model.ModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Component
public class BidProcess extends AbstractProcess<Bid> {

    private static final String SCXML_MODEL = "scxml/bidchart.xml";

    public BidProcess(Bid bid) throws ModelException {
        super(BidProcess.class.getClassLoader().getResource(SCXML_MODEL), bid);
    }

    @Override
    public Task<? extends Bid> getTask(String state) {
        switch (state) {
            case "init":
                return Task.<Bid>builder()
                        .subject("Creator")
                        .object(getObj())
                        .expectedCommands(List.of(new CreateCommand(getObj())))
                        .query(new CreatingQuery(getObj()))
                        .process(this)
                        .build();
            case "editing":
                return Task.<Bid>builder()
                        .subject("Editor")
                        .object(getObj())
                        .expectedCommands(List.of(new EditCommand.SaveCommand(getObj()),
                                new EditCommand.SubmitCommand(getObj())))
                        .query(new EditingQuery(getObj()))
                        .process(this)
                        .build();
            case "reviewing":
                return Task.<Bid>builder()
                        .subject("Reviewer")
                        .object(getObj())
                        .expectedCommands(List.of(new ReviewCommand.ApproveCommand(getObj()),
                                new ReviewCommand.DisapproveCommand(getObj())))
                        .query(new ReviewingQuery(getObj()))
                        .process(this)
                        .build();
            case "tracking":
                return Task.<Bid>builder()
                        .subject("Tracker")
                        .object(getObj())
                        .expectedCommands(List.of(new TrackCommand(getObj()),
                                new TrackCommand.FinalizeCommand(getObj())))
                        .query(new TracingQuery(getObj()))
                        .process(this)
                        .build();
            case "closed":
                return null;
        }
        return null;
    }


}
