package com.flipkart.ekl.hackfest.coreengineapis.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LMSSkillRequest {
    private String email_id;
    private String skill;
    private float level;
}
