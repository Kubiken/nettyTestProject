package testPairTcp.mainLoader;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import net.openhft.chronicle.core.Jvm;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import testPairTcp.CQ.OutputRunnable;
import testPairTcp.clientPart.NettyClient;
import testPairTcp.serverPart.NettyServer;
import io.netty.channel.EventLoopGroup;

import java.util.concurrent.CompletableFuture;

public class MainLoader {
    public static void main(String[] args){

        String host = "localhost";
        int port = 8080;

        NettyServer nettyServer = new NettyServer();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("Trying to start server");
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                nettyServer.run(port, bossGroup, workerGroup);
            }});

        Jvm.pause(5000);

        NettyClient nettyClient = new NettyClient();
        EventLoopGroup workerGroupClient = new NioEventLoopGroup();
        System.out.println("Trying to start client");
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                nettyClient.run(host, port, workerGroupClient);
            }});


        String path = "TestPairTcp";
        SingleChronicleQueue queue = SingleChronicleQueueBuilder.binary(path).build();
        ExcerptTailer tailer = queue.createTailer();

        System.out.println("Trying to read queue");

        while(true){
           OutputRunnable.readTailer(tailer);
        }
    }
}
