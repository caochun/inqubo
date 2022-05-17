package info.nemoworks.bid.service.command;

import info.nemoworks.bid.model.Bid;
import lombok.Getter;
import lombok.NonNull;

public class TrackCommand extends BidCommand {

    @Getter
    private String addon;

    @Getter
    private String tracker;

    public TrackCommand(@NonNull Bid target) {
        super(target);
        setCommandString("track");
    }

    public static class FinalizeCommand extends TrackCommand {

        public FinalizeCommand(@NonNull Bid target) {
            super(target);
            setCommandString("finalize");
        }
    }
}
