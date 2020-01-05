package com.humanup.matrixcollaborator.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of= {"projectTitle","projectDescription"})
public class ProjectVO {
    String projectTitle;
    String projectDescription;
}
