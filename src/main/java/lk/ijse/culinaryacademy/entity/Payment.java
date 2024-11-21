
package lk.ijse.culinaryacademy.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Payment {
    private String registrationId;
    private double amount;
    private String paymentMethod;
    private String paymentDate;
    private String transactionId;
    private String description;
}
