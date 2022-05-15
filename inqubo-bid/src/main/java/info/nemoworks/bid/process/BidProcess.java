package info.nemoworks.bid.process;

import info.nemoworks.inqubo.AbstractProcess;
import org.apache.commons.scxml2.model.ModelException;

import java.net.URL;

public class BidProcess extends AbstractProcess {

    public BidProcess(URL scxmlDocument) throws ModelException {
        super(scxmlDocument);
    }

    @Override
    public boolean invoke(String state) {
        return false;
    }
}
