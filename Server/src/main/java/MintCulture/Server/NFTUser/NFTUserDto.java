package MintCulture.Server.NFTUser;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NFTUserDto {

    @Column(name ="primary_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 일렬번호(PK)

    @Column(name ="owner_name", nullable = false)
    private String owner; // NFT소유자 이름

    @Column(name ="subs_month", nullable = false)
    private int subscriptionMonth; // 구독 월 수

    @Column(name ="donnated_amount", nullable = false )
    private Long totalDonationValue; // 후원 액수

    @Column(name ="user_level", nullable = false )
    private int level; // 레벨 for 해금

    @Column(name ="exp", nullable = false)
    private long nextToLevel = 1; // 다음 레벨업에 필요한 일종의 경험치

    @Column(name ="created_time", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdTime; // 생성된 시간 (UTC)
}