package info.nemoworks.bid.repository;

import info.nemoworks.bid.model.Bid;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class BidRepository {
    private final Map<String, Bid> bids = new HashMap<>();

    public void saveBid(Bid bid) {
        bids.put(bid.getId(), bid);
    }

    public Bid getBid(String bidId) {
        return bids.get(bidId);
    }
}
