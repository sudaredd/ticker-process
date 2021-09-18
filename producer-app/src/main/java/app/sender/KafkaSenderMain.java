package app.sender;

public class KafkaSenderMain {

    public static void main(String[] args) {
        KafkaSender kafkaSender = new KafkaSender();
        kafkaSender.sendMessages();
    }
}
