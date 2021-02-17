package decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import models.QueueMessage;

import java.nio.charset.Charset;

public class Encoder extends MessageToByteEncoder<QueueMessage> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    public void encode(ChannelHandlerContext ctx,
                          QueueMessage msg, ByteBuf out) throws Exception {
        out.writeDouble(msg.getSomePrice());
        out.writeInt(msg.getSomeText().length());
        out.writeCharSequence(msg.getSomeText(), charset);
        out.writeInt(msg.getSomeNum());
    }

}
