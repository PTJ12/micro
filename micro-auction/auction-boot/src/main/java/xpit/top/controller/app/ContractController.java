package xpit.top.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xpit.top.pojo.entity.Bid;
import xpit.top.result.Result;
import xpit.top.service.IContractService;

/**
 * @Author PuTongjiao
 * @Date 2022/10/31 15:34
 */
@RestController
@RequestMapping("/auction/contract")
public class ContractController {

    @Autowired
    private IContractService contractService;

    @GetMapping("totalBids/{productId}")
    public Result totalBids(@PathVariable long productId) throws Exception {
        return Result.okResult(contractService.totalBids(productId));
    }

    @GetMapping("finalizeAuction/{productId}")
    public Result finalizeAuction(@PathVariable long productId) throws Exception {
        return Result.okResult(contractService.finalizeAuction(productId));
    }

    @PostMapping("bid")
    public Result bid(@RequestBody Bid bid) throws Exception {
        return Result.okResult(contractService.bid(bid));
    }

    @GetMapping("revealBid")
    public Result revealBid(Bid bid) throws Exception {
        return Result.okResult(contractService.revealBid(bid));
    }

    @GetMapping("highestBidderInfo/{productId}")
    public Result highestBidderInfo(@PathVariable long productId) throws Exception {
        return Result.okResult(contractService.highestBidderInfo(productId));
    }

    @GetMapping("getProduct/{productId}")
    public Result getProduct(@PathVariable long productId) throws Exception {
        return Result.okResult(contractService.getProduct(productId));
    }

    @GetMapping("releaseAmountToSeller/{productId}")
    public Result releaseAmountToSeller(@PathVariable long productId) throws Exception {
        return Result.okResult(contractService.releaseAmountToSeller(productId));
    }

    @GetMapping("refundAmountToBuyer/{productId}")
    public Result refundAmountToBuyer(@PathVariable long productId) throws Exception {
        return Result.okResult(contractService.refundAmountToBuyer(productId));
    }

    @GetMapping("getBalance")
    public Result getBalance() throws Exception {
        return Result.okResult(contractService.getBalance());
    }

}
