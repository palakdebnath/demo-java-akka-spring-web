package demo.actor;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedActor;
import demo.model.Message;
import demo.service.DeleteService;

@Component("deleteActor")
@Scope("prototype")
public class DeleteActor extends UntypedActor {

    @Autowired
    private DeleteService deleteService;

    final private CompletableFuture<Message> future;

    public DeleteActor(CompletableFuture<Message> future) {
        this.future = future;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        deleteService.perform(this, (Message)message);

        if (message instanceof Message) {
            future.complete((Message) message);
        } else {
            unhandled(message);
        }

        getContext().stop(self());
    }
}
