package tests.serverPart;

import org.junit.jupiter.api.Test;
import testPairTcp.serverPart.NettyServer;

import static org.junit.jupiter.api.Assertions.*;

class NettyServerTest {

    @Test
    public void runWithNulls(){
        assertThrows(NullPointerException.class,
                ()-> {
                    NettyServer.run(0, null, null);
                });
    }

}