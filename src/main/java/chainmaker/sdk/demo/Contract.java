package chainmaker.sdk.demo;

import org.chainmaker.pb.common.ContractOuterClass;
import org.chainmaker.pb.common.Request;
import org.chainmaker.pb.common.ResultOuterClass;
import org.chainmaker.sdk.ChainClient;
import org.chainmaker.sdk.User;
import org.chainmaker.sdk.utils.FileUtils;
import org.chainmaker.sdk.utils.SdkUtils;

import java.util.HashMap;


public class Contract {

    private static final String QUERY_CONTRACT_METHOD = "find_by_file_hash";
    private static final String INVOKE_CONTRACT_METHOD = "save";
    private static final String CONTRACT_NAME = "test_contract_01";
    private static final String CONTRACT_FILE_PATH = "src/main/resources/rust-fact-2.0.0.wasm";

    public static void createContract(ChainClient chainClient, User user) {
        ResultOuterClass.TxResponse responseInfo = null;
        try {
            byte[] byteCode = FileUtils.getResourceFileBytes(CONTRACT_FILE_PATH);

            // 1. create payload
            Request.Payload payload = chainClient.createContractCreatePayload(CONTRACT_NAME, "1", byteCode,
                    ContractOuterClass.RuntimeType.WASMER, null);
            //2. create payloads with endorsement
            Request.EndorsementEntry[] endorsementEntries = SdkUtils
                    .getEndorsers(payload, new User[]{user});

            // 3. send request
            responseInfo = chainClient.sendContractManageRequest(payload, endorsementEntries, 10000, 10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("创建合约结果：");
        System.out.println(responseInfo);
    }
    public static void invokeContract(ChainClient chainClient) {
        ResultOuterClass.TxResponse responseInfo1 = null;
        ResultOuterClass.TxResponse responseInfo2 = null;
        try {

            HashMap<String, byte[]> parames01 = new HashMap<>();
            parames01.put("file_hash","12580".getBytes());
            parames01.put("file_name","12580".getBytes());
            parames01.put("time","12580".getBytes());
            responseInfo1 = chainClient.invokeContract(CONTRACT_NAME, INVOKE_CONTRACT_METHOD,
                    null, parames01,100000, 100000);

            HashMap<String, byte[]> parames02 = new HashMap<>();
            parames02.put("file_hash","12580".getBytes());
            responseInfo2 = chainClient.queryContract(CONTRACT_NAME,QUERY_CONTRACT_METHOD,null,parames01,10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("执行合约结果：");
        System.out.println(responseInfo1);
        System.out.println(responseInfo2);
    }

    public static void queryContract(ChainClient chainClient) {
        ResultOuterClass.TxResponse responseInfo = null;
        try {
            responseInfo = chainClient.queryContract(CONTRACT_NAME, QUERY_CONTRACT_METHOD,
                    null,  null,10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("查询合约结果：");
        System.out.println(responseInfo);
    }
}
