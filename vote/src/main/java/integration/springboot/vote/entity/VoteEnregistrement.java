package integration.springboot.vote.entity;

import jakarta.persistence.*;

@Entity
public class VoteEnregistrement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User depute;

    @ManyToOne
    private Vote vote;

    private String choix;

    // Getters et setters
}

