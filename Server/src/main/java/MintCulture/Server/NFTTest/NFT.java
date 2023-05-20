package MintCulture.Server.NFTTest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class NFT {

    @Id
    @Column(name ="serial_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 일렬번호(PK)

    @Column(name ="owner_name")
    private String owner; // NFT소유자 이름

    @Column(name ="subs_month")
    private int subscriptionMonth; // 구독 월 수

    @Column(name ="donnated_amount")
    private Long totalDonationValue; // 후원 액수

    @Column(name ="user_level")
    private int level; // 레벨 for 해금

    @Column(name ="exp")
    private Long nextToLevel; // 다음 레벨업에 필요한 일종의 경험치

}




