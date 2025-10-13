# 🎮 UNO Games Collection

A comprehensive collection of UNO game implementations in Java, featuring multiple variants and advanced object-oriented design patterns.

## 🎯 Game Variants

### [🔴 UNO Classic](./uno-classic/)
Traditional UNO game with complete rule implementation
- **Players**: 2-10 (Human/Computer mix)
- **Cards**: Standard 108-card deck
- **Features**: Skip, Reverse, Draw Two, Wild cards
- **Architecture**: Inheritance-based with anti-cheat protection

### [🔄 UNO Flip](./uno-flip/)
Modern UNO variant with double-sided cards
- **Players**: 2-10 (Human/Computer mix)
- **Cards**: Flip cards with Light/Dark sides
- **Features**: Flip cards, Draw Five, Skip Everyone
- **Special**: Dynamic side switching gameplay

### [🏗️ UNO Full](./UNO-full/)
Advanced inheritance-based implementation
- **Architecture**: Base classes with multiple variants
- **Variants**: Classic, Flip, and Mercy in one codebase
- **Design**: Polymorphism and inheritance showcase
- **Features**: Extensible framework for new variants

### [💙 UNO Mercy](./uno-nomercy/)
UNO variant with mercy rule modifications
- **Players**: 2-10 (Human/Computer mix)
- **Rules**: Modified mercy rules for balanced gameplay
- **Features**: Custom card effects and win conditions
- **Design**: Standalone implementation

## 🚀 Quick Start

```bash
# Clone the repository
git clone https://github.com/arijit-06/game-uno.git
cd game-uno

# Run UNO Classic
cd uno-classic
javac *.java
java UnoDemo

# Run UNO Flip
cd ../uno-flip
javac *.java
java UnoFlipDemo

# Run UNO Full (Inheritance Demo)
cd ../UNO-full
javac *.java
java UnoInheritanceDemo

# Run UNO Mercy
cd ../uno-nomercy
javac *.java
java UnoMercyDemo
```

## 🏗️ Architecture Highlights

- **Inheritance**: Abstract base classes prevent cheating
- **Data Structures**: Custom linked lists with precedence sorting
- **Polymorphism**: Unified player interface for Human/Computer
- **Encapsulation**: Protected card management systems
- **Strategy Pattern**: AI decision-making algorithms

## 🎲 Features

- ✅ Multiple UNO variants
- ✅ Human vs Computer gameplay
- ✅ Computer vs Computer simulation
- ✅ Complete rule implementations
- ✅ Anti-cheat architecture
- ✅ Extensible design patterns
- ✅ Custom data structures

## 📋 Requirements

- Java 8 or higher
- Command line interface
- No external dependencies

## 🤝 Contributing

Feel free to fork, modify, and submit pull requests for new UNO variants or improvements!

## 📄 License

Open source - feel free to use for educational purposes.