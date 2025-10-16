import java.util.Scanner;

public class UnoInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=== UNO INHERITANCE ARCHITECTURE DEMO ===");
        System.out.println("Demonstrating True Object-Oriented Design!");
        System.out.println();
        System.out.println("INHERITANCE HIERARCHY:");
        System.out.println("  BaseCard (Abstract)");
        System.out.println("  ├── ClassicCard - Standard UNO rules");
        System.out.println("  ├── FlipCard - Double-sided chaos");
        System.out.println("  └── MercyCard - Brutal No Mercy variant");
        System.out.println();
        System.out.println("  BasePlayer<T> (Generic Abstract)");
        System.out.println("  ├── ClassicHumanPlayer / ClassicComputerPlayer");
        System.out.println("  ├── FlipHumanPlayer / FlipComputerPlayer");
        System.out.println("  └── MercyHumanPlayer / MercyComputerPlayer");
        System.out.println();
        System.out.println("  BaseGame<T> (Generic Abstract)");
        System.out.println("  ├── ClassicUnoGame - Traditional gameplay");
        System.out.println("  ├── FlipUnoGame - Flip mechanics");
        System.out.println("  └── MercyUnoGame - Brutal mechanics");
        System.out.println();
        System.out.println("KEY OOP CONCEPTS DEMONSTRATED:");
        System.out.println("  ✓ Inheritance - Base classes extended by variants");
        System.out.println("  ✓ Polymorphism - Same interface, different behaviors");
        System.out.println("  ✓ Generics - Type-safe collections and methods");
        System.out.println("  ✓ Abstract Classes - Common behavior, specialized implementation");
        System.out.println("  ✓ Encapsulation - Protected data with controlled access");
        System.out.println("  ✓ Template Method Pattern - Base game flow, variant-specific details");
        System.out.println();
        System.out.println("CHOOSE YOUR UNO VARIANT:");
        System.out.println("1. Classic UNO - Traditional friendly gameplay");
        System.out.println("2. UNO Flip - Revolutionary double-sided chaos");
        System.out.println("3. UNO No Mercy - Brutal psychological warfare");
        System.out.println();
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice (1-3): ");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                System.out.println("\n🌟 Starting Classic UNO...");
                ClassicUnoGame classicGame = new ClassicUnoGame();
                classicGame.playGame();
                break;
                
            case 2:
                System.out.println("\n🔄 Starting UNO Flip...");
                FlipUnoGame flipGame = new FlipUnoGame();
                flipGame.playGame();
                break;
                
            case 3:
                System.out.println("\n💀 Starting UNO No Mercy...");
                System.out.println("WARNING: Friendships may not survive!");
                // MercyUnoGame mercyGame = new MercyUnoGame();
                // mercyGame.playGame();
                System.out.println("No Mercy implementation coming soon...");
                break;
                
            default:
                System.out.println("Invalid choice! Starting Classic UNO...");
                ClassicUnoGame defaultGame = new ClassicUnoGame();
                defaultGame.playGame();
        }
        
        scanner.close();
    }
}