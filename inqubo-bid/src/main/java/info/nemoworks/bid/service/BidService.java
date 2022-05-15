package info.nemoworks.bid.service;


import info.nemoworks.bid.service.command.*;
import info.nemoworks.bid.service.query.ApprovingQuery;
import info.nemoworks.bid.service.query.CreatingQuery;
import info.nemoworks.bid.service.query.EditingQuery;
import info.nemoworks.bid.model.Bid;

public interface BidService {

    void handleCreateCommand(CreateCommand command);

    void handleApproveCommand(ApproveCommand command);

    void handleCloseCommand(CloseCommand command);

    void handleEditContentCommand(EditContentCommand command);

    void handleAppendAddonCommand(AppendAddonCommand command);

    CreatingQuery queryOnCreating(Bid bid);

    EditingQuery queryOnEditing(Bid bid);

    ApprovingQuery queryOnApproving(Bid bid);

}
