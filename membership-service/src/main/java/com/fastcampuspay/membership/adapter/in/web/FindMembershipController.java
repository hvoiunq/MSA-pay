package com.fastcampuspay.membership.adapter.in.web;

import com.fastcampuspay.common.annotation.WebAdapter;
import com.fastcampuspay.membership.adapter.in.web.dto.response.MembershipResponse;
import com.fastcampuspay.membership.application.port.in.FindMembershipCommand;
import com.fastcampuspay.membership.application.port.in.FindMembershipUseCase;
import com.fastcampuspay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMembershipController {

    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping(path = "/membership/{membershipId}")
    ResponseEntity<MembershipResponse> findMembershipByMemberId(@PathVariable long membershipId) {

        FindMembershipCommand command = new FindMembershipCommand(membershipId);

        Membership membership = findMembershipUseCase.findMembership(command);
        MembershipResponse response = MembershipResponse.of(membership);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
