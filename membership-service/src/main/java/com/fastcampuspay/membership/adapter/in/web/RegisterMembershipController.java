package com.fastcampuspay.membership.adapter.in.web;


import com.fastcampuspay.common.annotation.WebAdapter;
import com.fastcampuspay.membership.adapter.in.web.dto.request.RegisterMembershipRequest;
import com.fastcampuspay.membership.adapter.in.web.dto.response.MembershipResponse;
import com.fastcampuspay.membership.application.port.in.RegisterMembershipCommand;
import com.fastcampuspay.membership.application.port.in.RegisterMembershipUseCase;
import com.fastcampuspay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping(path = "/membership/register")
    ResponseEntity<MembershipResponse> registerMembership(@RequestBody RegisterMembershipRequest request) {
        // request~~

        // request -> command

        // Usecase~~ (command를 받는 역할)

        RegisterMembershipCommand command = request.toCommand();
        Membership membership = registerMembershipUseCase.registerMembership(command);
        MembershipResponse response = MembershipResponse.of(membership);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
