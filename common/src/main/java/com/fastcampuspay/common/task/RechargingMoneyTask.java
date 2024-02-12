package com.fastcampuspay.common.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RechargingMoneyTask { // 돈 충전
    private String taskId;
    private String taskName;
    private String membershipId;
    private List<SubTask> subTaskList;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;
}
