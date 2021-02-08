package tests.decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testPairTcp.decoders.Encoder;
import testPairTcp.models.QueueMessage;
import testPairTcp.models.messageCreator.MessageCreator;

import static org.junit.jupiter.api.Assertions.*;

class EncoderTest {

    @Test
    void tryEncodeMessage(){
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        QueueMessage msg = new MessageCreator().createDefaultMessage();
        ByteBuf out = Mockito.mock(ByteBuf.class);

        Encoder enc = new Encoder();
        try {
            enc.encode(ctx, msg, out);
        }catch (Exception e){
            fail("Can't encode message");
        }
    }

}