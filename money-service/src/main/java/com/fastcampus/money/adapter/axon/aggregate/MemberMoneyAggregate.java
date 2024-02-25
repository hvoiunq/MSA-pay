package com.fastcampus.money.adapter.axon.aggregate;

import com.fastcampus.money.adapter.axon.command.CreatedMemberMoneyCommand;
import com.fastcampus.money.adapter.axon.event.CreatedMemberMoneyEvent;
import com.fastcampus.money.adapter.axon.event.IncreaseMemberMoneyEvent;
import com.fastcampus.money.application.port.in.IncreaseMoneyReqCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate()
@Data
@NoArgsConstructor
public class MemberMoneyAggregate {

    @AggregateIdentifier
    private String id;

    private long membershipId;

    private long balance;

    @CommandHandler
    public MemberMoneyAggregate(CreatedMemberMoneyCommand command) {
        System.out.println("MemberMoneyAggregate.MemberMoneyAggregate.create");

        apply(new CreatedMemberMoneyEvent(command.getTargetMembershipId())); //event 생성 및 저장
    }

    @CommandHandler
    public MemberMoneyAggregate(@NotNull IncreaseMoneyReqCommand command) {
        System.out.println("MemberMoneyAggregate.MemberMoneyAggregate.increase");

        id = command.getAggregateIdentifier();

        //store event
        apply(new IncreaseMemberMoneyEvent(id, command.getMembershipId(), command.getAmount()));
    }

    @EventSourcingHandler
    public void on(CreatedMemberMoneyEvent event) { // event sourcing해다가 event로부터 member aggregate를 생성함.
        System.out.println("MemberMoneyAggregate.on.create");

        id = UUID.randomUUID().toString();
        membershipId = event.getTargetMembershipId();
        balance = 0;
    }
    @EventSourcingHandler
    public void on(IncreaseMoneyReqCommand event) { // event sourcing해다가 event로부터 member aggregate를 생성함.
        System.out.println("MemberMoneyAggregate.on.increase");

        id = event.getAggregateIdentifier();
        membershipId = event.getMembershipId();
        balance = event.getAmount();
    }

}
