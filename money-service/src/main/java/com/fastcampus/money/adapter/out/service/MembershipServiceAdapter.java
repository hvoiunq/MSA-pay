package com.fastcampus.money.adapter.out.service;

import com.fastcampus.money.application.port.out.external.GetMembershipPort;
import com.fastcampus.money.application.port.out.external.MembershipStatus;
import com.fastcampuspay.common.client.CommonHttpClient;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.net.http.HttpResponse;

public class MembershipServiceAdapter implements GetMembershipPort {

    private final CommonHttpClient commonHttpClient;
    private final String membershipServiceUrl;

    public MembershipServiceAdapter(CommonHttpClient commonHttpClient,
                                    @Value("${service.membership.url}") String membershipServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.membershipServiceUrl = membershipServiceUrl;
    }

    @Override
    public MembershipStatus getMembership(String membershipId) {

        String url = String.join("/", membershipServiceUrl, "membership", membershipId);

        try {
            HttpResponse<String> stringHttpResponse = commonHttpClient.sendGetRequest(url);
            //json 형태의 membership 정보

            ObjectMapper objectMapper = new ObjectMapper();
            Membership membership = objectMapper.readValue((JsonParser) stringHttpResponse, Membership.class);

            if (membership.isValid()) {
                return new MembershipStatus(membership.getId(), true);
            } else {
                return new MembershipStatus(membership.getId(), false);
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }

        // 실제 http call로 memebership 호출
        // http client (tcp timeout, pod, connection pool 지정이 잘 되어야한다.)

    }
}
