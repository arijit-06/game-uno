public abstract class BaseCard implements Comparable<BaseCard> {
    public enum Color { RED, BLUE, GREEN, YELLOW, WILD }
    public enum Type { NUMBER, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR }
    
    protected Color color;
    protected Type type;
    protected int number;
    protected int precedence;
    
    public BaseCard(Color color, Type type, int number) {
        this.color = color;
        this.type = type;
        this.number = number;
        this.precedence = calculatePrecedence();
    }
    
    protected abstract int calculatePrecedence();
    
    public abstract boolean canPlayOn(BaseCard topCard);
    
    @Override
    public int compareTo(BaseCard other) {
        return Integer.compare(other.precedence, this.precedence);
    }
    
    // Getters
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