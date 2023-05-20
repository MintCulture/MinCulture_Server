package MintCulture.Server.NFTUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NFTService {

    private final NFTRepository nftRepository;


    /*
       API: Create NFT
       Request: Create NFT
       Method: POST
       Endpoint: '/nfts'
       Description: Creates a new NFT.
       Input:
           - nftdto: The NFT data to be created.
       Output:
           - nft: The created NFT object.
   */

    public NFTUser saveNFT(NFTUserDto nftdto) {
        NFTUser nftUser = new NFTUser();
        nftUser.setOwner(nftdto.getOwner());
        nftUser.setSubscriptionMonth(nftdto.getSubscriptionMonth());
        nftUser.setTotalDonationValue(nftdto.getTotalDonationValue());
        nftUser.setLevel(nftdto.getLevel());
        nftUser.setNextToLevel(nftdto.getNextToLevel());
        nftUser.setSerialNum((nftdto.getSerialNum()));
        return nftRepository.save(nftUser);
    }

    /*
        API: Get All NFTs
        Request: Get NFTs
        Method: GET
        Endpoint: '/nfts'
        Description: Retrieves all NFTs.
        Output:
            - nfts: A list of NFT objects.
    */
    public List<NFTUser> getAllNFTs() {
        List<NFTUser> nftUsers = nftRepository.findAll();
        if(nftUsers != null){
            return nftUsers;
        }
        else{
            return null;
        }
    }

    /*
        API: Get NFT by ID
        Request: Get NFT by ID
        Method: GET
        Endpoint: '/nfts/{id}'
        Description: Retrieves the NFT with the specified ID.
        Input:
            - id: The ID of the NFT to retrieve.
        Output:
            - nft: The NFT object with the specified ID.
    */
    public NFTUser getNFTById(Long serialNum) {
        Optional<NFTUser> nftOptional = nftRepository.findById(serialNum);
        return nftOptional.orElse(null);
    }

    /*
    API: Update NFT
    Request: Update NFT
    Method: PUT
    Endpoint: '/nfts/{id}'
    Description: Updates the NFT with the specified ID.
    Input:
        - id: The ID of the NFT to update.
        - nftdto: The updated NFT data.
    Output:
        - nft: The updated NFT object.
*/
    public NFTUser updateNFT(Long serialNum, NFTUserDto nftdto) {
        NFTUser existingNFTUser = nftRepository.findById(serialNum).orElse(null);

        if (existingNFTUser != null) {
            int subsMonth = existingNFTUser.getSubscriptionMonth();
            String curId = existingNFTUser.getSerialNum();
            long donation = existingNFTUser.getTotalDonationValue();
            int curLevel = existingNFTUser.getLevel();
            long curExp = existingNFTUser.getNextToLevel();

            LocalDateTime time = existingNFTUser.getCreatedTime();

            if (isOneMonthPassed(time)) { // 구독 1달이 지나면 경험치 획득 및 현재 구독개월수+1
                getExp(curId, subsMonth, donation);
                existingNFTUser.setSubscriptionMonth(nftdto.getSubscriptionMonth()+1);
            }

            existingNFTUser.setOwner(nftdto.getOwner());
            existingNFTUser.setTotalDonationValue(nftdto.getTotalDonationValue());

            if (curLevel == 1) {
                existingNFTUser.setLevel(curLevel + 1);
                existingNFTUser.setNextToLevel(nftdto.getNextToLevel() + 3);
            } else if (curLevel == 2) {
                existingNFTUser.setLevel(curLevel + 1);
                existingNFTUser.setNextToLevel(nftdto.getNextToLevel() + 3);
            } else if (curLevel >= 3) {
                existingNFTUser.setLevel(3);
                existingNFTUser.setNextToLevel(nftdto.getNextToLevel());
            }

            // Save the updated NFTUser
            NFTUser updatedNFTUser = nftRepository.save(existingNFTUser);

            return updatedNFTUser;
        } else {
            return null;
        }
    }

    /*
        API: Delete NFT
        Request: Delete NFT
        Method: DELETE
        Endpoint: '/nfts/{id}'
        Description: Deletes the NFT with the specified ID.
        Input:
            - id: The ID of the NFT to delete.
        Output:
            - deleted: A boolean indicating whether the NFT was successfully deleted.
    */
    public boolean deleteNFT(Long serialNum) {
        if (nftRepository.existsById(serialNum)) {
            nftRepository.deleteById(serialNum);
            return true;
        } else {
            return false;
        }
    }


    /*
        사용자 레벨에 따라 아이템 해금 -> 언제 getExp가 호출될지 정해줘야함
     */
    public void getExp(String serialNum, int subsMonth, long donation) {
        long id = Long.parseLong(serialNum);
        NFTUser nftUser = nftRepository.findById(id).orElse(null);
        if (nftUser != null) {
            long exp = subsMonth * 10 + (donation / 10000); // 예) 구독 3개월 도네 10만원 -> (30+10)/10 -> 경험치:4
            long nextToLevel = nftUser.getNextToLevel();
            int curLevel = nftUser.getLevel();
           

            if (exp >= nextToLevel) {
                nftUser.setLevel(curLevel + 1);
                nftUser.setNextToLevel(nextToLevel + 3); // 레벨이 올라갈 때마다 다음 레벨에 필요한 경험치를 3 증가시킴
                nftRepository.save(nftUser);
            }
        }
    }


    /*
         한 달 지났는지 체크
    */
    public static boolean isOneMonthPassed(LocalDateTime timestamp) {
        LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);
        long monthsDifference = ChronoUnit.MINUTES.between(timestamp, currentTime);
        // 원래는 ChronoUnit.MONTHS지만 빠른 테스트를 위해 1분 차이를 체크
        return monthsDifference >= 1;
    }

   
}