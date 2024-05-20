package chainmaker.sdk.service;

import org.chainmaker.pb.common.ResultOuterClass;
import org.chainmaker.sdk.ChainClient;

public interface ContractService {

    ResultOuterClass.TxResponse invokeContract(ChainClient chainClient, String hashKey, String data);

}
