import java.util.*;

public abstract class BasePlayer<T extends BaseCard> {
    protected String name;
    protected List<T> hand;
    protected boolean isComputer;
    
    public BasePlayer(String name, boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
        this.hand = new ArrayList<>();
    }
    
    public void addCard(T card) {
        hand.add(card);
        Collections.sort(hand); // Maintain precedence order
    }
    
    public abstract T playCard(T topCard, BaseGame<T> game);
    
    public abstract BaseCard.Color chooseColor();
    
    protected T findPlayableCard(T topCard) {
        for (T card : hand) {
            if (card.canPlayOn(topCard)) {
                return card;
            }
        }
        return null;
    }
    
    protected T removeCard(T card) {
        if (hand.remove(card)) {
            return card;
        }
        return null;
    }
    
    public int getHandSize() { return hand.size(); }
    public boolean hasCards() { return !hand.isEmpty(); }
    public String getName() { return name; }
    public boolean isComputer() { return isComputer; }
    public boolean hasWon() { return hand.isEmpty(); }
    public List<T> getCards() { return new ArrayList<>(hand); }
    
    @Override
    public String toString() {
        return name + " (" + (isComputer ? "Computer" : "Human") + ") - Cards: " + hand.size();
    }
}