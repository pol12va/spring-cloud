package com.sg.recommendations.api;

import com.google.common.collect.Sets;
import com.sg.recommendations.domain.exceptions.UserNotFoundException;
import com.sg.recommendations.api.external.MembershipApi;
import com.sg.springcloud.common.domain.entity.Member;
import com.sg.springcloud.common.domain.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationsController {

    private MembershipApi membershipApi;

    private Set<Movie> kidRecommendations = Sets.newHashSet(new Movie("lion king"), new Movie("frozen"));
    private Set<Movie> adultRecommendations = Sets.newHashSet(new Movie("shawshank redemption"), new Movie("spring"));

    public RecommendationsController(@Autowired MembershipApi membershipApi) {
        this.membershipApi = membershipApi;
    }

    @RequestMapping("/{user}")
    public Set<Movie> findRecommendationsForUser(@PathVariable String user) throws UserNotFoundException {
        System.out.println("User name: " + user);
        Member member = membershipApi.findMember(user);
        if (member == null) {
            throw new UserNotFoundException();
        }

        return member.getAge() < 17 ? kidRecommendations : adultRecommendations;
    }

}
