package clientPart;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import net.openhft.chronicle.core.Jvm;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class NettyClientTest {

    @Test
    public void paramsNullCheck(){
        assertThrows(NullPointerException.class, ()->{
            NettyClient nettyClient = new NettyClient();
            nettyClient.run(null,0,null, null);
        });
    }

    @Test
    public void clientTryToConnectToShutdownedServerWithoutRetry(){
        NettyClient nettyClient = new NettyClient();

        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = spy(NioEventLoopGroup.class);
        Bootstrap b = Mockito.spy(Bootstrap.class);

        System.setIn(new ByteArrayInputStream("n".getBytes()));
        System.setIn(System.in);

        nettyClient.run(host, port, workerGroup,b);

        verify(b).connect(host, port);
        verify(workerGroup).shutdownGracefully();
    }



}