package integration.springboot.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteEnregistrementRepository extends JpaRepository {
    @Override
    Optional findById(Object o);
}