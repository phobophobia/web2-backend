package hu.tilos.radio.backend.show;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowRepository extends MongoRepository<MongoShow, String> {
}
