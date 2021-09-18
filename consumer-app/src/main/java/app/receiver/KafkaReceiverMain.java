package app.receiver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaReceiverMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(() -> readMessages(0));
        executorService.submit(() -> readMessages(1));
        executorService.submit(() -> readMessages(2));
//        executorService.submit(() -> readMessages(0));
    }

    private static void readMessages(int partition) {
        KafkaReceiver kafkaReceiver = new KafkaReceiver(partition);
        kafkaReceiver.readMessages();
    }
}
