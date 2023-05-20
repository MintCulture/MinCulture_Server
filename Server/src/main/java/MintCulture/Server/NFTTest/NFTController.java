package MintCulture.Server.NFTTest;

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
    public ResponseEntity<NFT> createNFT(@RequestBody NFTDto nftdto){
        NFT createdNft = nftService.saveNFT(nftdto);
        return new ResponseEntity<>(createdNft, HttpStatus.CREATED);
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
    public ResponseEntity<List<NFT>> getNfts(){
        List<NFT> nfts = nftService.getAllNFTs();
        return new ResponseEntity<>(nfts, HttpStatus.OK);
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
    public ResponseEntity<NFT> getNftById(@PathVariable Long id){
        NFT nft = nftService.getNFTById(id);
        if (nft != null) {
            return new ResponseEntity<>(nft, HttpStatus.OK);
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
    public ResponseEntity<NFT> updateNFT(@PathVariable Long id, @RequestBody NFTDto nftdto) {
        NFT updatedNFT = nftService.updateNFT(id, nftdto);
        if (updatedNFT != null) {
            return new ResponseEntity<>(updatedNFT, HttpStatus.OK);
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
