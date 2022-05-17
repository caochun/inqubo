package info.nemoworks.bid.service.query;

import info.nemoworks.bid.model.Bid;
import info.nemoworks.inqubo.Query;
import lombok.NonNull;

public class BidQuery extends Query<Bid> {

    public BidQuery(@NonNull Bid bid) {
        super(bid);
    }

}
