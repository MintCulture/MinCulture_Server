package MintCulture.Server.NFTUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NFTRepository extends JpaRepository<UserNFT,Long> {

}
