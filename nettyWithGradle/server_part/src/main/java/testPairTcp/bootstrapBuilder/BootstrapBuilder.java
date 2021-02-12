package testPairTcp.bootstrapBuilder;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;

import testPairTcp.clientPart.ClientHandler;
import testPairTcp.decoders.Decoder;
import testPairTcp.decoders.Encoder;
import testPairTcp.serverPart.SimpleProcessingHandler;

import java.util.HashMap;
import java.util.Map;

public class BootstrapBuilder {

    public static Bootstrap bootstrapBuilder(EventLoopGroup elg, Class socketChanell,
                                             HashMap<ChannelOption, Object> options){

        if(elg==null|| socketChanell==null||options==null)
        {
            throw new NullPointerException("One of parametrs equals null: elg-"+elg.toString()+
                    " socketChanell-"+socketChanell.toString()+
                    " options-"+options.toString());
        }

        Bootstrap b = new Bootstrap();
        b.group(elg);
        b.channel(socketChanell);
        for(Map.Entry<ChannelOption, Object> entry: options.entrySet())
        b.option(entry.getKey(), entry.getValue());
        b.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            public void initChannel(SocketChannel ch)
                    throws Exception {
                ch.pipeline().addLast(new Encoder(),
                        new Decoder(), new ClientHandler());
            }
        });

        return b;
    }

    public static ServerBootstrap serverBootstrapBuilder(
            EventLoopGroup felg, EventLoopGroup selg, Class socketChanell,
            HashMap<ChannelOption, Object> options){

        if(felg==null||selg==null||socketChanell==null||options==null)
        {
            throw new NullPointerException("One of parametrs equals null: felg-"+felg.toString()+
                    " selg-"+selg.toString()+
                    " socketChanell-"+socketChanell.toString()+
                    " options-"+options.toString());
        }

        ServerBootstrap b = new ServerBootstrap();
        b.group(felg, selg);
        b.channel(socketChanell);
        for(Map.Entry<ChannelOption, Object> entry: options.entrySet())
            b.option(entry.getKey(), entry.getValue());
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch)
                    throws Exception {
                ch.pipeline().addLast(new Decoder(),
                        new Encoder(),
                        new SimpleProcessingHandler());
            }
        });

        return b;
    }
}
