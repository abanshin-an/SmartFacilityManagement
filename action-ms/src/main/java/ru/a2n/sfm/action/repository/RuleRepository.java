package ru.a2n.sfm.action.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.a2n.sfm.action.model.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, String> {
    List<Rule> getByMessageType(String messageType);
}
