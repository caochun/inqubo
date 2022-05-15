package info.nemoworks.bid.service.command;

import info.nemoworks.bid.model.Bid;
import lombok.Getter;
import lombok.NonNull;

public class AppendAddonCommand extends BidCommand {

    @Getter
    @NonNull
    private final String addon;

    @Getter
    private final String author;

    public AppendAddonCommand(@NonNull Bid target, @NonNull String addon, String author) {
        super(target);
        this.addon = addon;
        this.author = author;
    }
}
