public class ClassicUnoGame extends BaseGame<ClassicCard> {
    
    @Override
    protected void initializeDeck() {
        BaseCard.Color[] colors = {BaseCard.Color.RED, BaseCard.Color.BLUE, 
                                  BaseCard.Color.GREEN, BaseCard.Color.YELLOW};
        
        // Number cards
        for (BaseCard.Color color : colors) {
            deck.add(new ClassicCard(color, BaseCard.Type.NUMBER, 0));
            for (int i = 1; i <= 9; i++) {
                deck.add(new ClassicCard(color, BaseCard.Type.NUMBER, i));
                deck.add(new ClassicCard(color, BaseCard.Type.NUMBER, i));
            }
        }
        
        // Action cards
        for (BaseCard.Color color : colors) {
            for (int i = 0; i < 2; i++) {
                deck.add(new ClassicCard(color, BaseCard.Type.SKIP, -1));
                deck.add(new ClassicCard(color, BaseCard.Type.REVERSE, -1));
                deck.add(new ClassicCard(color, BaseCard.Type.DRAW_TWO, -1));
            }
        }
        
        // Wild cards
        for (int i = 0; i < 4; i++) {
            deck.add(new ClassicCard(BaseCard.Color.WILD, BaseCard.Type.WILD, -1));
            deck.add(new ClassicCard(BaseCard.Color.WILD, BaseCard.Type.WILD_DRAW_FOUR, -1));
        }
    }
    
    @Override
    protected BasePlayer<ClassicCard> createHumanPlayer(String name) {
        return new ClassicHumanPlayer(name);
    }
    
    @Override
    protected BasePlayer<ClassicCard> createComputerPlayer(String name) {
        return new ClassicComputerPlayer(name);
    }
    
    @Override
    protected void handleSpecialCard(ClassicCard card, BasePlayer<ClassicCard> currentPlayer) {
        switch (card.getType()) {
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
                BasePlayer<ClassicCard> nextPlayer = players.get(currentPlayerIndex);
                drawCards(nextPlayer, 2);
                System.out.println(nextPlayer.getName() + " draws 2 cards and is skipped!");
                break;
                
            case WILD:
                BaseCard.Color newColor = currentPlayer.chooseColor();
                topCard.setColor(newColor);
                System.out.println("Color changed to: " + newColor);
                break;
                
            case WILD_DRAW_FOUR:
                nextPlayer();
                BasePlayer<ClassicCard> nextPlayer4 = players.get(currentPlayerIndex);
                drawCards(nextPlayer4, 4);
                BaseCard.Color newColor4 = currentPlayer.chooseColor();
                topCard.setColor(newColor4);
                System.out.println(nextPlayer4.getName() + " draws 4 cards and is skipped!");
                System.out.println("Color changed to: " + newColor4);
                break;
        }
    }
    
    public static void main(String[] args) {
        ClassicUnoGame game = new ClassicUnoGame();
        game.playGame();
    }
}