public class ClassicCard extends BaseCard {
    
    public ClassicCard(Color color, Type type, int number) {
        super(color, type, number);
    }
    
    @Override
    protected int calculatePrecedence() {
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
    
    @Override
    public boolean canPlayOn(BaseCard topCard) {
        // Wild cards can always be played
        if (type == Type.WILD || type == Type.WILD_DRAW_FOUR) return true;
        
        // Same color
        if (color == topCard.color) return true;
        
        // Action cards can match type across colors
        if (type == topCard.type && (type == Type.SKIP || type == Type.REVERSE || type == Type.DRAW_TWO)) return true;
        
        // Numbers must match exactly
        if (type == Type.NUMBER && topCard.type == Type.NUMBER && number == topCard.number) return true;
        
        return false;
    }
}