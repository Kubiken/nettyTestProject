package tests.clientPart;

import io.netty.channel.EventLoopGroup;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sun.nio.ch.Net;
import testPairTcp.clientPart.NettyClient;

import static org.junit.jupiter.api.Assertions.*;

class NettyClientTest {

    @Test
    public void paramsNullCheck(){
        assertThrows(NullPointerException.class, ()->{
            NettyClient nettyClient = new NettyClient();
            nettyClient.run(null,0,null);
        });
    }

    @Test
    public void tryStartClient(){
        EventLoopGroup elg = Mockito.mock(EventLoopGroup.class);
        try{
            NettyClient nettyClient = new NettyClient();
        nettyClient.run("localhost", 8080, elg);

        }catch (Exception e){
            fail("Can't start client");
        }
    }

}