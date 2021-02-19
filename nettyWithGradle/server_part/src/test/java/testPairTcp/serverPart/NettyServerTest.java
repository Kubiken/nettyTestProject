package testPairTcp.serverPart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import net.openhft.chronicle.core.Jvm;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testPairTcp.bootstrapBuilder.BootstrapBuilder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

class NettyServerTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;


    @BeforeAll
    static void outStreamsStateReading(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    static void returnOutStreamsStateToNormal(){
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void runWithNulls_expectingNullPointerException(){
        assertThrows(NullPointerException.class,
                ()-> {
            NettyServer nettyServer = new NettyServer();
                    nettyServer.run(0, null, null, null);
                });
    }

    @Test
    public void runNettyServer_expectingCorrectRun(){

        ServerBootstrap b = Mockito.spy(ServerBootstrap.class);

        NettyServer nettyServer = new NettyServer();
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                while(!nettyServer.isServerStatus())
                    Jvm.pause(10);
                nettyServer.shutdown();
            }
        });
        nettyServer.run(8080, new NioEventLoopGroup(),
                new NioEventLoopGroup(), b);

        verify(b).bind(8080);

        String expected = "Server sucsessfully started\n";
        String actual = outContent.toString();

        assertEquals(expected, actual);

    }


}