package com.bnp.cardiff.notification;

import com.bnp.cardiff.notification.Service.ClaimRequestEventService;
import com.bnp.cardiff.notification.events.ClaimRequestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class NotificationApplication {
	private final ClaimRequestEventService claimRequestEventService;
	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}
	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(ClaimRequestEvent claimRequestEvent){

		claimRequestEventService.createEvent(claimRequestEvent);
		log.info("Received Notification for claimReques- {}",claimRequestEvent.getMessage());

	}


}
