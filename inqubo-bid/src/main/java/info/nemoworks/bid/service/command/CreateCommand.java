package info.nemoworks.bid.service.command;

import info.nemoworks.bid.model.Bid;
import lombok.Getter;
import lombok.NonNull;

public class CreateCommand extends BidCommand {

    @Getter
    @NonNull
    private final String title;

    @Getter
    private final String creator;

    public CreateCommand(@NonNull Bid target, @NonNull String title, String creator) {
        super(target);
        this.title = title;
        this.creator = creator;
    }
}
