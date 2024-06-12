package com.rmilab.restcontroller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
@RestController
public class RESTDockindSSEController {
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping("/stream-updates")
    public SseEmitter streamUpdates() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));

        return emitter;
    }
    public void sendUpdateToClients(String update) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(update);
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }
}
