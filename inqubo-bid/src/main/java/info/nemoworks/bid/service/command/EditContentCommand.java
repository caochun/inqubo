package info.nemoworks.bid.service.command;

import info.nemoworks.bid.model.Bid;
import lombok.Getter;
import lombok.NonNull;

public class EditContentCommand extends BidCommand {

    @Getter
    @NonNull
    private final String content;

    @Getter
    private final String editor;

    public EditContentCommand(@NonNull Bid target, @NonNull String content, String editor) {
        super(target);
        this.content = content;
        this.editor = editor;
    }
}