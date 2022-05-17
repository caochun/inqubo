package info.nemoworks.bid.service;


import info.nemoworks.bid.service.command.*;
import info.nemoworks.bid.service.query.ReviewingQuery;
import info.nemoworks.bid.service.query.CreatingQuery;
import info.nemoworks.bid.service.query.EditingQuery;
import info.nemoworks.bid.model.Bid;

public interface BidService {

    void handleCreateCommand(CreateCommand command);

    void handleReviewCommand(ReviewCommand command);

    void handleTrackCommand(TrackCommand command);

    void handleEditCommand(EditCommand command);

    CreatingQuery queryOnCreating(Bid bid);

    EditingQuery queryOnEditing(Bid bid);

    ReviewingQuery queryOnApproving(Bid bid);

}
