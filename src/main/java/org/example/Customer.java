package org.example;

import lombok.*;

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
