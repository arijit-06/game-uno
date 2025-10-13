import java.util.*;

public class CardList {
    private Node head;
    private int size;
    
    private class Node {
        Card card;
        Node next;
        
        Node(Card card) {
            this.card = card;
            this.next = null;
        }
    }
    
    public void addCard(Card card) {
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
    
    public Card removeHighestPrecedence() {
        if (head == null) return null;
        
        Card card = head.card;
        head = head.next;
        size--;
        return card;
    }
    
    public Card removeCard(Card targetCard) {
        if (head == null) return null;
        
        if (head.card.equals(targetCard)) {
            Card card = head.card;
            head = head.next;
            size--;
            return card;
        }
        
        Node current = head;
        while (current.next != null) {
            if (current.next.card.equals(targetCard)) {
                Card card = current.next.card;
                current.next = current.next.next;
                size--;
                return card;
            }
            current = current.next;
        }
        return null;
    }
    
    public Card findPlayableCard(Card topCard) {
        Node current = head;
        while (current != null) {
            if (current.card.canPlayOn(topCard)) {
                return current.card;
            }
            current = current.next;
        }
        return null;
    }
    
    private void sortByPrecedence() {
        if (head == null || head.next == null) return;
        
        List<Card> cards = new ArrayList<>();
        Node current = head;
        while (current != null) {
            cards.add(current.card);
            current = current.next;
        }
        
        Collections.sort(cards);
        
        head = null;
        size = 0;
        for (Card card : cards) {
            addCardUnsorted(card);
        }
    }
    
    private void addCardUnsorted(Card card) {
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
    
    public List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        Node current = head;
        while (current != null) {
            cards.add(current.card);
            current = current.next;
        }
        return cards;
    }
}