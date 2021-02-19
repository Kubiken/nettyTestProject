package models;

public class MessageCreator {

    public QueueMessage createQueueMessage(String text, Double price, Integer num){
        return new QueueMessage(text, price, num);
    }

    public QueueMessage createDefaultMessage (){
        return new QueueMessage("Vamus", 2019.0, 99);
    }

}
