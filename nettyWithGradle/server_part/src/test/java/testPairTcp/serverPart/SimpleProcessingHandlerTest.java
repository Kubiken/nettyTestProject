package testPairTcp.serverPart;

import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import models.QueueMessage;
import models.messageCreator.MessageCreator;

import static org.junit.jupiter.api.Assertions.fail;

class SimpleProcessingHandlerTest {

    static SimpleProcessingHandler sph;


    @BeforeAll
    static void initHandler(){
        sph = Mockito.mock(SimpleProcessingHandler.class);
    }

    @Test
    void serverChannelActivationTest_expectingCorrectHandler(){
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        try {
            sph.handlerAdded(ctx);
        }catch (Exception e){
            fail("Can't add channel to handler");
        }
    }

    @Test
    void chanellReadingTest_expectingCorrectReading(){
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        QueueMessage qm = new MessageCreator().createDefaultMessage();
        try {
            sph.channelRead(ctx, qm);
        }catch (Exception e){
            fail("Can't correct read");
        }
    }

    @Test
    void serverChannelKilling_expectingCorrectChanelDestroy(){
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        try {
            sph.handlerRemoved(ctx);
        }catch (Exception e){
            fail("Can't remove handler");
        }
    }
}