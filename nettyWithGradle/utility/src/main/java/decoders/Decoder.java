package decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import models.QueueMessage;
import models.messageCreator.MessageCreator;

import java.nio.charset.Charset;
import java.util.List;

public class Decoder extends ReplayingDecoder<QueueMessage> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    public void decode(ChannelHandlerContext ctx,
                          ByteBuf in, List<Object> out) throws Exception {

        MessageCreator mc = new MessageCreator();

        Double d = in.readDouble();
        int strLen = in.readInt();
        String t = in.readCharSequence(strLen, charset).toString();
        Integer i = in.readInt();

        out.add(mc.createQueueMessage(t, d, i));
    }
}
