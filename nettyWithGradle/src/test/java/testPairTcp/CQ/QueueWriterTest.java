package testPairTcp.CQ;

import org.junit.jupiter.api.Test;
import testPairTcp.models.QueueMessage;
import testPairTcp.models.messageCreator.MessageCreator;

import static org.junit.jupiter.api.Assertions.fail;

class QueueWriterTest {

    @Test
    void tryWrite(){
        QueueMessage qm = new MessageCreator().createDefaultMessage();
        QueueWriter qw = new QueueWriter();
        try{
            qw.write(qm);
        }catch (Exception e){
            fail("Can't correctly write message");
        }

    }

}