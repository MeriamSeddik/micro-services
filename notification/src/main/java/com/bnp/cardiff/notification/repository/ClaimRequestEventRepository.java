package com.bnp.cardiff.notification.repository;

import com.bnp.cardiff.notification.events.ClaimRequestEvent;
import com.bnp.cardiff.notification.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRequestEventRepository extends MongoRepository<Event,String> {
}
