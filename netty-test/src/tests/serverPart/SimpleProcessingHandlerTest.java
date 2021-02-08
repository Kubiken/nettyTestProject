package tests.serverPart;

import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testPairTcp.models.QueueMessage;
import testPairTcp.models.messageCreator.MessageCreator;
import testPairTcp.serverPart.SimpleProcessingHandler;

import static org.junit.jupiter.api.Assertions.*;

class SimpleProcessingHandlerTest {

    static SimpleProcessingHandler sph;


    @BeforeAll
    static void initHandler(){
        sph = Mockito.mock(SimpleProcessingHandler.class);
    }

    @Test
    void serverChannelActivationTest(){
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        try {
            sph.handlerAdded(ctx);
        }catch (Exception e){
            fail("Can't add channel to handler");
        }
    }

    @Test
    void chanellReadingTest(){
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        QueueMessage qm = new MessageCreator().createDefaultMessage();
        try {
            sph.channelRead(ctx, qm);
        }catch (Exception e){
            fail("Can't correct read");
        }
    }

    @Test
    void serverChannelKilling(){
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        try {
            sph.handlerRemoved(ctx);
        }catch (Exception e){
            fail("Can't remove handler");
        }
    }
}