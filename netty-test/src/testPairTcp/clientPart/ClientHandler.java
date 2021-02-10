package testPairTcp.clientPart;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import testPairTcp.CQ.QueueWriter;
import testPairTcp.models.QueueMessage;
import testPairTcp.models.messageCreator.MessageCreator;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        MessageCreator mc = new MessageCreator();
        ChannelFuture future = ctx.writeAndFlush(mc.createDefaultMessage());
        System.out.println("Send message");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("Message geted");
        QueueMessage data = (QueueMessage) msg;
        QueueWriter ir = new QueueWriter();
        ir.write(data);
        System.out.println(data.getSomeText());
        System.out.println(data.getSomeNum());
        System.out.println(data.getSomePrice());
        ctx.close();
    }
}
