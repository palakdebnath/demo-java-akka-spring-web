package demo.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import demo.di.SpringExtension;
import demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CompletableFutureService {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;
    
    private AtomicLong actorId = new AtomicLong(0);

    public CompletableFuture<Message> get(String action, Long id) {
        CompletableFuture<Message> future = new CompletableFuture<>();
        ActorRef workerActor = actorSystem.actorOf(springExtension.props("workerActor", future), "worker-actor");
        workerActor.tell(new Message(action, id), null);
        return future;
    }
    
    public CompletableFuture<Message> getInsert(String action, String data, Long id) {
        CompletableFuture<Message> future = new CompletableFuture<>();
        ActorRef workerActor = actorSystem.actorOf(springExtension.props("insertActor", future), "insert-actor");
        workerActor.tell(new Message(action, data, id), null);
        return future;
    }
    
    
    public CompletableFuture<Message> getDelete(String action, String data, Long id) {
        CompletableFuture<Message> future = new CompletableFuture<>();
        ActorRef workerActor = actorSystem.actorOf(springExtension.props("deleteActor", future), "delete-actor");
        workerActor.tell(new Message(action, data, id), null);
        return future;
    }
    
    
    public CompletableFuture<Message> getDisplay(String action, Long id) {
        CompletableFuture<Message> future = new CompletableFuture<>();
        ActorRef workerActor = actorSystem.actorOf(springExtension.props("displayActor", future), "display-actor" + actorId.getAndIncrement());
        workerActor.tell(new Message(action, id), null);
        return future;
    }
}
