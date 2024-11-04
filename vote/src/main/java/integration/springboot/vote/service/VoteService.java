package integration.springboot.vote.service;

import integration.springboot.vote.entity.Vote;
import integration.springboot.vote.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Optional<Vote> getVoteById(Long id) {
        return voteRepository.findById(id);
    }

    public Vote saveVote(Vote vote) {
        return voteRepository.save(vote);
    }

    public void recordVote(Long voteId, boolean isYesVote) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new RuntimeException("Vote not found"));
        if ("clos".equals(vote.getEtat())) {
            throw new RuntimeException("Le vote est déjà clos");
        }
        if (isYesVote) {
            vote.setOui(vote.getOui() + 1);
        } else {
            vote.setNon(vote.getNon() + 1);
        }
        vote.setVotants(vote.getVotants() + 1);
        voteRepository.save(vote);
    }
}


