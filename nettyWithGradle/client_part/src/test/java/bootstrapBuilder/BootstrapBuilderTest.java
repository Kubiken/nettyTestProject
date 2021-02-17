package bootstrapBuilder;

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
    public void sendNullsAsParamsToBootstrap_expectingNullPointer() {
        assertThrows(NullPointerException.class, ()->{
            Bootstrap b = new Bootstrap();
            BootstrapBuilder.bootstrapBuilder(null, null, null, null);
        });
    }

    @Test
    public void tryBuildBootstrapInctance_expectingCorrectBootstrapObject(){

        Bootstrap b = Mockito.spy(Bootstrap.class);
        EventLoopGroup elg = Mockito.mock(EventLoopGroup.class);
        Class socketChanel = SocketChannel.class;
        HashMap<ChannelOption, Object> options = new HashMap<>();
        options.put(ChannelOption.SO_KEEPALIVE, true);

        BootstrapBuilder.bootstrapBuilder(elg, socketChanel, options, b);

        verify(b).group(elg);
        verify(b).channel(socketChanel);
        for (Map.Entry<ChannelOption, Object> entry : options.entrySet())
            verify(b).option(entry.getKey(), entry.getValue());
    }



}