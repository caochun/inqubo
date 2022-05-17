package info.nemoworks.bid.service;


import info.nemoworks.bid.service.command.*;
import info.nemoworks.bid.service.query.ReviewingQuery;
import info.nemoworks.bid.service.query.CreatingQuery;
import info.nemoworks.bid.service.query.EditingQuery;
import info.nemoworks.bid.model.Bid;
import info.nemoworks.bid.service.query.TracingQuery;

public interface BidService {

    void handleCreateCommand(CreateCommand command);

    void handleReviewCommand(ReviewCommand command);

    void handleTrackCommand(TrackCommand command);

    void handleEditCommand(EditCommand command);

    Bid handleCreatingQuery(CreatingQuery creatingQuery);

    Bid handleEditingQuery(EditingQuery editingQuery);

    Bid handleReviewingQuery(ReviewingQuery reviewingQuery);

    Bid handleTracingQuery(TracingQuery tracingQuery);

}
