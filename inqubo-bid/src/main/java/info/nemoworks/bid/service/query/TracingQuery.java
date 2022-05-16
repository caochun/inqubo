package info.nemoworks.bid.service.query;

import info.nemoworks.bid.model.Bid;
import lombok.NonNull;

public class TracingQuery extends  BidQuery{
    public TracingQuery(@NonNull Bid bid) {
        super(bid);
    }
}
