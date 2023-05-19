package MintCulture.Server.Test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bread")
public class BreadController {
    private final BreadService breadService;

    @GetMapping
    public ResponseEntity<Iterable<Bread>> getAllBread(){
        Iterable<Bread> bread = breadService.getAllBread();
        return new ResponseEntity<>(bread, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bread> getBread(@PathVariable Long id){
        Bread bread = breadService.getBread(id);
        if(bread!=null){
            return new ResponseEntity<>(bread,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Bread> createBread(@RequestBody Bread bread){
        Bread newBread = breadService.createBread(bread);
        return new ResponseEntity<>(newBread,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bread> updateBread(@PathVariable Long id,@RequestBody Bread bread){
        Bread oldBread = breadService.getBread(id);
        if(oldBread != null){
            bread.setId(id);
            Bread updatedBread = breadService.updateBread(bread);
            return new ResponseEntity<>(updatedBread,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBread(@PathVariable Long id){
        Bread oldBread = breadService.getBread(id);
        if(oldBread != null){
            breadService.deleteBread(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}