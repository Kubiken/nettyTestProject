package models.messageCreator;

import org.junit.jupiter.api.Test;
import models.QueueMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageCreatorTest {

    @Test
    public void messageCreating_expectingCorrectBuilderWork()
    {
        QueueMessage qm = new QueueMessage("Vaskez", 20.0, 5);

        MessageCreator mc = new MessageCreator();
        assertEquals(qm, mc.createQueueMessage("Vaskez",20.0,5));
    }

    @Test
    public void messageDefaultCreating_expectingCorrectDefaultCreation()
    {
        QueueMessage qm = new QueueMessage("Vamus", 2019.0, 99);

        MessageCreator mc = new MessageCreator();
        assertEquals(qm, mc.createDefaultMessage());
    }

}