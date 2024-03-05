package com.example;

public class Main {
	public static void main(String[] args) {
        // Create an instance of InMemoryQueueService
        InMemoryQueueService queueService = new InMemoryQueueService();

        // Push messages to the queue
        queueService.push("myQueue", "Message 1"); //  priority 2
        queueService.push("myQueue", "Message 2", 1); //  priority 1
        queueService.push("myQueue", "Message 3");    // Default priority is 0

        // Pull messages from the queue
        Message message = queueService.pull("myQueue");
        if (message != null) {
            System.out.println("Pulled message: " + message.getBody());
        }

        // Delete a message from the queue (you would typically use receiptId obtained from pull operation)
        queueService.delete("myQueue", message.getReceiptId());
        
        
        UpstashQueueService service = new UpstashQueueService(new Credential());

        // Example usage
        service.push("foo", "Hello!");
        service.push("foo", "bye!");
        String message2 = service.pull("foo");
    }
}
