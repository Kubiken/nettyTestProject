package testPairTcp.serverPart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import testPairTcp.bootstrapBuilder.BootstrapBuilder;

import java.util.HashMap;

public class NettyServer {

    ChannelFuture channelFuture;
    private boolean serverStatus = false;

    public static void main(String[]args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        NettyServer nettyServer = new NettyServer();
        ServerBootstrap bootstrap = new ServerBootstrap();

        nettyServer.run(8080, bossGroup, workerGroup, bootstrap);
    }

    public boolean isServerStatus() {
        return serverStatus;
    }

    public void run(int port, EventLoopGroup bossGroup,
                    EventLoopGroup workerGroup, ServerBootstrap b) {

        if(port==0||bossGroup==null||workerGroup==null)
            throw new NullPointerException("One of parametr equals null: port-"+port+
                    " EventLoopGroup-"+bossGroup+
                    " workerGroup-"+workerGroup);

        try {
            HashMap<ChannelOption, Object> options = new HashMap<>();
            options.put(ChannelOption.SO_KEEPALIVE, true);
            options.put(ChannelOption.SO_BACKLOG, 128);

            BootstrapBuilder.serverBootstrapBuilder(bossGroup,workerGroup,
                    NioServerSocketChannel.class, options, b);

            channelFuture = b.bind(port).sync();
            serverStatus = true;
            System.out.println("Server sucsessfully started");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public void shutdown(){
        serverStatus = false;
        channelFuture.channel().close();
        channelFuture.channel().parent().close();
        System.out.println("Server successfully shutdowned");
    }
}
