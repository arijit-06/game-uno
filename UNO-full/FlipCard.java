public class FlipCard extends BaseCard {
    public enum Side { LIGHT, DARK }
    public enum LightColor { TEAL, PINK, PURPLE, ORANGE, WILD }
    public enum DarkColor { BLUE, GREEN, RED, YELLOW, WILD }
    public enum ExtendedType { 
        // Base types
        NUMBER, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR,
        // Flip exclusive
        FLIP, WILD_DRAW_TWO, SKIP_EVERYONE, DRAW_FIVE, WILD_DRAW_COLOR
    }
    
    private LightColor lightColor;
    private DarkColor darkColor;
    private ExtendedType lightType;
    private ExtendedType darkType;
    private int lightNumber;
    private int darkNumber;
    private Side currentSide;
    
    public FlipCard(LightColor lightColor, ExtendedType lightType, int lightNumber,
                    DarkColor darkColor, ExtendedType darkType, int darkNumber) {
        super(mapToBaseColor(lightColor), mapToBaseType(lightType), lightNumber);
        this.lightColor = lightColor;
        this.darkColor = darkColor;
        this.lightType = lightType;
        this.darkType = darkType;
        this.lightNumber = lightNumber;
        this.darkNumber = darkNumber;
        this.currentSide = Side.LIGHT;
        updateBaseProperties();
    }
    
    private static Color mapToBaseColor(LightColor lightColor) {
        switch (lightColor) {
            case TEAL: return Color.BLUE;
            case PINK: return Color.RED;
            case PURPLE: return Color.YELLOW;
            case ORANGE: return Color.GREEN;
            case WILD: return Color.WILD;
            default: return Color.RED;
        }
    }
    
    private static Type mapToBaseType(ExtendedType extType) {
        switch (extType) {
            case NUMBER: return Type.NUMBER;
            case SKIP: return Type.SKIP;
            case REVERSE: return Type.REVERSE;
            case DRAW_TWO: return Type.DRAW_TWO;
            case WILD: return Type.WILD;
            case WILD_DRAW_FOUR: return Type.WILD_DRAW_FOUR;
            default: return Type.WILD; // For flip-specific cards
        }
    }
    
    public void flip() {
        currentSide = (currentSide == Side.LIGHT) ? Side.DARK : Side.LIGHT;
        updateBaseProperties();
        precedence = calculatePrecedence();
    }
    
    private void updateBaseProperties() {
        if (currentSide == Side.LIGHT) {
            this.color = mapToBaseColor(lightColor);
            this.type = mapToBaseType(lightType);
            this.number = lightNumber;
        } else {
            this.color = mapDarkToBaseColor(darkColor);
            this.type = mapToBaseType(darkType);
            this.number = darkNumber;
        }
    }
    
    private Color mapDarkToBaseColor(DarkColor darkColor) {
        switch (darkColor) {
            case BLUE: return Color.BLUE;
            case GREEN: return Color.GREEN;
            case RED: return Color.RED;
            case YELLOW: return Color.YELLOW;
            case WILD: return Color.WILD;
            default: return Color.RED;
        }
    }
    
    @Override
    protected int calculatePrecedence() {
        if (currentSide == Side.LIGHT) {
            switch (lightType) {
                case WILD_DRAW_TWO: return 110;
                case WILD: return 100;
                case FLIP: return 90;
                case DRAW_TWO: return 80;
                case SKIP: return 70;
                case REVERSE: return 60;
                case NUMBER: return lightNumber;
                default: return 0;
            }
        } else {
            switch (darkType) {
                case WILD_DRAW_COLOR: return 120;
                case WILD: return 100;
                case FLIP: return 95;
                case DRAW_FIVE: return 85;
                case SKIP_EVERYONE: return 75;
                case REVERSE: return 60;
                case NUMBER: return darkNumber;
                default: return 0;
            }
        }
    }
    
    @Override
    public boolean canPlayOn(BaseCard topCard) {
        if (!(topCard instanceof FlipCard)) return false;
        
        FlipCard flipTop = (FlipCard) topCard;
        if (currentSide != flipTop.currentSide) return false;
        
        // Use classic card logic for base validation
        ClassicCard tempCard = new ClassicCard(color, type, number);
        return tempCard.canPlayOn(topCard);
    }
    
    // Getters for flip-specific properties
    public Side getCurrentSide() { return currentSide; }
    public LightColor getLightColor() { return lightColor; }
    public DarkColor getDarkColor() { return darkColor; }
    public ExtendedType getLightType() { return lightType; }
    public ExtendedType getDarkType() { return darkType; }
    
    @Override
    public String toString() {
        if (currentSide == Side.LIGHT) {
            if (lightType == ExtendedType.NUMBER) return "L:" + lightColor + "_" + lightNumber;
            return "L:" + lightColor + "_" + lightType;
        } else {
            if (darkType == ExtendedType.NUMBER) return "D:" + darkColor + "_" + darkNumber;
            return "D:" + darkColor + "_" + darkType;
        }
    }
}