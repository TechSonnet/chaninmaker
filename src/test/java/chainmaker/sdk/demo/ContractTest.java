package chainmaker.sdk.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static chainmaker.sdk.demo.InitClient.inItChainClient;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContractTest {

    @Test
    void invokeContract() throws Exception {
        // 初始化这一步是至关重要的
        inItChainClient();
        Contract.invokeContract(InitClient.chainClient,"test001","test001");
    }
}