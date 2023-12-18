/*
 * Copyright 2021-Current jittagornp.me
 */
package com.mera.defi;

import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;

import java.math.BigDecimal;
import java.util.function.Consumer;

/**
 * @author jittagornp
 */
@Slf4j
public class DeFiTest {

    private static final String WALLET_DIRECTORY = System.getProperty("user.home") + "/crypto-wallet";
    private static final String WALLET_FILE_NAME = "<YOUR_WALLET_FILE>.json";
    private static final String WALLET_PASSWORD = "<YOUR_WALLET_PASSWORD>";

    public static void main(String[] args) throws Exception {

       // final Credentials credentials = WalletUtils.loadCredentials(WALLET_PASSWORD, new File(WALLET_DIRECTORY, WALLET_FILE_NAME));
      final Credentials credentials = Credentials.create("90579008541a46995c8c7c8bd48e8df7dd61a735f40c876e133fe9a09ef082ca");
      //bsc(credentials);
        polygon(credentials);
        //bitkub(credentials);
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
        deFi.onTransfer(BUSD, log("Event Transfer"));
    }

    private static void polygon(final Credentials credentials) throws Exception {

        final String quickSwapRouter = "0xF491e7B69E4244ad4002BC14e878a34207E38c29";
        final String WFTM = "0x21be370D5312f44cB42ce377BC9b8a0cEF1A4C83";
        final String FUSDT = "0x049d68029688eAbF473097a2fC38ef61633A3C7A";

        final DeFi deFi = DeFiSDK.fantomNet(credentials)
                .setTokenAutoApproveNTimes(3)
                .setDefaultSwapSlippage(0.5)
                .setDefaultSwapDeadlineMinutes(10);
      deFi.getTokenAmountsOut(quickSwapRouter,"0x04068DA6C83AFCFA0e13ba15A6696662335D5B75","0x21be370D5312f44cB42ce377BC9b8a0cEF1A4C83", BigDecimal.valueOf(Math.abs(-2))).thenAccept(log(" Amount out")).get();
      deFi.getTokenPrice("0x04068DA6C83AFCFA0e13ba15A6696662335D5B75","0x21be370D5312f44cB42ce377BC9b8a0cEF1A4C83",quickSwapRouter).thenAccept(log( "  Price :")).get();

        //deFi.getGasPrice().thenAccept(log("Gas price")).get();
        //deFi.getGasBalance().thenAccept(log("Gas balance")).get();
       // deFi.getTokenBalance(WFTM).thenAccept(log("WFTM Token Balance")).get();
        //deFi.getTokenBalance(FUSDT).thenAccept(log("FUSDT Token Balance")).get();
        ///deFi.getTokenAmountsOut(quickSwapRouter, WFTM, FUSDT, BigDecimal.ONE).thenAccept(log("WFTM Amount out")).get();
       // deFi.getTokenPrice(WFTM, FUSDT, quickSwapRouter).thenAccept(log("FUSDT Price")).get();
       // deFi.getTokenInfo(WFTM, FUSDT, quickSwapRouter).thenAccept(log("FUSDT")).get();
      //  deFi.getTokenInfo(FUSDT, FUSDT, quickSwapRouter).thenAccept(log("FUSDT")).get();
       // deFi.getTokenAllowance(FUSDT, quickSwapRouter).thenAccept(log("Token allowance")).get();
      //  deFi.tokenApprove(WFTM, BigDecimal.valueOf(1), quickSwapRouter).thenAccept(log("Approve Tx")).get();
        //deFi.tokenSwap(quickSwapRouter, WFTM, FUSDT, BigDecimal.valueOf(1)).thenAccept(log("Swap Tx")).get();
        //deFi.tokenSwapAndAutoApprove(quickSwapRouter, WMATIC, USDC, BigDecimal.valueOf(5.8)).thenAccept(log("Swap Tx")).get();
        //deFi.tokenTransfer(WMATIC, "<Target>", BigDecimal.valueOf(0.0013)).thenAccept(log("Transfer Tx")).get();
        //deFi.tokenApprove(WMATIC, BigDecimal.TEN, quickSwapRouter).thenAccept(log("Token Approve Tx")).get();
        //deFi.tokenSwap(quickSwapRouter, USDC, WMATIC, deFi.getTokenBalance(USDC).get()).thenAccept(log("Swap Tx")).get();
        //deFi.tokenSwap(quickSwapRouter, WMATIC, USDC, deFi.getTokenBalance(WMATIC).get()).thenAccept(log("Swap Tx")).get();
        //deFi.fillGas(deFi.getTokenBalance(WFTM).get()).thenAccept(log("Fill Gas Tx")).get();
        //deFi.tokenSwapAndFillGas(quickSwapRouter, USDC, WMATIC, BigDecimal.ONE).thenAccept(log("Swap and Fill Gas Tx")).get();
        //deFi.onBlock(block -> log("Block number").accept(block.getNumber()));
        //deFi.onTransfer(USDC, log("Event Transfer"));
    }

    private static <T> Consumer<T> log(final String message) {
        return (T value) -> log.info("{} => {}", message, value);
    }

}
