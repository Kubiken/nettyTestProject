package tests.clientPart;

import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testPairTcp.clientPart.ClientHandler;
import testPairTcp.models.QueueMessage;
import testPairTcp.models.messageCreator.MessageCreator;

import static org.junit.jupiter.api.Assertions.*;

class ClientHandlerTest {

    @Test
    public void chanelActivationTest(){
        ClientHandler ch = Mockito.mock(ClientHandler.class);
        ChannelHandlerContext chc = Mockito.mock(ChannelHandlerContext.class);
        try {
            ch.channelActive(chc);
        } catch (Exception e) {
            fail("Cant activate chanell");
        }
    }

    @Test
    public void chanellReadingTest(){

        QueueMessage qm = new MessageCreator().createDefaultMessage();
        ClientHandler ch = Mockito.mock(ClientHandler.class);
        ChannelHandlerContext chc = Mockito.mock(ChannelHandlerContext.class);

        try{
            ch.channelRead(chc, (Object)qm);
        } catch (Exception e) {
            fail("Can't read message");
        }
    }

}