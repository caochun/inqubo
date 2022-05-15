package info.nemoworks.bid.service.command;

import info.nemoworks.bid.model.Bid;
import lombok.Getter;
import lombok.NonNull;

public class ApproveCommand extends BidCommand {

    @Getter
    private final String approver;

    public ApproveCommand(@NonNull Bid bid, String approver) {
        super(bid);
        this.approver = approver;
    }
}
