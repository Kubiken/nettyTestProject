package models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import models.messageCreator.MessageCreator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueueMessageTest {

    static QueueMessage qm;

    @BeforeAll
    static void generateMessage(){
        qm = new MessageCreator().createDefaultMessage();
    }

    @Test
    void getPrice_expectingPriceValue(){
        Double real = qm.getSomePrice();
        Double expected = 2019.0;
        assertEquals(expected, real);
    }

    @Test
    void getText_expectingTextValue(){
        String expected = "Vamus";
        String real = qm.getSomeText();
        assertEquals(expected, real);
    }

    @Test
    void getNum_expectingInteerValue(){
        Integer expected = 99;
        Integer real = qm.getSomeNum();
        assertEquals(expected, real);
    }

    @Test
    void tryConstructor_expectingCorrectConstructorWork(){
        QueueMessage qmt = new QueueMessage("Test", 10.0, 11);
        assertEquals("Test", qmt.getSomeText());
        assertEquals(10.0, qmt.getSomePrice());
        assertEquals(11, qmt.getSomeNum());
    }

}