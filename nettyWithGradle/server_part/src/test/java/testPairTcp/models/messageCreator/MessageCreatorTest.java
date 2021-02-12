package testPairTcp.models.messageCreator;

import org.junit.jupiter.api.Test;
import testPairTcp.models.QueueMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageCreatorTest {

    @Test
    public void messageCreating()
    {
        QueueMessage qm = new QueueMessage("Vaskez", 20.0, 5);

        MessageCreator mc = new MessageCreator();
        assertEquals(qm, mc.createQueueMessage("Vaskez",20.0,5));
    }

    @Test
    public void messageDefaultCreating()
    {
        QueueMessage qm = new QueueMessage("Vamus", 2019.0, 99);

        MessageCreator mc = new MessageCreator();
        assertEquals(qm, mc.createDefaultMessage());
    }

    @Test
    public void messageDefaultCreatingNotNULL(){
        MessageCreator mc = new MessageCreator();

        assertNotNull(mc.createDefaultMessage());
    }

}