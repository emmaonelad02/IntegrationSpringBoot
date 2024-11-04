package integration.springboot.vote.controller;


import integration.springboot.vote.entity.Vote;
import integration.springboot.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/{id}/results")
    @PreAuthorize("hasRole('PRESIDENT')") // Assurez-vous que seul le président peut appeler cet endpoint
    public ResponseEntity<Vote> getVoteResults(@PathVariable Long id) {
        Optional<Vote> vote = voteService.getVoteById(id);
        return vote.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/vote")
    @PreAuthorize("hasRole('DEPUTE')") // Assurez-vous que seuls les députés peuvent voter
    public ResponseEntity<Void> vote(@PathVariable Long id, @RequestParam boolean isYesVote) {
        try {
            voteService.recordVote(id, isYesVote);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}


