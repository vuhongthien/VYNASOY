package vn.fis.logfile.vinasoy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import vn.fis.logfile.vinasoy.model.History;
import vn.fis.logfile.vinasoy.repository.HistoryRepository;
import vn.fis.logfile.vinasoy.service.HistoryService;

import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryRepository historyRepository;
    @Override
    public List<History> listAll() {
        return historyRepository.findAll();
    }

    @Override
    public History create(History history) {
        return historyRepository.save(history);
    }
}
