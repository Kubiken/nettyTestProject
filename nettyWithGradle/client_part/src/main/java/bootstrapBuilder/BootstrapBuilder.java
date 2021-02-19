package bootstrapBuilder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import clientPart.ClientHandler;
import decoders.Decoder;
import decoders.Encoder;


import java.util.HashMap;
import java.util.Map;

public class BootstrapBuilder {


    public static void bootstrapBuilder(EventLoopGroup elg, Class socketChanell,
                                             HashMap<ChannelOption, Object> options, Bootstrap b) {

        if (elg == null || socketChanell == null || options == null) {
            throw new NullPointerException("One of parametrs equals null: elg-" + elg.toString() +
                    " socketChanell-" + socketChanell.toString() +
                    " options-" + options.toString());
        }

        b.group(elg);
        b.channel(socketChanell);
        for (Map.Entry<ChannelOption, Object> entry : options.entrySet())
            b.option(entry.getKey(), entry.getValue());
        b.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            public void initChannel(SocketChannel ch)
                    throws Exception {
                ch.pipeline().addLast(new Encoder(),
                        new Decoder(), new ClientHandler());
            }
        });

    }

}
