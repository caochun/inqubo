package info.nemoworks.bid.service.command;


import info.nemoworks.bid.model.Bid;
import lombok.Getter;

public class CloseCommand extends BidCommand {
    @Getter
    private final String closer;

    public CloseCommand(Bid bid, String closer) {
        super(bid);
        this.closer = closer;
    }
}