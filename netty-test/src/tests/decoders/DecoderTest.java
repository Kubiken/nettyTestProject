package tests.decoders;

import io.netty.buffer.*;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testPairTcp.decoders.Decoder;
import testPairTcp.decoders.Encoder;
import testPairTcp.models.QueueMessage;
import testPairTcp.models.messageCreator.MessageCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {

    static ByteBuf in = Unpooled.buffer();

    @BeforeAll
    public static void generateMsg(){
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        QueueMessage msg = new MessageCreator().createDefaultMessage();

        Encoder enc = new Encoder();
        try {
            enc.encode(ctx, msg, in);
        }catch (Exception e){
            fail("Can't encode message");
        }
    }

    @Test
    void correctDecoding(){
        Decoder dec = new Decoder();
        ChannelHandlerContext chx = Mockito.mock(ChannelHandlerContext.class);
        List<Object> out = new ArrayList<>();

        try {
            dec.decode(chx, in, out);
        }catch (Exception e){
            fail("Can't decode generated msg");
        }
    }

}