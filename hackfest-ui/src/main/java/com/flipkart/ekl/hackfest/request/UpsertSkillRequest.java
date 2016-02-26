package com.flipkart.ekl.hackfest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chaitanya.naik on 25/02/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertSkillRequest {
    private String userId;
}
