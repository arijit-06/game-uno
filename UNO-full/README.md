# 🏗️ UNO Full - Inheritance Framework

Advanced UNO implementation showcasing object-oriented design patterns with multiple game variants in a unified codebase.

## 🎯 Architecture Overview

### 🔧 Base Classes
- **BaseCard**: Abstract foundation for all card types
- **BasePlayer**: Abstract player with common functionality  
- **BaseGame**: Abstract game controller template

### 🎮 Implemented Variants
- **Classic UNO**: Traditional gameplay
- **Flip UNO**: Double-sided cards
- **Mercy UNO**: Modified mercy rules

## 🏗️ Class Hierarchy

```
BaseCard (Abstract)
├── ClassicCard - Standard UNO cards
├── FlipCard - Double-sided cards
└── MercyCard - Mercy rule cards

BasePlayer (Abstract)
├── ClassicHumanPlayer / ClassicComputerPlayer
├── FlipHumanPlayer / FlipComputerPlayer
└── MercyHumanPlayer / MercyComputerPlayer

BaseGame (Abstract)
├── ClassicUnoGame
├── FlipUnoGame
└── MercyUnoGame
```

## 🚀 How to Run

```bash
javac *.java
java UnoInheritanceDemo
```

## 🎯 Design Patterns

- **Template Method**: BaseGame defines game flow
- **Strategy Pattern**: Different AI behaviors
- **Factory Pattern**: Card creation
- **Polymorphism**: Unified player interface
- **Inheritance**: Code reuse across variants

## 🔧 Key Features

- ✅ Multiple UNO variants in one framework
- ✅ Extensible architecture for new variants
- ✅ Shared base functionality
- ✅ Polymorphic player management
- ✅ Unified game controller interface

## 📚 Educational Value

Perfect for learning:
- Object-oriented design principles
- Inheritance and polymorphism
- Abstract classes and interfaces
- Design pattern implementation