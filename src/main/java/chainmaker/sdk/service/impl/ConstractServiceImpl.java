package chainmaker.sdk.service.impl;

import chainmaker.sdk.service.ContractService;
import org.chainmaker.pb.common.ResultOuterClass;
import org.chainmaker.sdk.ChainClient;

import java.util.HashMap;
import java.util.Map;

public class ConstractServiceImpl implements ContractService {

    /**
     * 合约查询数据方法名
     */
    private static final String QUERY_CONTRACT_METHOD = "find_by_file_hash";
    /**
     * 合约保存数据方法名
     */
    private static final String INVOKE_CONTRACT_METHOD = "save";
    /**
     * 合约在链上的名称
     */
    private static final String CONTRACT_NAME = "test_contract_01";
    /**
     * 合约文件路径
     */
    private static final String CONTRACT_FILE_PATH = "src/main/resources/rust-fact-2.0.0.wasm";

    @Override
    public ResultOuterClass.TxResponse invokeContract(ChainClient chainClient, String hashKey, String data) {
        ResultOuterClass.TxResponse responseInfo = null;
        try {
            HashMap<String, byte[]> parames = new HashMap<>();
            parames.put("file_hash",hashKey.getBytes());
            parames.put("file_name",data.getBytes());
            parames.put("time","12580".getBytes());
            responseInfo = chainClient.invokeContract(CONTRACT_NAME, INVOKE_CONTRACT_METHOD, null, parames,100000, 100000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("执行合约结果：");
        System.out.println(responseInfo);

        return responseInfo;
    }

}
