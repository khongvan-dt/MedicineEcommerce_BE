 package aptech.vn.backend.dto;
 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;
 import java.time.LocalDateTime;

 @Data
 public class VouchersDTO {
     @Data
     @NoArgsConstructor
     @AllArgsConstructor
     public static class SaveVouchersDto {
         private Long id;
         private String code;
         private Double voucherPercentage;
         private Integer stock;
         private LocalDateTime startDate;
         private LocalDateTime endDate;
     }

     @Data
     @NoArgsConstructor
     @AllArgsConstructor
     public static class GetVouchersDto {
         private Long id;
         private String code;
         private Double voucherPercentage;
         private Integer stock;
         private LocalDateTime startDate;
         private LocalDateTime endDate;
         private LocalDateTime createdAt;
         private LocalDateTime updatedAt;
     }

 }
