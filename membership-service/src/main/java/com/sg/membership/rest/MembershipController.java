package com.sg.membership.rest;


import com.google.common.collect.ImmutableMap;
import com.sg.springcloud.common.domain.entity.Member;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/member")
public class MembershipController {

    private final Map<String, Member> memberStore = ImmutableMap.of(
            "jschneider", new Member("jschneider", 10),
            "twicksell", new Member("twicksell", 30)
    );

    @RequestMapping("/{user}")
    public Member findMemberByName(@PathVariable(name = "user") String userName) {
        return memberStore.get(userName);
    }

}