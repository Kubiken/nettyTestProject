package testPairTcp.serverPart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

class NettyServerTest {

    @Test
    public void runWithNulls_expectingNullPointerException(){
        assertThrows(NullPointerException.class,
                ()-> {
            NettyServer nettyServer = new NettyServer();
                    nettyServer.run(0, null, null, null);
                });
    }


}