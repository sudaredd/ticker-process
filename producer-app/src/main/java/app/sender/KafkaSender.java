package app.sender;

import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import app.common.KafkaHelper;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaSender {

    private static int PARTITION_COUNT = 3;

    private static String TOPIC_NAME = "ticker_partitions";

    private static int MSG_COUNT = 4;

    KafkaProducer producer = new KafkaProducer<>(KafkaHelper.getProducerProps());

    @SneakyThrows
    public void sendMessages() {

        for (int i = 0; i < MSG_COUNT; i++) {
            for (int partitionId = 0; partitionId < PARTITION_COUNT; partitionId++) {
                String value = String.format("Message id %s and partitionid %s", i, partitionId);
                String key = Integer.toString(i);
                System.out.printf("Sending message topic: %s, key: %s, value: %s, partition id: %s%n",
                    TOPIC_NAME, key, value, partitionId);
                producer.send(new ProducerRecord<>(TOPIC_NAME, partitionId, key, value));
            }
            System.out.println("Sleeping");
            Thread.sleep(4000);
        }
    }
}
