package vn.fis.logfile.vinasoy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.logfile.vinasoy.model.Star;
import vn.fis.logfile.vinasoy.repository.StarRepository;
import vn.fis.logfile.vinasoy.service.StarService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {
    @Autowired
    StarRepository starRepository;
    @Override
    public Star addstar(Star star) {
        return starRepository.save(star);
    }

    @Override
    public List<Star> ListStar(String u) {
        return starRepository.findAll().stream()
                .filter(line -> {
                    return line.getFromUser().equals(u) ;
                })
                .collect(Collectors.toList());
    }
}
