package info.nemoworks.bid.service;

import info.nemoworks.bid.repository.BidRepository;
import info.nemoworks.bid.service.command.*;
import info.nemoworks.bid.service.query.ReviewingQuery;
import info.nemoworks.bid.service.query.CreatingQuery;
import info.nemoworks.bid.service.query.EditingQuery;
import info.nemoworks.bid.model.Addon;
import info.nemoworks.bid.model.Bid;
import info.nemoworks.bid.model.Content;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BidServiceImp implements BidService {


    @Autowired
    public void setBidRepository(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    private BidRepository bidRepository;


    @Override
    public void handleCreateCommand(CreateCommand command) {

        LoggerFactory.getLogger(BidServiceImp.class).info("handling create command :" + command.getTitle());
        Bid bid = command.getTarget();
        bid.setTitle(command.getTitle());
        bid.setCreator(command.getCreator());
        bidRepository.saveBid(bid);
    }


    @Override
    public void handleReviewCommand(ReviewCommand command) {
        Bid bid = bidRepository.getBid(command.getTarget().getId());
        bid.setApproved(true);
        bidRepository.saveBid(bid);
    }

    @Override
    public void handleTrackCommand(TrackCommand command) {
        Bid bid = bidRepository.getBid(command.getTarget().getId());

        if (command instanceof TrackCommand.FinalizeCommand) {
            bid.setClosed(true);
        } else {
            bid.getAddons().add(new Addon(command.getTarget().getId(), command.getAddon(), command.getTracker()));
        }
        bidRepository.saveBid(bid);

    }

    @Override
    public void handleEditCommand(EditCommand command) {
        Bid bid = bidRepository.getBid(command.getTarget().getId());
        bid.setContent(new Content(command.getContent(), command.getEditor()));
        bidRepository.saveBid(bid);
    }


    @Override
    public CreatingQuery queryOnCreating(Bid bid) {
        return new CreatingQuery(bid);
    }

    @Override
    public EditingQuery queryOnEditing(Bid bid) {
        return new EditingQuery(bid);
    }

    @Override
    public ReviewingQuery queryOnApproving(Bid bid) {
        return new ReviewingQuery(bid);
    }

}
