package models.messageCreator;

import models.MessageCreator;
import models.QueueMessage;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageCreatorTest {

    @Test
    public void messageCreating_expectingCorrectBuilderWork()
    {
        MessageCreator mc = new MessageCreator();
        QueueMessage qm = mc.createQueueMessage("Vaskez",20.0,5);

        assertEquals("Vaskez", qm.getSomeText());
        assertEquals(20.0, qm.getSomePrice());
        assertEquals(5, qm.getSomeNum());
    }

    @Test
    public void messageDefaultCreating_expectingCorrectDefaultCreation()
    {
        MessageCreator mc = new MessageCreator();
        QueueMessage qm = mc.createDefaultMessage();

        assertEquals("Vamus", qm.getSomeText());
        assertEquals(2019.0, qm.getSomePrice());
        assertEquals(99, qm.getSomeNum());
    }

}