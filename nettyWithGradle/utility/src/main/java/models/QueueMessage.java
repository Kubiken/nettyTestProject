package models;

public class QueueMessage {

    private final Integer someNum;
    private final String someText;
    private final Double somePrice;


    public QueueMessage(String text, Double price, Integer num){
        this.someText = text;
        this.somePrice = price;
        this.someNum = num;
    }

    public QueueMessage(){
        this.someText = "zero_message";
        this.somePrice = 0.0;
        this.someNum = 0;
    }

    public Double getSomePrice() {
        return somePrice;
    }

    public String getSomeText() {
        return someText;
    }

    public Integer getSomeNum() {
        return someNum;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        QueueMessage qm = (QueueMessage) obj;
        if(!qm.getSomePrice().equals(this.somePrice)||!qm.getSomeNum().equals(this.someNum)||
        !qm.getSomeText().equals(this.someText))
            return false;
        return true;
    }

}
