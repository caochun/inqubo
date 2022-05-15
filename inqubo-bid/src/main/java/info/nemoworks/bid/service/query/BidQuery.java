package info.nemoworks.bid.service.query;

import info.nemoworks.bid.model.Bid;
import info.nemoworks.inqubo.Query;
import lombok.NonNull;

public class BidQuery extends Query<Bid> {

    public BidQuery(@NonNull Bid bid) {
        super(bid);
    }

    public static final String APPROVING = "approve";
    public static final String EDITING = "editing";
    public static final String CREATING = "created";

}
