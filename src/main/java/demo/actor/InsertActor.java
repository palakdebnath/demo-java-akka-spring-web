package demo.actor;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedActor;
import demo.model.Message;
import demo.service.InsertService;

@Component("insertActor")
@Scope("prototype")
public class InsertActor extends UntypedActor {

    @Autowired
    private InsertService insertService;

    final private CompletableFuture<Message> future;

    public InsertActor(CompletableFuture<Message> future) {
        this.future = future;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        insertService.perform(this, (Message)message);

        if (message instanceof Message) {
            future.complete((Message) message);
        } else {
            unhandled(message);
        }

        getContext().stop(self());
    }
}
