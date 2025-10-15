public class MercyCard implements Comparable<MercyCard> {
    public enum Color { RED, BLUE, GREEN, YELLOW, WILD }
    public enum Type { 
        NUMBER, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR,
        // No Mercy exclusive cards
        SKIP_EVERYONE, DRAW_TEN, DISCARD_ALL, WILD_DRAW_TEN,
        MERCY_RULE, STACKING_DRAW_TWO, STACKING_DRAW_FOUR
    }
    
    private Color color;
    private Type type;
    private int number;
    private int precedence;
    private boolean isStackable;
    
    public MercyCard(Color color, Type type, int number) {
        this.color = color;
        this.type = type;
        this.number = number;
        this.precedence = calculatePrecedence();
        this.isStackable = (type == Type.DRAW_TWO || type == Type.WILD_DRAW_FOUR || 
                           type == Type.DRAW_TEN || type == Type.WILD_DRAW_TEN ||
                           type == Type.STACKING_DRAW_TWO || type == Type.STACKING_DRAW_FOUR);
    }
    
    private int calculatePrecedence() {
        switch (type) {
            case WILD_DRAW_TEN: return 150;
            case MERCY_RULE: return 140;
            case DRAW_TEN: return 130;
            case WILD_DRAW_FOUR: return 120;
            case STACKING_DRAW_FOUR: return 115;
            case WILD: return 110;
            case DISCARD_ALL: return 100;
            case SKIP_EVERYONE: return 90;
            case STACKING_DRAW_TWO: return 85;
            case DRAW_TWO: return 80;
            case SKIP: return 70;
            case REVERSE: return 60;
            case NUMBER: return number;
            default: return 0;
        }
    }
    
    public boolean canPlayOn(MercyCard topCard) {
        // Wild cards can always be played
        if (type == Type.WILD || type == Type.WILD_DRAW_FOUR || type == Type.WILD_DRAW_TEN) return true;
        
        // Same color
        if (color == topCard.color) return true;
        
        // Action cards can match type across colors (No Mercy rule)
        if (type == topCard.type && type != Type.NUMBER) return true;
        
        // Numbers must match exactly
        if (type == Type.NUMBER && topCard.type == Type.NUMBER && number == topCard.number) return true;
        
        // Special stacking rules
        if (isStackingCard() && topCard.isStackingCard()) return true;
        
        return false;
    }
    
    public boolean isStackingCard() {
        return type == Type.DRAW_TWO || type == Type.WILD_DRAW_FOUR || 
               type == Type.DRAW_TEN || type == Type.WILD_DRAW_TEN ||
               type == Type.STACKING_DRAW_TWO || type == Type.STACKING_DRAW_FOUR;
    }
    
    public int getDrawAmount() {
        switch (type) {
            case DRAW_TWO:
            case STACKING_DRAW_TWO: return 2;
            case WILD_DRAW_FOUR:
            case STACKING_DRAW_FOUR: return 4;
            case DRAW_TEN:
            case WILD_DRAW_TEN: return 10;
            default: return 0;
        }
    }
    
    @Override
    public int compareTo(MercyCard other) {
        return Integer.compare(other.precedence, this.precedence);
    }
    
    // Getters
    public Color getColor() { return color; }
    public Type getType() { return type; }
    public int getNumber() { return number; }
    public int getPrecedence() { return precedence; }
    public boolean isStackable() { return isStackable; }
    
    public void setColor(Color color) { this.color = color; }
    
    @Override
    public String toString() {
        if (type == Type.NUMBER) return color + "_" + number;
        return color + "_" + type;
    }
}