package com.ca.p;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import com.mera.defi.DeFi;
import com.mera.defi.DeFiSDK;
import com.mera.defi.smartcontract.Router;
import me.jittagornp.defi.smartcontract.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Ethereum;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Async;
import org.web3j.utils.Convert;


public class NewPairsTracker {
  public static String RPC_URL = "https://bsc.getblock.io/471f5718-5292-4ea0-a153-daa6affce6ba/mainnet/";

  private static final String WS_URL ="wss://bsc-mainnet.rpcfast.com/ws?api_key=vDHPyyMp9C1EQ7cNddr9cKUwGU8v7RamsavrmKDv8r4BTV1Yn7qHzX70bG4sb3kT" ;
  private static  Web3j web3j = null;//Web3j.build(new WebSocketService(WS_URL,true));


  private static final String pancakeFactoryAddress = "0xcA143Ce32Fe78f1f7019d7d551a6402fC5350c73";
  private static final BigInteger amount = Convert.toWei(BigDecimal.valueOf(0.1), Convert.Unit.ETHER).toBigInteger();
  private static final BigInteger deadline = BigInteger.valueOf((System.currentTimeMillis() / 1000) + (600 * 30));

  private static final Logger logger = LoggerFactory.getLogger(NewPairsTracker.class);


  public EthBlockNumber getBlockNumber() throws ExecutionException, InterruptedException {
    EthBlockNumber result = new EthBlockNumber();
    result = this.web3j.ethBlockNumber()
      .sendAsync()
      .get();
    return result;
  }
  public static ScheduledExecutorService defaultExecutorService() {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    return scheduledExecutorService;
  }
  public static  void main(String args[]){

    NewPairsTracker a = new NewPairsTracker();
    final Credentials credentials = Credentials.create("90579008541a46995c8c7c8bd48e8df7dd61a735f40c876e133fe9a09ef082ca");
    try {



      web3j = Web3j.build(new HttpService("https://bsc-mainnet.nodereal.io/v1/c39cf5b992844862a28cf386f68d310e"),1000,  defaultExecutorService());
      System.out.println(web3j);

      Factory pan = Factory.load(pancakeFactoryAddress,web3j,credentials,new DefaultGasProvider());
       System.out.println(pan.allPairsLength().send());

//      pan.pairCreatedEventFlowable(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST).subscribe(s->{
//
//        logger.info(s.token0+":"+s.token1+":"+s.pair );
//
//
//      }, t -> logger.error("Error during scanning of pending transactions.", t));

       web3j.pendingTransactionFlowable().subscribe(web3jTx -> {
        try {
          logger.info(web3jTx.getGasPrice().toString());
        } catch (Throwable t) {
          logger.error("Error while processing transaction.", t);
        }
      }, t -> logger.error("Error during scanning of pending transactions.", t));

    //  Factory f = Factory.load(pancakeFactoryAddress,web3j,credentials, new DefaultGasProvider());

     // System.out.println(f.allPairsLength().send());

      //f.pairCreatedEventFlowable(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST).subscribe();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  private static void bsc(final Credentials credentials) throws Exception {

    final String pancakeSwapRouter = "0x10ED43C718714eb63d5aA57B78B54704E256024E";
    final String WBNB = "0xbb4CdB9CBd36B01bD1cBaEBF2De08d9173bc095c";
    final String BUSD = "0xe9e7cea3dedca5984780bafc599bd69add087d56";

    final DeFi deFi = DeFiSDK.bscMainnet(credentials)
      .setTokenAutoApproveNTimes(3)
      .setDefaultSwapSlippage(0.5)
      .setDefaultSwapDeadlineMinutes(10);


    // deFi.getGasPrice().thenAccept(log("Gas price")).get();
    // deFi.getGasBalance().thenAccept(log("Gas balance")).get();
    // deFi.getTokenAmountsOut(pancakeSwapRouter, WBNB, BUSD, BigDecimal.ONE).thenAccept(log("WBNB Amount out")).get();
    // deFi.getTokenPrice(WBNB, BUSD, pancakeSwapRouter).thenAccept(log("WBNB Price")).get();
    // deFi.getTokenInfo(WBNB, BUSD, pancakeSwapRouter).thenAccept(log("WBNB")).get();
    //deFi.getTokenInfo(BUSD, BUSD, pancakeSwapRouter).thenAccept(log("BUSD")).get();
    // deFi.getTokenAllowance(BUSD, pancakeSwapRouter).thenAccept(log("Token allowance")).get();
    //deFi.tokenApprove(BUSD, BigDecimal.valueOf(2.433), pancakeSwapRouter).thenAccept(log("Approve Tx")).get();
    // deFi.tokenSwap(pancakeSwapRouter, WBNB, BUSD, BigDecimal.valueOf(5.8)).thenAccept(log("Swap Tx")).get();
    //deFi.tokenSwapAndAutoApprove(pancakeSwapRouter, WBNB, BUSD, BigDecimal.valueOf(5.8)).thenAccept(log("Swap Tx")).get();
    //deFi.tokenTransfer(WBNB, "<Target>", BigDecimal.valueOf(0.0013)).thenAccept(log("Transfer Tx")).get();
    //deFi.tokenApprove(WBNB, BigDecimal.TEN, pancakeSwapRouter).thenAccept(log("Token Approve Tx")).get();
    //deFi.tokenSwap(pancakeSwapRouter, BUSD, WBNB, deFi.getTokenBalance(BUSD).get()).thenAccept(log("Swap Tx")).get();
    //deFi.tokenSwap(pancakeSwapRouter, WBNB, BUSD, deFi.getTokenBalance(WBNB).get()).thenAccept(log("Swap Tx")).get();
    //deFi.fillGas(WBNB, deFi.getTokenBalance(WBNB).get()).thenAccept(log("Fill Gas Tx")).get();
    //deFi.tokenSwapAndFillGas(pancakeSwapRouter, BUSD, WBNB, BigDecimal.ONE).thenAccept(log("Swap and Fill Gas Tx")).get();
    //deFi.onBlock(block -> log("Block number").accept(block.getNumber()));

  }
}
