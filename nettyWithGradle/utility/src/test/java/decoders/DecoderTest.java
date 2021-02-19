package decoders;

import io.netty.buffer.*;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import models.QueueMessage;
import models.MessageCreator;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

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
    void tryDecodeMock_expectingQueueMessageObject() throws Exception {
        ByteBuf inMock = Mockito.mock(ByteBuf.class);
        when(inMock.readDouble()).thenReturn(10.0);
        when(inMock.readInt()).thenReturn(5, 15);
        when(inMock.readCharSequence(anyInt(), any(Charset.class)))
                .thenReturn("victo");

        ChannelHandlerContext chx = Mockito.mock(ChannelHandlerContext.class);
        List<Object> out = new ArrayList<>();

        Decoder dec = new Decoder();
        dec.decode(chx, inMock, out);

        assertEquals(1, out.size());
        QueueMessage real = (QueueMessage)out.get(0);

        assertEquals(10.0, real.getSomePrice());
        assertEquals("victo", real.getSomeText());
        assertEquals(15, real.getSomeNum());
    }

}