package MintCulture.Server.NFTTest;

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

    public NFT saveNFT(NFTDto nftdto) {
        NFT nft = new NFT();
        nft.setOwner(nftdto.getOwner());
        nft.setSubscriptionMonth(nftdto.getSubscriptionMonth());
        nft.setTotalDonationValue(nftdto.getTotalDonationValue());
        nft.setLevel(nftdto.getLevel());
        nft.setNextToLevel(nftdto.getNextToLevel());
        return nftRepository.save(nft);
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
    public List<NFT> getAllNFTs() {
        List<NFT> nfts = nftRepository.findAll();
        if(nfts!= null){
            return nfts;
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
    public NFT getNFTById(Long id) {
        Optional<NFT> nftOptional = nftRepository.findById(id);
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
    public NFT updateNFT(Long id, NFTDto nftdto) {
        NFT existingNFT = nftRepository.findById(id)
                .orElse(null);

        if (existingNFT != null) {
            // Update the properties of the existing NFT object
            existingNFT.setOwner(nftdto.getOwner());
            existingNFT.setSubscriptionMonth(nftdto.getSubscriptionMonth());
            existingNFT.setTotalDonationValue(nftdto.getTotalDonationValue());
            existingNFT.setLevel(nftdto.getLevel());
            existingNFT.setNextToLevel(nftdto.getNextToLevel());

            // Save the updated NFT
            return nftRepository.save(existingNFT);
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
    public boolean deleteNFT(Long id) {
        if (nftRepository.existsById(id)) {
            nftRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}