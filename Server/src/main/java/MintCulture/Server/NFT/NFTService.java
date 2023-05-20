//package MintCulture.Server.NFT;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class NFTService {
//
//    private final NFTRepository nftRepository;
//
//
//    /*
//       API: Create NFT
//       Request: Create NFT
//       Method: POST
//       Endpoint: '/nfts'
//       Description: Creates a new NFT.
//       Input:
//           - nftdto: The NFT data to be created.
//       Output:
//           - nft: The created NFT object.
//   */
//
//    public NFT saveNFT(NFT nft) {
//        return nftRepository.save(nft);
//    }
//
//    /*
//        API: Get All NFTs
//        Request: Get NFTs
//        Method: GET
//        Endpoint: '/nfts'
//        Description: Retrieves all NFTs.
//        Output:
//            - nfts: A list of NFT objects.
//    */
//    public List<NFT> getAllNFTs() {
//        return nftRepository.findAll();
//    }
//
//    /*
//        API: Get NFT by ID
//        Request: Get NFT by ID
//        Method: GET
//        Endpoint: '/nfts/{id}'
//        Description: Retrieves the NFT with the specified ID.
//        Input:
//            - id: The ID of the NFT to retrieve.
//        Output:
//            - nft: The NFT object with the specified ID.
//    */
//    public NFT getNFTById(Long id) {
//        return nftRepository.findById(id).orElse(null);
//    }
//
//    /*
//    API: Update NFT
//    Request: Update NFT
//    Method: PUT
//    Endpoint: '/nfts/{id}'
//    Description: Updates the NFT with the specified ID.
//    Input:
//        - id: The ID of the NFT to update.
//        - nftdto: The updated NFT data.
//    Output:
//        - nft: The updated NFT object.
//*/
//    public NFT updateNFT(Long id, NFT nftdto) {
//        NFT existingNFT = nftRepository.findById(id)
//                .orElse(null);
//
//        if (existingNFT != null) {
//            // Update the properties of the existing NFT object
//            existingNFT.setOwner(nftdto.getOwner());
//            existingNFT.setMetadata(nftdto.getMetadata());
//
//            // Save the updated NFT
//            return nftRepository.save(existingNFT);
//        } else {
//            return null;
//        }
//    }
//
//    /*
//        API: Delete NFT
//        Request: Delete NFT
//        Method: DELETE
//        Endpoint: '/nfts/{id}'
//        Description: Deletes the NFT with the specified ID.
//        Input:
//            - id: The ID of the NFT to delete.
//        Output:
//            - deleted: A boolean indicating whether the NFT was successfully deleted.
//    */
//    public boolean deleteNFT(Long id) {
//        if (nftRepository.existsById(id)) {
//            nftRepository.deleteById(id);
//            return true;
//        } else {
//            return false;
//        }
//    }
//}
