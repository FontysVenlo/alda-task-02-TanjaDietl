/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner;

/**
 *
 * @author tanja
 */
public class Queue<Node> {

    private final Node[] queue;
    private int initialSizeValue;
    private int index = 0;
    private int front = 0;
    private int rear = 0;
    private Node<Appointment> runner;
    private boolean run = true;

    public Queue() {
        this.initialSizeValue = 5;
        this.queue = new Node[initialSizeValue];

    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E item) {
            this.item = item;
            next = null;
        }
    }

    public void put(Appointment item) {
        Node<Appointment> data = new Node(item);
        
        if (!isFull()) {
            if(isEmpty()){
                queue[rear] = data;
                rear = (rear + 1) % initialSizeValue;
                index++;
            } else {
                runner = queue[rear-1];
                queue[rear] = data;
                runner.next = queue[rear];
                rear = (rear + 1) % initialSizeValue;
                index++;
            }
                
        } else {
            System.out.println("Queue is Full");
        }

    }

    public Node get() {
        if (!isEmpty()) {
            front = (front + 1) % initialSizeValue;
            index--;
        }else{
            System.out.println("Queue is Empty");
        }
        return queue[front];
    }

    public void show() {
        System.out.println("========== QUEUE ELEMENTS ==========");
        for (int i = 0; i < index; i++) {
            System.out.println("Information: "+ queue[(front + i) % initialSizeValue].item );
            System.out.println("Current Node: "+ queue[(front + i) % initialSizeValue] + " Next Node: " + queue[(front + i) % initialSizeValue].next );
        }
        System.out.println("========== QUEUE DONE ==========");
    }

    public void removeAll() {
        for (int i = 0; i < queue.length; i++) {
            get();
        }
    }

    public int getSize() {
        return index;
    }

    public boolean isEmpty() {

        return getSize() == 0;
    }

    public boolean isFull() {

        return getSize() == initialSizeValue;
    }

}