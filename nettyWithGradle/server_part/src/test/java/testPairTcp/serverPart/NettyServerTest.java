package testPairTcp.serverPart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NettyServerTest {

    @Test
    public void runWithNulls(){
        assertThrows(NullPointerException.class,
                ()-> {
            NettyServer nettyServer = new NettyServer();
                    nettyServer.run(0, null, null);
                });
    }

}