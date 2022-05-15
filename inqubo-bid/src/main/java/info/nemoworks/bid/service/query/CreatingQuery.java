package info.nemoworks.bid.service.query;

import info.nemoworks.bid.model.Bid;
import lombok.NonNull;

public class CreatingQuery extends BidQuery {
    public CreatingQuery(@NonNull Bid bid) {
        super(bid);
    }
}