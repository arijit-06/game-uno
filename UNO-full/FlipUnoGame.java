public class FlipUnoGame extends BaseGame<FlipCard> {
    private FlipCard.Side currentSide;
    
    public FlipUnoGame() {
        super();
        this.currentSide = FlipCard.Side.LIGHT;
    }
    
    @Override
    protected void initializeDeck() {
        FlipCard.LightColor[] lightColors = {FlipCard.LightColor.TEAL, FlipCard.LightColor.PINK, 
                                           FlipCard.LightColor.PURPLE, FlipCard.LightColor.ORANGE};
        FlipCard.DarkColor[] darkColors = {FlipCard.DarkColor.BLUE, FlipCard.DarkColor.GREEN, 
                                         FlipCard.DarkColor.RED, FlipCard.DarkColor.YELLOW};
        
        // Number cards
        for (int i = 0; i < lightColors.length; i++) {
            deck.add(new FlipCard(lightColors[i], FlipCard.ExtendedType.NUMBER, 0,
                                darkColors[i], FlipCard.ExtendedType.NUMBER, 0));
            
            for (int num = 1; num <= 9; num++) {
                for (int copy = 0; copy < 2; copy++) {
                    deck.add(new FlipCard(lightColors[i], FlipCard.ExtendedType.NUMBER, num,
                                        darkColors[i], FlipCard.ExtendedType.NUMBER, num));
                }
            }
        }
        
        // Action cards
        for (int i = 0; i < lightColors.length; i++) {
            for (int copy = 0; copy < 2; copy++) {
                deck.add(new FlipCard(lightColors[i], FlipCard.ExtendedType.REVERSE, -1,
                                    darkColors[i], FlipCard.ExtendedType.REVERSE, -1));
                deck.add(new FlipCard(lightColors[i], FlipCard.ExtendedType.SKIP, -1,
                                    darkColors[i], FlipCard.ExtendedType.SKIP_EVERYONE, -1));
                deck.add(new FlipCard(lightColors[i], FlipCard.ExtendedType.DRAW_TWO, -1,
                                    darkColors[i], FlipCard.ExtendedType.DRAW_FIVE, -1));
                deck.add(new FlipCard(lightColors[i], FlipCard.ExtendedType.FLIP, -1,
                                    darkColors[i], FlipCard.ExtendedType.FLIP, -1));
            }
        }
        
        // Wild cards
        for (int i = 0; i < 4; i++) {
            deck.add(new FlipCard(FlipCard.LightColor.WILD, FlipCard.ExtendedType.WILD, -1,
                                FlipCard.DarkColor.WILD, FlipCard.ExtendedType.WILD, -1));
            deck.add(new FlipCard(FlipCard.LightColor.WILD, FlipCard.ExtendedType.WILD_DRAW_TWO, -1,
                                FlipCard.DarkColor.WILD, FlipCard.ExtendedType.WILD_DRAW_COLOR, -1));
        }
    }
    
    @Override
    protected BasePlayer<FlipCard> createHumanPlayer(String name) {
        return new FlipHumanPlayer(name);
    }
    
    @Override
    protected BasePlayer<FlipCard> createComputerPlayer(String name) {
        return new FlipComputerPlayer(name);
    }
    
    @Override
    protected void handleSpecialCard(FlipCard card, BasePlayer<FlipCard> currentPlayer) {
        if (currentSide == FlipCard.Side.LIGHT) {
            handleLightSideCard(card, currentPlayer);
        } else {
            handleDarkSideCard(card, currentPlayer);
        }
    }
    
    private void handleLightSideCard(FlipCard card, BasePlayer<FlipCard> currentPlayer) {
        switch (card.getLightType()) {
            case SKIP:
                System.out.println("Next player is skipped!");
                nextPlayer();
                break;
                
            case REVERSE:
                clockwise = !clockwise;
                System.out.println("Direction reversed!");
                break;
                
            case DRAW_TWO:
                nextPlayer();
                BasePlayer<FlipCard> nextPlayer = players.get(currentPlayerIndex);
                drawCards(nextPlayer, 2);
                System.out.println(nextPlayer.getName() + " draws 2 cards and is skipped!");
                break;
                
            case FLIP:
                System.out.println("🌑 FLIPPING TO DARK SIDE! 🌑");
                flipToOtherSide();
                break;
                
            case WILD:
                // Handle color change for flip cards
                System.out.println("Color changed (Light Side)");
                break;
        }
    }
    
    private void handleDarkSideCard(FlipCard card, BasePlayer<FlipCard> currentPlayer) {
        switch (card.getDarkType()) {
            case SKIP_EVERYONE:
                System.out.println("💀 EVERYONE ELSE IS SKIPPED! " + currentPlayer.getName() + " plays again!");
                return; // Don't advance to next player
                
            case REVERSE:
                clockwise = !clockwise;
                System.out.println("Direction reversed!");
                break;
                
            case DRAW_FIVE:
                nextPlayer();
                BasePlayer<FlipCard> nextPlayer = players.get(currentPlayerIndex);
                drawCards(nextPlayer, 5);
                System.out.println("💥 " + nextPlayer.getName() + " draws 5 cards and is skipped!");
                break;
                
            case FLIP:
                System.out.println("🌟 FLIPPING TO LIGHT SIDE! 🌟");
                flipToOtherSide();
                break;
        }
    }
    
    private void flipToOtherSide() {
        currentSide = (currentSide == FlipCard.Side.LIGHT) ? FlipCard.Side.DARK : FlipCard.Side.LIGHT;
        
        // Flip all cards in all hands
        for (BasePlayer<FlipCard> player : players) {
            for (FlipCard card : player.getCards()) {
                card.flip();
            }
        }
        
        // Flip the deck
        for (FlipCard card : deck) {
            card.flip();
        }
        
        // Flip the top card
        topCard.flip();
        
        System.out.println("🔄 Everything flipped to " + currentSide + " side!");
        System.out.println("New top card: " + topCard);
    }
    
    public static void main(String[] args) {
        FlipUnoGame game = new FlipUnoGame();
        game.playGame();
    }
}