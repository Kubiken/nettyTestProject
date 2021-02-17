package testPairTcp.bootstrapBuilder;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

class BootstrapBuilderTest {

    @Test
    public void bootstrapServerInitWithNulls_expectingNullPointerException() {
        assertThrows(NullPointerException.class, ()->{
        ServerBootstrap b = new ServerBootstrap();
        BootstrapBuilder.serverBootstrapBuilder(null, null, null, null, b);
        });
    }

    @Test
    public void bootstrapServerTryToStart_expectingSuccessfulStart(){
        ServerBootstrap b = Mockito.spy(ServerBootstrap.class);
        EventLoopGroup bossElg = Mockito.mock(EventLoopGroup.class);
        EventLoopGroup workerElg =Mockito.mock(EventLoopGroup.class);
        Class socketChanel = SocketChannel.class;
        HashMap<ChannelOption, Object> options = new HashMap<>();
        options.put(ChannelOption.SO_KEEPALIVE, true);

        BootstrapBuilder.serverBootstrapBuilder(bossElg, workerElg, socketChanel, options, b);

        verify(b).group(bossElg, workerElg);
        verify(b).channel(socketChanel);
        for (Map.Entry<ChannelOption, Object> entry : options.entrySet())
            verify(b).option(entry.getKey(), entry.getValue());
    }
    }

