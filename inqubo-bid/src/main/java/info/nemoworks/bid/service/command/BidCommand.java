package info.nemoworks.bid.service.command;

import info.nemoworks.bid.model.Bid;
import info.nemoworks.inqubo.Command;
import lombok.NonNull;

public class BidCommand extends Command<Bid> {

    public BidCommand(@NonNull Bid target) {
        super(target);
    }
}
