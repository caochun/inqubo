package info.nemoworks.bid.service.command;

import info.nemoworks.bid.model.Bid;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class CreateCommand extends BidCommand {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String creator;

    public CreateCommand(@NonNull Bid target) {
        super(target);
        this.setCommandString("create");
    }
}
