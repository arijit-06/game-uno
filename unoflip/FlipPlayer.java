import java.util.*;

public abstract class FlipPlayer {
    protected String name;
    protected FlipCardList hand;
    protected boolean isComputer;
    
    public FlipPlayer(String name, boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
        this.hand = new FlipCardList();
    }
    
    public void addCard(FlipCard card) {
        hand.addCard(card);
    }
    
    public abstract FlipCard playCard(FlipCard topCard, UnoFlipGame game);
    
    public abstract FlipCard.LightColor chooseLightColor();
    public abstract FlipCard.DarkColor chooseDarkColor();
    
    public int getHandSize() {
        return hand.size();
    }
    
    public boolean hasCards() {
        return !hand.isEmpty();
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isComputer() {
        return isComputer;
    }
    
    protected FlipCard findPlayableCard(FlipCard topCard) {
        return hand.findPlayableCard(topCard);
    }
    
    protected FlipCard removeCard(FlipCard card) {
        return hand.removeCard(card);
    }
    
    public boolean hasWon() {
        return hand.isEmpty();
    }
    
    public void flipHand() {
        hand.flipAllCards();
    }
    
    public List<FlipCard> getCards() {
        return hand.getCards();
    }
    
    @Override
    public String toString() {
        return name + " (" + (isComputer ? "Computer" : "Human") + ") - Cards: " + hand.size();
    }
}