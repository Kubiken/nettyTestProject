package tests.bootstrapBuilder;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testPairTcp.bootstrapBuilder.BootstrapBuilder;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BootstrapBuilderTest {

    @Test
    public void notNULLBuildedBootstrap() {
        Bootstrap b = new Bootstrap();
        EventLoopGroup elg = Mockito.mock(EventLoopGroup.class);
        HashMap<ChannelOption, Object> options = new HashMap<>();

        b = BootstrapBuilder.bootstrapBuilder(elg, NioServerSocketChannel.class, options);
        assertNotNull(b);
    }

    @Test
    public void bootstrapBuilderNullableParametrs() {
        assertThrows(NullPointerException.class, ()->{
            Bootstrap b = new Bootstrap();
            b = BootstrapBuilder.bootstrapBuilder(null, null, null);
        });
    }


    @Test
    public void notNULLServerBootstrapBuilder() {
        ServerBootstrap b = new ServerBootstrap();
        EventLoopGroup elg = Mockito.mock(EventLoopGroup.class);
        EventLoopGroup elgs = Mockito.mock(EventLoopGroup.class);
        HashMap<ChannelOption, Object> options = new HashMap<>();

        b = BootstrapBuilder.serverBootstrapBuilder(elg, elgs, NioServerSocketChannel.class, options);
        assertNotNull(b);
    }

    @Test
    public void bootstrapServerBuilderNullableParametrs() {
        assertThrows(NullPointerException.class, ()->{
        ServerBootstrap b = new ServerBootstrap();
        b = BootstrapBuilder.serverBootstrapBuilder(null, null, null, null);
        });
    }

}