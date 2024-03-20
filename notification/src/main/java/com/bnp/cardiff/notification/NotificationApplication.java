package com.bnp.cardiff.notification;

import com.bnp.cardiff.notification.events.ClaimRequestEvent;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}
	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(ClaimRequestEvent claimRequestEvent){
		log.info("Received Notification for claimReques- {}",claimRequestEvent.getMessage());

	}


}
