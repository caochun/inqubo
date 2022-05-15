package info.nemoworks.bid.service.query;

import info.nemoworks.bid.model.Bid;
import lombok.NonNull;

public class EditingQuery extends BidQuery {
    public EditingQuery(@NonNull Bid bid) {
        super(bid);
    }
}
