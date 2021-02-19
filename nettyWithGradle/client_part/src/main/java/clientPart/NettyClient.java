package clientPart;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.openhft.chronicle.core.Jvm;
import bootstrapBuilder.BootstrapBuilder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class NettyClient {
    public static void main(String[]args) throws Exception {

        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        NettyClient nettyClient = new NettyClient();
        Bootstrap b = new Bootstrap();
        nettyClient.run(host, port, workerGroup, b);

    }

    public void run(String host, int port, EventLoopGroup workerGroup, Bootstrap b){
        if(host == null || port == 0 || workerGroup == null)
            throw new NullPointerException("One of parametr equals null: host-"+host+
                    " port-"+port+
                    " workerGroup-"+workerGroup);
        try {
            Scanner in = new Scanner(System.in);
            HashMap<ChannelOption, Object> options = new HashMap<>();
            options.put(ChannelOption.SO_KEEPALIVE, true);
            BootstrapBuilder.bootstrapBuilder(workerGroup,NioSocketChannel.class, options, b);

            ChannelFuture f = null;
            while(true) {
                try {
                    f = b.connect(host, port).sync();
                break;
                    } catch (Exception e) {
                        Jvm.pause(5000);
                        System.out.println("Connection failed. Retry? [Y/N]");
                        String answer = in.nextLine().toLowerCase(Locale.ROOT);

                        if(!answer.equals("y")) {
                            System.out.println("Shutting down clent");
                            return;
                        }
                    System.out.println("Conection retry...");
                        }
                    }

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
