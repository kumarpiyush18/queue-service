package com.example;

public class PriorityMessage extends Message{
	private int priority;
	
	public PriorityMessage(String body, int priority) {
		super(body);
		this.priority=priority;
	}
	
	public int getPriority() {
        return priority;
    }
}
