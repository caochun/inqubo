package info.nemoworks.bid.service.command;

import info.nemoworks.bid.model.Bid;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class ReviewCommand extends BidCommand {
    @Getter
    @Setter
    private String reviewer;

    @Setter
    @Getter
    private String comment;

    public ReviewCommand(@NonNull Bid target) {
        super(target);
    }

    public static class ApproveCommand extends ReviewCommand {

        public ApproveCommand(@NonNull Bid target) {
            super(target);
            setCommandString("approve");
        }
    }

    public static class DisapproveCommand extends ReviewCommand {

        public DisapproveCommand(@NonNull Bid target) {
            super(target);
            setCommandString("disapprove");
        }
    }
}
