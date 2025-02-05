import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface Observer {
    void update(AuctionEvent event);
}

// Concrete Observer: Bidder
class Bidder implements Observer {
    private String bidderName;

    public Bidder(String bidderName) {
        this.bidderName = bidderName;
    }

    public String getBidderName() {
        return bidderName;
    }

    @Override
    public void update(AuctionEvent event) {
        switch (event.getType()) {
            case ITEM_AVAILABLE:
                System.out.println(bidderName + ": Item " + event.getItem() + " is available.");
                break;
            case BIDDING_STARTED:
                System.out.println(bidderName + ": Bidding started for " + event.getItem() + ".");
                break;
            case BIDDING_ENDED:
                System.out.println(bidderName + ": Bidding ended for " + event.getItem() + ".");
                break;
            case BID_PLACED:
                System.out.println(bidderName + ": Placed a bid of $" + event.getBidAmount() + " for " + event.getItem() + ".");
                break;
            default:
                break;
        }
    }
}

// Subject Interface
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(AuctionEvent event);
}

// Concrete Subject: Auction
class Auction implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String item;
    private double currentBid;
    private Bidder highestBidder;

    public Auction(String item) {
        this.item = item;
        this.currentBid = 0.0;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(AuctionEvent event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    public void startAuction() {
        AuctionEvent event = new AuctionEvent(AuctionEventType.BIDDING_STARTED, item);
        notifyObservers(event);
    }

    public void endAuction() {
        AuctionEvent event = new AuctionEvent(AuctionEventType.BIDDING_ENDED, item);
        notifyObservers(event);

        if (highestBidder != null) {
            System.out.println("Auction winner: " + highestBidder.getBidderName() + " with a bid of $" + currentBid);
        } else {
            System.out.println("No bids were placed. Auction ended without a winner.");
        }
    }

    public void placeBid(Bidder bidder, double bidAmount) {
        if (bidAmount > currentBid) {
            currentBid = bidAmount;
            highestBidder = bidder;
            AuctionEvent event = new AuctionEvent(AuctionEventType.BID_PLACED, item, bidAmount);
            notifyObservers(event);
        } else {
            System.out.println(bidder.getBidderName() + ": Invalid bid. Bid amount must be greater than the current bid.");
        }
    }

    public double getCurrentBid() {
        return currentBid;
    }
}

// AuctionEvent
class AuctionEvent {
    private AuctionEventType type;
    private String item;
    private double bidAmount;

    public AuctionEvent(AuctionEventType type, String item) {
        this.type = type;
        this.item = item;
    }

    public AuctionEvent(AuctionEventType type, String item, double bidAmount) {
        this.type = type;
        this.item = item;
        this.bidAmount = bidAmount;
    }

    public AuctionEventType getType() {
        return type;
    }

    public String getItem() {
        return item;
    }

    public double getBidAmount() {
        return bidAmount;
    }
}

// AuctionEventType enum
enum AuctionEventType {
    ITEM_AVAILABLE, BIDDING_STARTED, BIDDING_ENDED, BID_PLACED
}

// Abstract Auction class (Template Method)
abstract class AbstractAuction {
    protected Auction auction;

    public AbstractAuction(Auction auction) {
        this.auction = auction;
    }

    public void executeAuction() {
        prepareAuction();
        auction.startAuction();
        conductBidding();
        auction.endAuction();
    }

    protected abstract void prepareAuction();
    protected abstract void conductBidding();
}

// StandardAuction class
class StandardAuction extends AbstractAuction {

    public StandardAuction(Auction auction) {
        super(auction);
    }

    @Override
    protected void prepareAuction() {
        System.out.println("Standard Auction: Preparing auction.");
    }

    @Override
    protected void conductBidding() {
        System.out.println("Standard Auction: Conducting bidding.");
        auction.placeBid(new Bidder("Alice"), 120); 
        auction.placeBid(new Bidder("Bob"), 150);
        auction.placeBid(new Bidder("Alice"), 180); 
    }
}

// ReserveAuction class
class ReserveAuction extends AbstractAuction {
    private double reservePrice;

    public ReserveAuction(Auction auction, double reservePrice) {
        super(auction);
        this.reservePrice = reservePrice;
    }

    @Override
    protected void prepareAuction() {
        System.out.println("Reserve Auction: Preparing auction.");
    }

    @Override
    protected void conductBidding() {
        System.out.println("Reserve Auction: Conducting bidding.");
        auction.placeBid(new Bidder("Alice"), 120); 
        auction.placeBid(new Bidder("Bob"), 180);
        auction.placeBid(new Bidder("Alice"), 220);

        if (auction.getCurrentBid() >= reservePrice) {
            System.out.println("Reserve price met. Auction successful.");
        } else {
            System.out.println("Reserve price not met. Auction failed.");
        }
    }
}

// Client
public class AuctionSystem {
    public static void main(String[] args) {
        Auction auction = new Auction("Painting");

        Bidder bidder1 = new Bidder("Alice");
        Bidder bidder2 = new Bidder("Bob");

        auction.attach(bidder1);
        auction.attach(bidder2);

        StandardAuction standardAuction = new StandardAuction(auction);
        standardAuction.executeAuction();
    }
}