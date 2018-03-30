package demo.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import demo.model.Message;
import demo.service.CompletableFutureService;

@RestController
public class DeferredResultController {

    private static final Long DEFERRED_RESULT_TIMEOUT = 1000L;

    private AtomicLong id = new AtomicLong(0);

    @Autowired
    private CompletableFutureService completableFutureService;

    @RequestMapping("/async-non-blocking")
    @ResponseBody
    public DeferredResult<Message> getAsyncNonBlocking() {
        DeferredResult<Message> deferred = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);
        CompletableFuture<Message> future = completableFutureService.get("async-non-blocking", id.getAndIncrement());
        future.whenComplete((result, error) -> {
            if (error != null) {
                deferred.setErrorResult(error);
            } else {
                deferred.setResult(result);
            }
        });
        return deferred;
    }
    
    @RequestMapping(value="/insert/{name}", method=RequestMethod.POST)
    @ResponseBody
    public DeferredResult<Message> insert(@PathVariable("name") String name) {
        DeferredResult<Message> deferred = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);
        CompletableFuture<Message> future = completableFutureService.getInsert("insert", name, id.getAndIncrement());
        future.whenComplete((result, error) -> {
            if (error != null) {
                deferred.setErrorResult(error);
            } else {
                deferred.setResult(result);
            }
        });
        return deferred;
    }
    
    @RequestMapping(value="/delete/{identity}", method=RequestMethod.DELETE)
    @ResponseBody
    public DeferredResult<Message> delete(@PathVariable("identity") String identity) {
        DeferredResult<Message> deferred = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);
        CompletableFuture<Message> future = completableFutureService.getDelete("delete", identity, id.getAndIncrement());
        future.whenComplete((result, error) -> {
            if (error != null) {
                deferred.setErrorResult(error);
            } else {
                deferred.setResult(result);
            }
        });
        return deferred;
    }
    
    @RequestMapping(value="/display", method=RequestMethod.GET)
    @ResponseBody
    public DeferredResult<Message> display() {
        DeferredResult<Message> deferred = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);
        CompletableFuture<Message> future = completableFutureService.getDisplay("display", id.getAndIncrement());
        future.whenComplete((result, error) -> {
            if (error != null) {
                deferred.setErrorResult(error);
            } else {
                deferred.setResult(result);
            }
        });
        return deferred;
    }
}
