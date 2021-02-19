package decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import models.QueueMessage;
import models.MessageCreator;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;

class EncoderTest {


    @Test
    void tryEncodeToBytebuf_expectingAllWritingMethodsCalling() throws Exception {
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        QueueMessage msg = new MessageCreator().createDefaultMessage();
        ByteBuf out = Mockito.spy(ByteBuf.class);

        Encoder enc = new Encoder();
        enc.encode(ctx, msg, out);

        verify(out).writeDouble(msg.getSomePrice());
        verify(out).writeInt(msg.getSomeText().length());
        verify(out).writeCharSequence(msg.getSomeText(), Charset.defaultCharset());
        verify(out).writeInt(msg.getSomeNum());

    }
}