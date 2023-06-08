package com.example.hs;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class HsService {
    @Autowired
    private HsRepository repo;
    public List<Hs> listAll(String keyword) {
        if (keyword!=null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
    public void save(Hs hs) {
        repo.save(hs);
    }
    public Hs get(Long id) {
        return repo.findById(id).get();
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }

}


