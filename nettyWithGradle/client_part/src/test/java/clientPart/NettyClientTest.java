package clientPart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class NettyClientTest {

    @Test
    public void paramsNullCheck(){
        assertThrows(NullPointerException.class, ()->{
            NettyClient nettyClient = new NettyClient();
            nettyClient.run(null,0,null);
        });
    }


}