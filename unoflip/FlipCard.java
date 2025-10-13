public class FlipCard implements Comparable<FlipCard> {
    public enum Side { LIGHT, DARK }
    public enum LightColor { TEAL, PINK, PURPLE, ORANGE, WILD }
    public enum DarkColor { BLUE, GREEN, RED, YELLOW, WILD }
    public enum LightType { NUMBER, REVERSE, SKIP, DRAW_TWO, FLIP, WILD, WILD_DRAW_TWO }
    public enum DarkType { NUMBER, REVERSE, SKIP_EVERYONE, DRAW_FIVE, FLIP, WILD, WILD_DRAW_COLOR }
    
    // Light side properties
    private LightColor lightColor;
    private LightType lightType;
    private int lightNumber;
    
    // Dark side properties  
    private DarkColor darkColor;
    private DarkType darkType;
    private int darkNumber;
    
    private Side currentSide;
    private int precedence;
    
    public FlipCard(LightColor lightColor, LightType lightType, int lightNumber,
                    DarkColor darkColor, DarkType darkType, int darkNumber) {
        this.lightColor = lightColor;
        this.lightType = lightType;
        this.lightNumber = lightNumber;
        this.darkColor = darkColor;
        this.darkType = darkType;
        this.darkNumber = darkNumber;
        this.currentSide = Side.LIGHT;
        this.precedence = calculatePrecedence();
    }
    
    private int calculatePrecedence() {
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
    
    public void flip() {
        currentSide = (currentSide == Side.LIGHT) ? Side.DARK : Side.LIGHT;
        precedence = calculatePrecedence();
    }
    
    public boolean canPlayOn(FlipCard topCard) {
        if (currentSide != topCard.currentSide) return false;
        
        if (currentSide == Side.LIGHT) {
            if (lightType == LightType.WILD || lightType == LightType.WILD_DRAW_TWO) return true;
            if (lightColor == topCard.lightColor) return true;
            if (lightType == topCard.lightType && (lightType == LightType.REVERSE || 
                lightType == LightType.SKIP || lightType == LightType.DRAW_TWO || lightType == LightType.FLIP)) return true;
            if (lightType == LightType.NUMBER && topCard.lightType == LightType.NUMBER && 
                lightNumber == topCard.lightNumber) return true;
        } else {
            if (darkType == DarkType.WILD || darkType == DarkType.WILD_DRAW_COLOR) return true;
            if (darkColor == topCard.darkColor) return true;
            if (darkType == topCard.darkType && (darkType == DarkType.REVERSE || 
                darkType == DarkType.SKIP_EVERYONE || darkType == DarkType.DRAW_FIVE || darkType == DarkType.FLIP)) return true;
            if (darkType == DarkType.NUMBER && topCard.darkType == DarkType.NUMBER && 
                darkNumber == topCard.darkNumber) return true;
        }
        return false;
    }
    
    @Override
    public int compareTo(FlipCard other) {
        return Integer.compare(other.precedence, this.precedence);
    }
    
    // Getters
    public Side getCurrentSide() { return currentSide; }
    public LightColor getLightColor() { return lightColor; }
    public DarkColor getDarkColor() { return darkColor; }
    public LightType getLightType() { return lightType; }
    public DarkType getDarkType() { return darkType; }
    public int getLightNumber() { return lightNumber; }
    public int getDarkNumber() { return darkNumber; }
    public int getPrecedence() { return precedence; }
    
    public void setLightColor(LightColor color) { this.lightColor = color; }
    public void setDarkColor(DarkColor color) { this.darkColor = color; }
    
    @Override
    public String toString() {
        if (currentSide == Side.LIGHT) {
            if (lightType == LightType.NUMBER) return "L:" + lightColor + "_" + lightNumber;
            return "L:" + lightColor + "_" + lightType;
        } else {
            if (darkType == DarkType.NUMBER) return "D:" + darkColor + "_" + darkNumber;
            return "D:" + darkColor + "_" + darkType;
        }
    }
}