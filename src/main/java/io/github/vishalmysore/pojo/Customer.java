package io.github.vishalmysore.pojo;

import lombok.*;

/**
 * These are all your pojos that are used to send data to the AI processor.
 * They will get automatically populated based on the NLP
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Customer {
    private String customerName;
    private String customerLocation;
    private String problemFaced;
}
