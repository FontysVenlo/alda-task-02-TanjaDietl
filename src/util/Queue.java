/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner.util;

import appointmentplanner.Appointment;

/**
 *
 * @author tanja
 */
public class Queue<Item> {

    private Node<Item>[] queue;
    private Node<Item>[] tempQueue;
    private final int initialSizeValue;
    private int index = 0;
    private int front = 0;
    private Node<Item> runner;
    private boolean run = true;

    public Queue() {
        this.initialSizeValue = 200;
        this.queue = new Node[initialSizeValue];

    }

    private static class Node<Item> {

        Item item;
        Node<Item> next;

        Node(Item item) {
            this.item = item;
            next = null;
        }
    }

    public void put(Item item) {
        Node<Item> data = new Node(item);

        if (!isFull()) {
            if (isEmpty()) {
                queue[index] = data;
                index++;
            } else {
                runner = queue[index - 1];
                queue[index] = data;
                runner.next = queue[index];
                index++;
            }
        } else if (isFull()) {
            System.out.println("Queue is Full");
        }

    }

    public Item get() {
        Item itemToReturn = null;
        if (!isEmpty()) {
            itemToReturn = queue[front].item;
            copyArray();
            index--;
        } else if (isEmpty()){
            copyArray();
            index = 0;
            System.out.println("Queue is Empty ");
            
        }
        return itemToReturn;
    }

    public void show() {
        System.out.println("========== QUEUE ELEMENTS ==========");
        for (int i = 0; i < index; i++) {
            System.out.println("Information: " + queue[(front + i) % initialSizeValue].item);
            System.out.println("Current Node: " + queue[(front + i) % initialSizeValue] + " Next Node: " + queue[(front + i) % initialSizeValue].next);
            System.out.println("index: " + index);
            System.out.println("front: " + front);
        }
        System.out.println("========== QUEUE DONE ==========");
    }

    public void removeAll() {
        index = 0;
        front = 0;
        tempQueue = new Node[200];
        queue = tempQueue;
    }
    
    private void copyArray(){
        tempQueue = new Node[200];
        int tempIndex = 0;
        for (int i = 1; i < index; i++) {
            tempQueue[tempIndex] = queue[i];
            tempIndex++;
        }
        queue = tempQueue;
    }

    public int getSize() {
        return index;
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public boolean isFull() {

        return getSize() == initialSizeValue;
    }

}