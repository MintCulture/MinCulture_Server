//package MintCulture.Server.NFT;
//
//import MintCulture.Server.Streamer.Streamer;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.data.annotation.CreatedDate;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class NFT {
//
//    @Id
//    @Column(name="serial_number")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id; // 일렬번호(PK)
//
//    @Column(name="owner_name")
//    private String owner; // NFT소유자 이름
//
//    @ManyToOne
//    private Streamer streamer;
//
//    @Column(name="issued_time",nullable = false)
//    private Timestamp createdTime; // 발행날짜
//
//    private String metadata;
//
//    private String nftMetadata;
//
//    private String nftImageReference;
//
//    @Column(name="subs_months")
//    private int subscriptionMonth; // 구독 월 수
//
//    @Column(name="donated_amount")
//    private double totalDonationValue; // 후원 액수
//
//    @Column(name="user_level")
//    private int level; // 레벨 for 해금
//
//    @Column(name="exp")
//    private int nextToLevel; // 다음 레벨업에 필요한 일종의 경험치
//
//}
//
//
//
//
