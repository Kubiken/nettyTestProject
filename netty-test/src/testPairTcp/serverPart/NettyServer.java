package testPairTcp.serverPart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import testPairTcp.bootstrapBuilder.BootstrapBuilder;
import testPairTcp.decoders.Encoder;
import testPairTcp.decoders.Decoder;

import java.util.HashMap;

public class NettyServer {

    public static void main(String[]args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        NettyServer nettyServer = new NettyServer();
        nettyServer.run(8080, bossGroup, workerGroup);
    }

    public void run(int port, EventLoopGroup bossGroup,
                           EventLoopGroup workerGroup) {

        if(port==0||bossGroup==null||workerGroup==null)
            throw new NullPointerException("One of parametr equals null: port-"+port+
                    " EventLoopGroup-"+bossGroup+
                    " workerGroup-"+workerGroup);

        try {
            HashMap<ChannelOption, Object> options = new HashMap<>();
            options.put(ChannelOption.SO_KEEPALIVE, true);
            options.put(ChannelOption.SO_BACKLOG, 128);

            ServerBootstrap b = BootstrapBuilder.serverBootstrapBuilder(bossGroup,workerGroup,
                    NioServerSocketChannel.class, options);

            ChannelFuture f = b.bind(port).sync();
            System.out.println("Server sucsessfully started");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
