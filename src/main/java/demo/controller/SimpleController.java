package demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import demo.service.DisplayService;
import demo.service.InsertService;

@RestController
@RequestMapping(value="/v2")
public class SimpleController {
	
	private static final Long DEFERRED_RESULT_TIMEOUT = 1000L;

	@Autowired
	DisplayService displayService;
	
	@Autowired
	InsertService insertService;
	
	private AtomicLong counter = new AtomicLong(0);

	@RequestMapping(value="/display", method=RequestMethod.GET)
	@ResponseBody
	public String display() throws InterruptedException {

		displayService.perform();
		return "Success";
	}
	
	@RequestMapping(value = "/deferreddisplay", method = RequestMethod.GET)
	public DeferredResult<String> timeDeferred() {
	    
	    DeferredResult<String> result = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);

	    new Thread(() -> {
	        try {
				result.setResult(displayService.perform());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }, "MyThread-" + counter.incrementAndGet()).start();

	    return result;
	}
	

	@RequestMapping(value="/insert/{name}", method=RequestMethod.POST)
	@ResponseBody
	public String insert(@PathVariable("name") String name) {

		insertService.perform("ZZZ");
		return "Success";
	}
}
