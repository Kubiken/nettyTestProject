package testPairTcp.clientPart;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import testPairTcp.bootstrapBuilder.BootstrapBuilder;

import java.util.HashMap;

public class NettyClient {
    public static void main(String[]args) throws Exception {

        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        run(host, port, workerGroup);

    }

    public static void run(String host, int port, EventLoopGroup workerGroup){
        if(host == null || port == 0 || workerGroup == null)
            throw new NullPointerException("One of parametr equals null: host -"+host+
                    " port-"+port+
                    " workerGroup-"+workerGroup);
        try {
            HashMap<ChannelOption, Object> options = new HashMap<>();
            options.put(ChannelOption.SO_KEEPALIVE, true);

            Bootstrap b = BootstrapBuilder.bootstrapBuilder(workerGroup,NioSocketChannel.class, options);

            ChannelFuture f = b.connect(host, port).sync();
            System.out.println("Client sucsessfully started");

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
            finally {
            workerGroup.shutdownGracefully();
        }
    }
}
