package translate_message.message_translate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import translate_message.message_translate.Entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	
}
