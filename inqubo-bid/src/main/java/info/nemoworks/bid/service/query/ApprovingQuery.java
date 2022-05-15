package info.nemoworks.bid.service.query;

import info.nemoworks.bid.model.Bid;
import lombok.NonNull;

public class ApprovingQuery extends BidQuery {
    public ApprovingQuery(@NonNull Bid bid) {
        super(bid);
    }
}
