package testPairTcp.serverPart;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import models.QueueMessage;
import models.messageCreator.MessageCreator;


public class SimpleProcessingHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf tmp;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("Handler added");
        tmp = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("Handler removed");
        tmp.release();
        tmp = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("Message getted by server");
        MessageCreator mc = new MessageCreator();
        QueueMessage requestData = (QueueMessage) msg;
        ChannelFuture future = ctx.writeAndFlush(mc.createQueueMessage("Response string "+requestData.getSomeText(),
                requestData.getSomePrice()+1, requestData.getSomeNum()+11));
        System.out.println("Message modified and sended back");
        future.addListener(ChannelFutureListener.CLOSE);

    }

}