package info.nemoworks.bid.service.command;

import info.nemoworks.bid.model.Bid;
import lombok.Getter;
import lombok.NonNull;

public class EditCommand extends BidCommand {

    @Getter
    private String content;

    @Getter
    private String editor;

    public EditCommand(@NonNull Bid target) {
        super(target);
    }

    public static class SaveCommand extends EditCommand {
        public SaveCommand(@NonNull Bid target) {
            super(target);
            setCommandString("save");
        }
    }

    public static class SubmitCommand extends EditCommand {
        public SubmitCommand(@NonNull Bid target) {
            super(target);
            setCommandString("submit");
        }
    }
}
