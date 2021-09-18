package app.receiver;

import app.common.KafkaHelper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;

public class KafkaReceiver {

    private static String TOPIC_NAME = "ticker_partitions";

    private static int MSG_COUNT = 4;

    KafkaConsumer<String, String> consumer;

    public KafkaReceiver(int partition) {
        consumer = new KafkaConsumer<>(KafkaHelper.getConsumerProps(TOPIC_NAME));
        consumer.assign(Collections.singleton(new TopicPartition(TOPIC_NAME, partition)));
//        consumer.subscribe(Collections.singleton(TOPIC_NAME));
    }

    public void readMessages() {
        int numMsgReceived = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
            for (ConsumerRecord<String, String> record : records) {
                numMsgReceived++;
                System.out.printf("consumed: key = %s, value = %s, partition id= %s, offset = %s%n",
                    record.key(), record.value(), record.partition(), record.offset());
            }
            consumer.commitSync();
        }
    }
}
