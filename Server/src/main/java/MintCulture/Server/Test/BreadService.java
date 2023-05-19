package MintCulture.Server.Test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreadService {
    private final BreadRepository breadRepository;

//    @Autowired
//    public BreadService(BreadRepository breadRepository){
//        this.breadRepository = breadRepository;
//    }

    public Iterable<Bread> getAllBread(){
        return breadRepository.findAll();
    }
//    public List<Bread> getAllBread(){
//        return breadRepository.findAll();
//    }

    //    public Optional<Bread> getBread(Long id){
//        return breadRepository.findById(id);
//    }
    public Bread getBread(Long id){
        return breadRepository.findById(id).orElse(null);
    }

    public Bread createBread(Bread bread){
        return breadRepository.save(bread);
    }

    public Bread updateBread(Bread bread){
        return breadRepository.save(bread);
    }

    public void deleteBread(Long id){
        breadRepository.deleteById(id);
    }
}