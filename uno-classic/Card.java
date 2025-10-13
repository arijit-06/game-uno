public class Card implements Comparable<Card> {
    public enum Color { RED, BLUE, GREEN, YELLOW, WILD }
    public enum Type { NUMBER, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR }
    
    private Color color;
    private Type type;
    private int number;
    private int precedence;
    
    public Card(Color color, Type type, int number) {
        this.color = color;
        this.type = type;
        this.number = number;
        this.precedence = calculatePrecedence();
    }
    
    private int calculatePrecedence() {
        switch (type) {
            case WILD_DRAW_FOUR: return 100;
            case WILD: return 90;
            case DRAW_TWO: return 80;
            case SKIP: return 70;
            case REVERSE: return 60;
            case NUMBER: return number;
            default: return 0;
        }
    }
    
    public boolean canPlayOn(Card topCard) {
        // Wild cards can always be played
        if (type == Type.WILD || type == Type.WILD_DRAW_FOUR) {
            return true;
        }
        
        // Same color can always be played
        if (color == topCard.color) {
            return true;
        }
        
        // Action cards: Same type can be played (Skip on Skip, Reverse on Reverse, Draw2 on Draw2)
        if (type == topCard.type && (type == Type.SKIP || type == Type.REVERSE || type == Type.DRAW_TWO)) {
            return true;
        }
        
        // Number cards: Only same number allowed (7 on 7, 3 on 3, etc.)
        if (type == Type.NUMBER && topCard.type == Type.NUMBER && number == topCard.number) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public int compareTo(Card other) {
        return Integer.compare(other.precedence, this.precedence); // Higher precedence first
    }
    
    public Color getColor() { return color; }
    public Type getType() { return type; }
    public int getNumber() { return number; }
    public int getPrecedence() { return precedence; }
    public void setColor(Color color) { this.color = color; }
    
    @Override
    public String toString() {
        if (type == Type.NUMBER) return color + "_" + number;
        return color + "_" + type;
    }
}