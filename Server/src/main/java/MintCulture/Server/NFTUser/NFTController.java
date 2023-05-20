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
    public ResponseEntity<NFTUser> createNFT(@RequestBody NFTUserDto nftdto){
        NFTUser createdNftUser = nftService.saveNFT(nftdto);
        
        // NFT 발급
        
        return new ResponseEntity<>(createdNftUser, HttpStatus.CREATED);
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
    public ResponseEntity<List<NFTUser>> getNfts(){
        List<NFTUser> nftUsers = nftService.getAllNFTs();
        return new ResponseEntity<>(nftUsers, HttpStatus.OK);
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
    public ResponseEntity<NFTUser> getNftById(@PathVariable Long id){
        NFTUser nftUser = nftService.getNFTById(id);
        if (nftUser != null) {
            return new ResponseEntity<>(nftUser, HttpStatus.OK);
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
    public ResponseEntity<NFTUser> updateNFT(@PathVariable long id, @RequestBody NFTUserDto nftdto) {

        NFTUser updatedNFTUser = nftService.updateNFT(id, nftdto);

        if (updatedNFTUser != null) {

//            long iD = updatedNFTUser.getId();
//            int subsMonth = updatedNFTUser.getSubscriptionMonth();
//            long donation = updatedNFTUser.getTotalDonationValue();
//
//            nftService.getExp(iD,subsMonth,donation);
//            //getExp(Long id, int subsMonth, long donation)

            return new ResponseEntity<>(updatedNFTUser, HttpStatus.OK);
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
