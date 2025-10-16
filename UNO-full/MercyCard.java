public class MercyCard extends BaseCard {
    public enum ExtendedType {
        // Base types
        NUMBER, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR,
        // No Mercy exclusive
        SKIP_EVERYONE, DRAW_TEN, DISCARD_ALL, WILD_DRAW_TEN,
        MERCY_RULE, STACKING_DRAW_TWO, STACKING_DRAW_FOUR
    }
    
    private ExtendedType extendedType;
    private boolean isStackable;
    
    public MercyCard(Color color, ExtendedType extendedType, int number) {
        super(color, mapToBaseType(extendedType), number);
        this.extendedType = extendedType;
        this.isStackable = (extendedType == ExtendedType.DRAW_TWO || 
                           extendedType == ExtendedType.WILD_DRAW_FOUR || 
                           extendedType == ExtendedType.DRAW_TEN || 
                           extendedType == ExtendedType.WILD_DRAW_TEN ||
                           extendedType == ExtendedType.STACKING_DRAW_TWO || 
                           extendedType == ExtendedType.STACKING_DRAW_FOUR);
    }
    
    private static Type mapToBaseType(ExtendedType extType) {
        switch (extType) {
            case NUMBER: return Type.NUMBER;
            case SKIP: return Type.SKIP;
            case REVERSE: return Type.REVERSE;
            case DRAW_TWO: return Type.DRAW_TWO;
            case WILD: return Type.WILD;
            case WILD_DRAW_FOUR: return Type.WILD_DRAW_FOUR;
            default: return Type.WILD; // For mercy-specific cards
        }
    }
    
    @Override
    protected int calculatePrecedence() {
        switch (extendedType) {
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
    
    @Override
    public boolean canPlayOn(BaseCard topCard) {
        // Use classic card logic for base validation
        ClassicCard tempCard = new ClassicCard(color, type, number);
        if (tempCard.canPlayOn(topCard)) return true;
        
        // Special stacking rules for No Mercy
        if (topCard instanceof MercyCard) {
            MercyCard mercyTop = (MercyCard) topCard;
            if (isStackingCard() && mercyTop.isStackingCard()) return true;
        }
        
        return false;
    }
    
    public boolean isStackingCard() {
        return isStackable;
    }
    
    public int getDrawAmount() {
        switch (extendedType) {
            case DRAW_TWO:
            case STACKING_DRAW_TWO: return 2;
            case WILD_DRAW_FOUR:
            case STACKING_DRAW_FOUR: return 4;
            case DRAW_TEN:
            case WILD_DRAW_TEN: return 10;
            default: return 0;
        }
    }
    
    public ExtendedType getExtendedType() { return extendedType; }
    
    @Override
    public String toString() {
        if (extendedType == ExtendedType.NUMBER) return color + "_" + number;
        return color + "_" + extendedType;
    }
}