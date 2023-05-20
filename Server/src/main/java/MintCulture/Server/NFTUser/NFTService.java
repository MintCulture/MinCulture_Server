package MintCulture.Server.NFTUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public UserNFT saveNFT(NFTDto nftdto) {
        UserNFT userNft = new UserNFT();
        userNft.setOwner(nftdto.getOwner());
        userNft.setSubscriptionMonth(nftdto.getSubscriptionMonth());
        userNft.setTotalDonationValue(nftdto.getTotalDonationValue());
        userNft.setLevel(nftdto.getLevel());
        userNft.setNextToLevel(nftdto.getNextToLevel());
        return nftRepository.save(userNft);
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
    public List<UserNFT> getAllNFTs() {
        List<UserNFT> userNfts = nftRepository.findAll();
        if(userNfts != null){
            return userNfts;
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
    public UserNFT getNFTById(Long id) {
        Optional<UserNFT> nftOptional = nftRepository.findById(id);
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
    public UserNFT updateNFT(Long id, NFTDto nftdto) {
        UserNFT existingUserNFT = nftRepository.findById(id)
                .orElse(null);

        if (existingUserNFT != null) {
            // Update the properties of the existing NFT object
            existingUserNFT.setOwner(nftdto.getOwner());
            existingUserNFT.setSubscriptionMonth(nftdto.getSubscriptionMonth());
            existingUserNFT.setTotalDonationValue(nftdto.getTotalDonationValue());
            existingUserNFT.setLevel(nftdto.getLevel());
            existingUserNFT.setNextToLevel(nftdto.getNextToLevel());

            // Save the updated NFT
            UserNFT updatedUserNFT = nftRepository.save(existingUserNFT);

            return updatedUserNFT;
        }
        else {
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
    public boolean deleteNFT(Long id) {
        if (nftRepository.existsById(id)) {
            nftRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    /*
        사용자 레벨에 따라 아이템 해금 -> 언제 getExp가 호출될지 정해줘야함
     */
    public void getExp(Long id, int subsMonth, long donation) {
        UserNFT userNft = nftRepository.findById(id).orElse(null);
        if (userNft != null) {
            long exp = subsMonth * 10 + (donation / 10000); // 예) 구독3개월 도네10만원 -> (30+10)/10 -> 4
            int newLevel = levelUp(userNft.getLevel(), exp);

            if (newLevel == 2) {
                userNft.setLevel(newLevel);
                nftRepository.save(userNft); // 레벨을 2로 업데이트하고 데이터베이스에 저장
            } else if (newLevel == 3) {
                userNft.setLevel(newLevel);
                nftRepository.save(userNft); // 레벨을 3으로 업데이트하고 데이터베이스에 저장
            }
        }
    }

    /*
        경험치에 따라 사용자 레벨업
     */
    private int levelUp(int currentLevel, long exp) {
        int newLevel = currentLevel;
        long tmp = exp / 10;
        if (tmp >= 3) {
            newLevel += 1;
        } else if (tmp >= 6) {
            newLevel += 1;
        } else if (tmp >= 9) {
            newLevel += 1;
        }
        return newLevel;
    }

}