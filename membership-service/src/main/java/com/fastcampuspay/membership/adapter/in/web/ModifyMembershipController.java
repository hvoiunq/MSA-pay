package com.fastcampuspay.membership.adapter.in.web;

import com.fastcampuspay.common.annotation.WebAdapter;
import com.fastcampuspay.membership.adapter.in.web.dto.request.ModifyMembershipRequest;
import com.fastcampuspay.membership.adapter.in.web.dto.response.MembershipResponse;
import com.fastcampuspay.membership.application.port.in.ModifyMembershipCommand;
import com.fastcampuspay.membership.application.port.in.ModifyMembershipUseCase;
import com.fastcampuspay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {

    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PostMapping(path = "/membership/{membershipId}/modify")
    ResponseEntity<MembershipResponse> modifyMembership(@PathVariable(value = "membershipId") long membershipId, @RequestBody ModifyMembershipRequest request) {

        ModifyMembershipCommand command = request.toCommand(membershipId);
        Membership membership = modifyMembershipUseCase.modifyMembership(command);
        MembershipResponse response = MembershipResponse.of(membership);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}