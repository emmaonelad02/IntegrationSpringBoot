package integration.springboot.vote.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id
    private String id;
    private String prenom;
    private String nom;
    private String role;


    @OneToMany(mappedBy = "depute")
    private List<VoteEnregistrement> votesEffectues;
}
