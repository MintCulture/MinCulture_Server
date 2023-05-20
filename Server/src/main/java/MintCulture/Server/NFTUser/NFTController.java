package MintCulture.Server.NFTUser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nfts")

public class NFTController {
    private final NFTService nftService;


    /*
       API:create NFT
       Request:Create NFT
       Method:Post
       Endpoint: '/nfts'
       Description: Creates a new NFT.
       Input:
           - nftdto: The NFT data to be created.
       Output:
           - nft: The created NFT object.
     */
    @PostMapping
    public ResponseEntity<UserNFT> createNFT(@RequestBody NFTDto nftdto){
        UserNFT createdUserNft = nftService.saveNFT(nftdto);
        
        // NFT 발급
        
        return new ResponseEntity<>(createdUserNft, HttpStatus.CREATED);
    }

    /*
        API:Get All NFts
        Request: Get NFTs
        Method: GET
        Endpoint: '/api/nfts/all'
        Description: Retrieves all NFTs.
        Output:
            - nfts: A list of NFT objects.
    */
    @GetMapping("/all")
    public ResponseEntity<List<UserNFT>> getNfts(){
        List<UserNFT> userNfts = nftService.getAllNFTs();
        return new ResponseEntity<>(userNfts, HttpStatus.OK);
    }

     /*
        Request: Get NFT by ID
        Method: GET
        Endpoint: 'api/nfts/{id}'
        Description: Retrieves the NFT with the specified ID.
        Input:
            - id: The ID of the NFT to retrieve.
        Output:
            - nft: The NFT object with the specified ID.
    */
    @GetMapping("/{id}")
    public ResponseEntity<UserNFT> getNftById(@PathVariable Long id){
        UserNFT userNft = nftService.getNFTById(id);
        if (userNft != null) {
            return new ResponseEntity<>(userNft, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*
    API: Update NFT
    Request: Update NFT
    Method: PUT
    Endpoint: 'api/nfts/{id}'
    Description: Updates the NFT with the specified ID.
    Input:
        - id: The ID of the NFT to update.
        - nftdto: The updated NFT data.
    Output:
        - nft: The updated NFT object.
*/
    @PutMapping("/{id}")
    public ResponseEntity<UserNFT> updateNFT(@PathVariable long id, @RequestBody NFTDto nftdto) {

        UserNFT updatedUserNFT = nftService.updateNFT(id, nftdto);

        if (updatedUserNFT != null) {

            long iD = updatedUserNFT.getId();
            int subsMonth = updatedUserNFT.getSubscriptionMonth();
            long donation = updatedUserNFT.getTotalDonationValue();

            nftService.getExp(iD,subsMonth,donation);
            //getExp(Long id, int subsMonth, long donation)
            return new ResponseEntity<>(updatedUserNFT, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    /*
        API: Delete NFT
        Request: Delete NFT
        Method: DELETE
        Endpoint: 'api/nfts/{id}'
        Description: Deletes the NFT with the specified ID.
        Input:
            - id: The ID of the NFT to delete.
        Output:
            - None.
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNFT(@PathVariable Long id) {
        boolean deleted = nftService.deleteNFT(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
