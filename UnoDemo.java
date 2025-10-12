public class UnoDemo {
    public static void main(String[] args) {
        System.out.println("=== UNO GAME DEMO ===");
        System.out.println("Features:");
        System.out.println("✓ Inheritance-based Player system (prevents cheating)");
        System.out.println("✓ Linked List implementation for card management");
        System.out.println("✓ Precedence-based card ordering (higher precedence discarded first)");
        System.out.println("✓ Support for 2-10 players");
        System.out.println("✓ Human vs Computer vs Computer gameplay");
        System.out.println("✓ Complete UNO rules implementation");
        System.out.println("\nStarting game...\n");
        
        // Start the actual game
        UnoGame game = new UnoGame();
        game.playGame();
    }
}