package tests.clientPart;

import io.netty.channel.EventLoopGroup;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testPairTcp.clientPart.NettyClient;

import static org.junit.jupiter.api.Assertions.*;

class NettyClientTest {

    @Test
    public void paramsNullCheck(){
        assertThrows(NullPointerException.class, ()->{
            NettyClient.run(null,0,null);
        });
    }

    @Test
    public void tryRun(){
        EventLoopGroup elg = Mockito.mock(EventLoopGroup.class);
        try{
        NettyClient.run("localhost", 8080, elg);
        }catch (Exception e){
            fail("Can't start client");
        }
    }

}