package info.nemoworks.bid.service.query;

import info.nemoworks.bid.model.Bid;
import lombok.NonNull;

public class ReviewingQuery extends BidQuery {
    public ReviewingQuery(@NonNull Bid bid) {
        super(bid);
    }

}
