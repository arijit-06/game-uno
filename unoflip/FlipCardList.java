import java.util.*;

public class FlipCardList {
    private Node head;
    private int size;
    
    private class Node {
        FlipCard card;
        Node next;
        
        Node(FlipCard card) {
            this.card = card;
            this.next = null;
        }
    }
    
    public void addCard(FlipCard card) {
        Node newNode = new Node(card);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        sortByPrecedence();
    }
    
    public FlipCard removeHighestPrecedence() {
        if (head == null) return null;
        
        FlipCard card = head.card;
        head = head.next;
        size--;
        return card;
    }
    
    public FlipCard removeCard(FlipCard targetCard) {
        if (head == null) return null;
        
        if (head.card.equals(targetCard)) {
            FlipCard card = head.card;
            head = head.next;
            size--;
            return card;
        }
        
        Node current = head;
        while (current.next != null) {
            if (current.next.card.equals(targetCard)) {
                FlipCard card = current.next.card;
                current.next = current.next.next;
                size--;
                return card;
            }
            current = current.next;
        }
        return null;
    }
    
    public FlipCard findPlayableCard(FlipCard topCard) {
        Node current = head;
        while (current != null) {
            if (current.card.canPlayOn(topCard)) {
                return current.card;
            }
            current = current.next;
        }
        return null;
    }
    
    public void flipAllCards() {
        Node current = head;
        while (current != null) {
            current.card.flip();
            current = current.next;
        }
        sortByPrecedence(); // Re-sort after flipping changes precedence
    }
    
    private void sortByPrecedence() {
        if (head == null || head.next == null) return;
        
        List<FlipCard> cards = new ArrayList<>();
        Node current = head;
        while (current != null) {
            cards.add(current.card);
            current = current.next;
        }
        
        Collections.sort(cards);
        
        head = null;
        size = 0;
        for (FlipCard card : cards) {
            addCardUnsorted(card);
        }
    }
    
    private void addCardUnsorted(FlipCard card) {
        Node newNode = new Node(card);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    
    public List<FlipCard> getCards() {
        List<FlipCard> cards = new ArrayList<>();
        Node current = head;
        while (current != null) {
            cards.add(current.card);
            current = current.next;
        }
        return cards;
    }
}