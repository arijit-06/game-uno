public abstract class Player {
    protected String name;
    protected CardList hand;
    protected boolean isComputer;
    
    public Player(String name, boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
        this.hand = new CardList();
    }
    
    public void addCard(Card card) {
        hand.addCard(card);
    }
    
    public abstract Card playCard(Card topCard, UnoGame game);
    
    public abstract Card.Color chooseColor();
    
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
    
    protected Card findPlayableCard(Card topCard) {
        return hand.findPlayableCard(topCard);
    }
    
    protected Card removeCard(Card card) {
        return hand.removeCard(card);
    }
    
    public boolean hasWon() {
        return hand.isEmpty();
    }
    
    @Override
    public String toString() {
        return name + " (" + (isComputer ? "Computer" : "Human") + ") - Cards: " + hand.size();
    }
}