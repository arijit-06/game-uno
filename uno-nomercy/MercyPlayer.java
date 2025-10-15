import java.util.*;

public abstract class MercyPlayer {
    protected String name;
    protected MercyCardList hand;
    protected boolean isComputer;
    protected boolean hasCalledUno;
    protected int mercyRuleProtection; // Turns protected from Mercy Rule
    
    public MercyPlayer(String name, boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
        this.hand = new MercyCardList();
        this.hasCalledUno = false;
        this.mercyRuleProtection = 0;
    }
    
    public void addCard(MercyCard card) {
        hand.addCard(card);
        if (hand.size() > 1) hasCalledUno = false;
    }
    
    public abstract MercyCard playCard(MercyCard topCard, UnoMercyGame game);
    
    public abstract MercyCard.Color chooseColor();
    
    public abstract boolean wantsToStack(MercyCard topCard);
    
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
    
    protected MercyCard findPlayableCard(MercyCard topCard) {
        return hand.findPlayableCard(topCard);
    }
    
    protected MercyCard findStackingCard(MercyCard topCard) {
        return hand.findStackingCard(topCard);
    }
    
    protected MercyCard removeCard(MercyCard card) {
        return hand.removeCard(card);
    }
    
    public boolean hasWon() {
        return hand.isEmpty();
    }
    
    public void callUno() {
        hasCalledUno = true;
    }
    
    public boolean hasCalledUno() {
        return hasCalledUno;
    }
    
    public void resetUnoCall() {
        hasCalledUno = false;
    }
    
    public List<MercyCard> discardAllOfColor(MercyCard.Color color) {
        return hand.removeAllOfColor(color);
    }
    
    public void setMercyProtection(int turns) {
        mercyRuleProtection = turns;
    }
    
    public boolean hasMercyProtection() {
        return mercyRuleProtection > 0;
    }
    
    public void decrementMercyProtection() {
        if (mercyRuleProtection > 0) mercyRuleProtection--;
    }
    
    public List<MercyCard> getCards() {
        return hand.getCards();
    }
    
    @Override
    public String toString() {
        String protection = hasMercyProtection() ? " [PROTECTED:" + mercyRuleProtection + "]" : "";
        return name + " (" + (isComputer ? "Computer" : "Human") + ") - Cards: " + hand.size() + protection;
    }
}